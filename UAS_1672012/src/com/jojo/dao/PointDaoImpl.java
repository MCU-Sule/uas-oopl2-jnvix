/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jojo.dao;

import com.jojo.entity.Transaction;
import com.jojo.entity.Member;
import com.jojo.util.DaoService;
import com.jojo.util.MySQLConnection;
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
public class PointDaoImpl
        implements DaoService<Laboratorium> {

    @Override
    public List<Laboratorium> fetchAllList() throws SQLException, ClassNotFoundException {
        List<Laboratorium> laboratoriumlists = new ArrayList<>();
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT * from laboratorium ";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {

                        Laboratorium laboratorium = new Laboratorium();
                        laboratorium.setId(rs.getInt("id"));
                        laboratorium.setName(rs.getString("name"));
                        laboratorium.setCapacity(rs.getInt("capacity"));
                        laboratoriumlists.add(laboratorium);
                    }
                }
            }
        }
        return laboratoriumlists;
    }

    @Override
    public int addData(Laboratorium t) throws ClassNotFoundException, SQLException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "INSERT INTO laboratorium(name,capacity) VALUES(?,?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, t.getName());
                ps.setInt(2, t.getCapacity());
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
    public int deleteData(Laboratorium t) throws ClassNotFoundException, SQLException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "DELETE FROM laboratorium WHERE  id=?";
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
    public int updateData(Laboratorium t) throws ClassNotFoundException, SQLException {
      int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "UPDATE laboratorium SET name=?, capacity =? WHERE id=?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, t.getName());
                ps.setInt(2, t.getCapacity());
                ps.setInt(3,t.getId());
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
