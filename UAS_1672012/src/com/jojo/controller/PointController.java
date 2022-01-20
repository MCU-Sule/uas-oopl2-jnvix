/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jojo.controller;

import com.jojo.dao.PointDaoImpl;
import com.jojo.entity.Point;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author 1672012
 */
public class PointController implements Initializable {

    @FXML
    private TextField txtName;
    @FXML
    private TextField txtCapacity;
    @FXML
    private TextField txtId;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<Laboratorium> tableLab;
    @FXML
    private TableColumn<Laboratorium, Integer> col1;
    @FXML
    private TableColumn<Laboratorium, String> col2;
    @FXML
    private TableColumn<Laboratorium, Integer> col3;
    private ObservableList<Laboratorium> laboratoriumList;
    private LaboratoriumDaoImpl laboratoriumDaoImpl ;

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
