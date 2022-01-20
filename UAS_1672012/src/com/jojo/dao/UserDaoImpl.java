/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jojo.dao;

import com.jojo.entity.Point;
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
public class UserDaoImpl implements DaoService<Member> {

    @Override
    public List<Member> fetchAllList() throws SQLException, ClassNotFoundException {
        List<Member> memberList = new ArrayList<>();
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT * from member";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        Member member = new Member();
                        member.setId(rs.getInt("id"));
                        member.setName(rs.getString("name"));
                        member.setPassword(rs.getString("password"));
                        member.setPosition(rs.getString("position"));
                        member.setUsername(rs.getString("username"));
                        userList.add(member);
                    }
                }
            }
        }
        return userList;
    }

    public Member fetchUser(Member member) throws ClassNotFoundException, SQLException {
        Member result;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "SELECT * from member where username = ? and password = md5(?) ";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, member.getUsername());
                ps.setString(2, member.getPassword());
                try (ResultSet rs = ps.executeQuery()) {
                    result = new Member();
                    while (rs.next()) {

                        result.setId(rs.getInt("id"));
                        result.setName(rs.getString("name"));
                        result.setPassword(rs.getString("password"));
                        result.setPosition(rs.getString("position"));
                        result.setUsername(rs.getString("username"));
                    }
                    return result;
                }
            }
        }
    }

    @Override
    public int addData(Member t) throws ClassNotFoundException, SQLException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "INSERT INTO member (name,username, password, position) VALUES(?,?,md5(?),?)";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, t.getName());
                ps.setString(2, t.getUsername());
                ps.setString(3, t.getPassword());
                ps.setString(4, t.getPosition());
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
    public int deleteData(Member t) throws ClassNotFoundException, SQLException {
        int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "DELETE FROM member WHERE  id=?";
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
    public int updateData(Member t) throws ClassNotFoundException, SQLException {
int result = 0;
        try (Connection connection = MySQLConnection.createConnection()) {
            String query = "UPDATE member SET name=?,username=?, password=?, position=? WHERE id=?";
            try (PreparedStatement ps = connection.prepareStatement(query)) {
                ps.setString(1, t.getName());
                ps.setString(2, t.getUsername());
                ps.setString(3, t.getPassword());
                ps.setString(4,t.getPosition());
                ps.setInt(5,t.getId());
                
                if (ps.executeUpdate() != 0) {
                    connection.commit();
                    result = 1;
                } else {
                    connection.rollback();
                }
            }
        }
        return result;    }

}
