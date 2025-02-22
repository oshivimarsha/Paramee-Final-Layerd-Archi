package lk.ijse.parameeIceCream.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.parameeIceCream.bo.BOFactory;
import lk.ijse.parameeIceCream.bo.custom.CustomerBO;
import lk.ijse.parameeIceCream.dto.CustomerDTO;
import lk.ijse.parameeIceCream.entity.tm.CustomerTm;
import lk.ijse.parameeIceCream.util.Regex;
import org.controlsfx.control.textfield.TextFields;

import java.sql.SQLException;
import java.util.List;

public class CustomerFormController {


    public TextField txtTel;
    public TextField txtEmail;
    public AnchorPane rootNode;

    public TextField txtId;

    public TextField txtName;
    public TextField txtNIC;
    public TextField txtAddress;

    public TableColumn<?, ?> colId;

    public TableColumn<?, ?> colName;
    public TableColumn<?, ?> colNic;
    public TableColumn<?, ?> colAddress;
    public TableColumn<?, ?> colTel;
    public TableColumn<?, ?> colEmail;
    public TableView<CustomerTm> tblCustomerCart;
    public TextField txtSearchHere;
    public JFXButton btnSave;
    CustomerBO customerBO = (CustomerBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.CUSTOMERBO);


    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
        getCustomerTels();
        getCurrentId();
    }

    private void getCustomerTels() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> telList = customerBO.getTelsCustomers();

            for(String tel : telList) {
                obList.add(tel);
            }
            TextFields.bindAutoCompletion(txtSearchHere, obList);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllCustomers() {
        ObservableList<CustomerTm> obList = FXCollections.observableArrayList();

        try {
            List<CustomerDTO> customerList = customerBO.getAllCustomers();
            for (CustomerDTO customer : customerList) {
                CustomerTm tm = new CustomerTm(
                        customer.getId(),
                        customer.getName(),
                        customer.getNic(),
                        customer.getAddress(),
                        customer.getEmail(),
                        customer.getTel()
                );

                obList.add(tm);
            }

            tblCustomerCart.setItems(obList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
    }


    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String nic = txtNIC.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String tel = txtTel.getText();

        if (isValied()) {
            CustomerDTO customer = new CustomerDTO(id, name, nic, address, email, tel);

            try {
                boolean isSaved = customerBO.saveCustomer(customer);//CustomerRepo.save(customer);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
                    clearFields();
                    initialize();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "Wrong Input!").show();
        }
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtNIC.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
        txtTel.setText("");
        txtSearchHere.setText("");
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException {
        String id = txtId.getText();
        String name = txtName.getText();
        String nic = txtNIC.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String tel = txtTel.getText();

        CustomerDTO customer = new CustomerDTO(id, name, nic, address, email, tel);

        boolean isUpdated = false;
        try {
            isUpdated = customerBO.updateCustomer(customer);//CustomerRepo.update(customer);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        if(isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
            clearFields();
            initialize();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException {
        String nic = txtNIC.getText();
        System.out.println(txtNIC.getText());
        try {
            boolean isDeleted = false;

            isDeleted = customerBO.deleteCustomer(nic);//CustomerRepo.delete(nic);

            if(isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "customer deleted!").show();
                clearFields();
                initialize();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws SQLException {
        txtAddress.requestFocus();
    }



    public void txtSearchHereOnAction(ActionEvent actionEvent) throws SQLException {
        String tel = txtSearchHere.getText();
        //String tell = txtSearchHere.getText();
        try {
            CustomerDTO customer = customerBO.searchCustomer(tel);//CustomerRepo.searchByTel(tel);
            // Department department = DepartmentRepo.searchById(employee.getDepartmentId());
            if (customer != null) {
                txtId.setText(customer.getId());
                txtName.setText(customer.getName());
                txtNIC.setText(customer.getNic());
                txtAddress.setText(customer.getAddress());
                txtEmail.setText(customer.getEmail());
                txtTel.setText(customer.getTel());

            } else {
                new Alert(Alert.AlertType.INFORMATION, "employee not found!").show();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getCurrentId() {
        String nextId = "";

        try {

            nextId = customerBO.generateNewID();
            txtId.setText(nextId);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return nextId;
    }

    public void txtIdOnAction(ActionEvent actionEvent) {
        txtName.requestFocus();
    }

    public void txtNameOnAction(ActionEvent actionEvent) {
        txtNIC.requestFocus();
    }

    public void txtAddressOnAction(ActionEvent actionEvent) {
        txtEmail.requestFocus();
    }


    public void txtEmailOnAction(ActionEvent actionEvent) {
        txtTel.requestFocus();
    }

    public void txtIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.CID, txtId);
    }

    public void txtNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.NAME, txtName);
    }

    public void txtNicOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.NIC, txtNIC);
    }

    public void txtAddressOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.ADDRESS, txtAddress);
    }

    public void txtTelOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.TEL, txtTel);
    }

    public void txtEmailOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.EMAIL, txtEmail);
    }

    public boolean isValied(){
        if (!Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.CID, txtId)) return false;
        if (!Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.NAME, txtName)) return false;
        if (!Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.NIC, txtNIC)) return false;
        if (!Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.ADDRESS, txtAddress)) return false;
        if (!Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.TEL, txtTel)) return false;
        if (!Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.EMAIL, txtEmail)) return false;
        return true;
    }
}
