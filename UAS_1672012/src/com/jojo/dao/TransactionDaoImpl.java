/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jojo.dao;

import com.rossevine.entity.Laboratorium;
import com.rossevine.entity.Maintenance;
import com.rossevine.entity.User;
import com.rossevine.util.DaoService;
import com.rossevine.util.MySQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 1672012
 */
public class TransactionDaoImpl implements DaoService<Maintenance> {

    @Override
    public List<Maintenance> fetchAllList() throws SQLException, ClassNotFoundException {
        List<Maintenance> maintenanceList = new ArrayList<>();
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT m.id as id, m.task as task , u.name as user_name, m.user_id as user_id, m.date as date, l.name as lab_name, m.laboratorium_id as lab_id FROM maintenance m JOIN user u on m.user_id = u.id JOIN laboratorium l on m.laboratorium_id = l.id";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        User user = new User();
                        user.setId(rs.getInt("user_id"));
                        user.setName(rs.getString("user_name"));

                        Laboratorium laboratorium = new Laboratorium();
                        laboratorium.setId(rs.getInt("lab_id"));
                        laboratorium.setName(rs.getString("lab_name"));

                        Maintenance maintenance = new Maintenance();
                        maintenance.setId(rs.getInt("id"));
                        maintenance.setTask(rs.getString("task"));
                        maintenance.setDate(rs.getDate("date"));
                        maintenance.setUser(user);
                        maintenance.setLaboratorium(laboratorium);
                        maintenanceList.add(maintenance);
                    }
                }
            }
        }
        return maintenanceList;
    }

    public List<Maintenance> fetchList(User t) throws SQLException, ClassNotFoundException {
        List<Maintenance> maintenanceList = new ArrayList<>();
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT m.id as id, m.task as task , u.name as user_name, u.id as user_id, m.date as date, l.name as lab_name, l.id as lab_id FROM maintenance m JOIN user u on m.user_id = u.id JOIN laboratorium l on m.laboratorium_id = l.id WHERE m.user_id =?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, t.getId());
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        User user = new User();
                        user.setId(rs.getInt("user_id"));
                        user.setName(rs.getString("user_name"));

                        Laboratorium laboratorium = new Laboratorium();
                        laboratorium.setId(rs.getInt("lab_id"));
                        laboratorium.setName(rs.getString("lab_name"));

                        Maintenance maintenance = new Maintenance();
                        maintenance.setId(rs.getInt("id"));
                        maintenance.setTask(rs.getString("task"));
                        maintenance.setDate(rs.getDate("date"));
                        maintenance.setUser(user);
                        maintenance.setLaboratorium(laboratorium);
                        maintenanceList.add(maintenance);
                    }
                }
            }
        }
        return maintenanceList;
    }

    @Override
    public int addData(Maintenance t) throws ClassNotFoundException, SQLException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "INSERT INTO maintenance(user_id,laboratorium_id,task,date) VALUES(?,?,?,?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, t.getUser().getId());
                ps.setInt(2, t.getLaboratorium().getId());
                ps.setString(3, t.getTask());
                ps.setDate(4, t.getDate());
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;
    }

    @Override
    public int deleteData(Maintenance t) throws ClassNotFoundException, SQLException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "DELETE FROM maintenance WHERE  id=?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, t.getId());
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;
    }

    @Override
    public int updateData(Maintenance t) throws ClassNotFoundException, SQLException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "UPDATE maintenance SET laboratorium_id=?,task=?, date=? WHERE id=?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setInt(1, t.getLaboratorium().getId());
                ps.setString(2, t.getTask());
                ps.setDate(3, t.getDate());
                ps.setInt(4,t.getId());
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;
    }

}
