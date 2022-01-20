/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jojo.controller;

import com.jojo.dao.PointDaoImpl;
import com.jojo.dao.TransactionDaoImpl;
import com.jojo.entity.Point;
import com.jojo.entity.Transaction;
import com.jojo.entity.Member;
import com.jojo.dao.UserDaoImpl;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author 1672012
 */
public class UserManagementController implements Initializable {

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<User> tableUser;
    @FXML
    private TableColumn<User, Integer> col1;
    @FXML
    private TableColumn<User, String> col2;
    @FXML
    private TableColumn<User, String> col3;
    @FXML
    private TableColumn<User, String> col4;
    private ObservableList<User> userList;
    private UserDaoImpl userDaoImpl;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtUsername;
    @FXML
    private ComboBox<String> cmbPosition;
    @FXML
    private PasswordField txtPassword;
    @FXML
    private TextField txtId;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      }

    @FXML
    private void btnAddOnAction(ActionEvent event) {
      
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
      
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
      
    }

    @FXML
    private void tableOnMouseClicked(MouseEvent event) {
      
    }

}
