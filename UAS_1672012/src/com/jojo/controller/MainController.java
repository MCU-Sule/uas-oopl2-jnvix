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
import java.net.URL;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author 1672012
 */
public class MainController implements Initializable {

    @FXML
    private Menu menu;
    @FXML
    private MenuItem menuUser;
    @FXML
    private MenuItem menuLab;
    @FXML
    private MenuItem close;
    @FXML
    private TextArea txtTask;
    @FXML
    private TextField txtId;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<Laboratorium> cmbLaboratorium;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<Maintenance> tableMain;
    @FXML
    private TableColumn<Maintenance, Integer> col1;
    @FXML
    private TableColumn<Maintenance, User> col2;
    @FXML
    private TableColumn<Maintenance, Laboratorium> col3;
    @FXML
    private TableColumn<Maintenance, String> col4;
    @FXML
    private TableColumn<Maintenance, Date> col5;
    private User thisUser;

    private ObservableList<Maintenance> maintenanceList;
    private ObservableList<Laboratorium> laboratoriList;
    private MaintenanceDaoImpl maintenanceDaoImpl;
    private LaboratoriumDaoImpl laboratoriumDaoImpl;
    @FXML
    private TextField txtUser;
    public LoginController loginController;

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
        thisUser = loginController.getUser();
        txtUser.setText(thisUser.getName());
        if (!thisUser.getPosition().equals("Koordinator")) {
            menuLab.setDisable(true);
        }
        try {
            maintenanceList.addAll(maintenanceDaoImpl.fetchList(thisUser));
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        maintenanceDaoImpl = new MaintenanceDaoImpl();
        laboratoriumDaoImpl = new LaboratoriumDaoImpl();
        maintenanceList = FXCollections.observableArrayList();
        laboratoriList = FXCollections.observableArrayList();

        try {
            laboratoriList.addAll(laboratoriumDaoImpl.fetchAllList());
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);

        }
        tableMain.setItems(maintenanceList);
        cmbLaboratorium.setItems(laboratoriList);

        col1.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getId()));
        col2.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getUser()));
        col3.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getLaboratorium()));
        col4.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getTask()));
        col5.setCellValueFactory(data -> new SimpleObjectProperty(data.getValue().getDate()));
        
    }

    @FXML
    private void btnAddOnAction(ActionEvent event) {
        if (txtTask.getText().trim().isEmpty() || datePicker.getValue() == null || cmbLaboratorium.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Data belum lengkap!!");
            alert.showAndWait();
        } else {
            Maintenance maintenance = new Maintenance();
            maintenance.setUser(thisUser);
            maintenance.setDate(Date.valueOf(datePicker.getValue()));
            maintenance.setLaboratorium(cmbLaboratorium.getValue());
            maintenance.setTask(txtTask.getText());
            try {
                int result = maintenanceDaoImpl.addData(maintenance);
                if (result == 1) {
                    txtTask.clear();
                    maintenanceList.clear();
                    maintenanceList.addAll(maintenanceDaoImpl.fetchList(thisUser));
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        Laboratorium laboratorium = cmbLaboratorium.getValue();
        String task = txtTask.getText();
        Date date = Date.valueOf(datePicker.getValue());

        Maintenance maintenance = tableMain.getSelectionModel().getSelectedItem();
        int selected = tableMain.getSelectionModel().getSelectedIndex();
        maintenance.setId(Integer.parseInt(id));
        maintenance.setLaboratorium(laboratorium);
        maintenance.setTask(task);
        maintenance.setDate(date);
        try {
            int result = maintenanceDaoImpl.updateData(maintenance);
            if (result == 1) {
                maintenanceList.remove(selected);
                maintenanceList.clear();
                maintenanceList.addAll(maintenanceDaoImpl.fetchList(thisUser));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnDeleteOnAction(ActionEvent event) {
       
    }

    @FXML
    private void tableOnMouseClicked(MouseEvent event) {
        Maintenance selectedItems = tableMain.getSelectionModel().getSelectedItem();
        txtId.setText(String.valueOf(selectedItems.getId()));
        txtTask.setText(selectedItems.getTask());
        cmbLaboratorium.setValue(selectedItems.getLaboratorium());
        btnAdd.setDisable(true);
        btnUpdate.setDisable(false);
        btnDelete.setDisable(false);
    }

}
