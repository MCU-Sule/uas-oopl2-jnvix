/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jojo.controller;

import com.jojo.Main;
import com.jojo.dao.UserDaoImpl;
import com.jojo.entity.Member;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author 1672012
 */
public class LoginController implements Initializable {

    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPassword;

    private UserDaoImpl daoImpl;
    private Stage mainStage;
    private User user;
    @FXML
    private BorderPane roots;

    public User getUser() {
        return user;
    }
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        daoImpl = new UserDaoImpl();
        user = new User();

    }

    @FXML
    private void btnLoginOnAction(ActionEvent event) throws IOException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        user.setUsername(username);
        user.setPassword(password);
        try {
            user = daoImpl.fetchUser(user);
            if (user.getName()!= null) {
                
                    FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("view/mainView.fxml"));
                    Parent root = fxmlLoader.load();
                    MainController controller = fxmlLoader.getController();
                    controller.setLoginController(this);
                    Scene scene = new Scene(root);
                    mainStage = new Stage();
                    mainStage.setTitle("Maintenance Management");
                    mainStage.setScene(scene);
                    mainStage.initOwner(roots.getScene().getWindow());
                    mainStage.initModality(Modality.WINDOW_MODAL);
                    mainStage.show();
                
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Username dan password tidak sesuai");
                alert.showAndWait();
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
