/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jojo.entity;

import java.sql.Date;

/**
 *
 * @author 1672012
 */
public class Transaction {
    private int id;
    private Member member;
    private Point Point;
    private String Task;
    private Date date;

    public Transaction(int id, Member member, Point Point, String Task, Date date) {
        this.id = id;
        this.member = new Member();
        this.Point = new Point();
        this.Task = Task;
        this.date = date;
    }


    public Transaction() {
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Member getUser() {
        return member;
    }

    public void setUser(Member member) {
        this.member = member;
    }

    public Point getLaboratorium() {
        return Point;
    }

    public void setLaboratorium(Point Point) {
        this.Point = Point;
    }

    public String getTask() {
        return Task;
    }

    public void setTask(String Task) {
        this.Task = Task;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


  
    
    
            
}
