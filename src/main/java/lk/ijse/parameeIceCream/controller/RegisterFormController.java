package lk.ijse.parameeIceCream.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.parameeIceCream.bo.BOFactory;
import lk.ijse.parameeIceCream.bo.custom.LoginBO;
import lk.ijse.parameeIceCream.util.Regex;

import java.io.IOException;

public class RegisterFormController {
    public AnchorPane rootNode;
    public TextField txtEmail;
    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPw;

    LoginBO userBO = (LoginBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.LOGINBO);

    public void btnRegisterOnAction(ActionEvent actionEvent) {
        String name = txtName.getText();
        String password = txtPw.getText();
        String email = txtEmail.getText();

        if (isValid()) {
            try {
                boolean isSaved = userBO.saveUser(name, password, email);
                if(isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "user saved!").show();
                    navigateLogin();
                }
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "Wrong Input!").show();
        }
    }

    public void navigateLogin() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login Form");
    }

    public void txtPasswordOnAction(ActionEvent actionEvent) {
        txtEmail.requestFocus();
    }

    public void txtUserNameOnAction(ActionEvent actionEvent) {
        txtPw.requestFocus();
    }

    public void txtPasswordOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.PASSWORD, txtPw);
    }

    public void txtEmailOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.EMAIL, txtEmail);
    }

    public void txtNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.NAME, txtName);
    }

    public boolean isValid() {
        if (!Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.PASSWORD, txtPw)) return false;
        if (!Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.EMAIL, txtEmail)) return false;
        if (!Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.NAME, txtName)) return false;
        return true;
    }
}