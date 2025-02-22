package lk.ijse.parameeIceCream.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.parameeIceCream.bo.BOFactory;
import lk.ijse.parameeIceCream.bo.custom.SupplierBO;
import lk.ijse.parameeIceCream.db.DbConnection;
import lk.ijse.parameeIceCream.dto.SupplierDTO;
import lk.ijse.parameeIceCream.entity.tm.SupplierTm;
import lk.ijse.parameeIceCream.util.Regex;
import org.controlsfx.control.textfield.TextFields;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SuppliersFormController {
    public TextField txtId;
    public TextField txtName;
    public TextField txtNIC;
    public TextField txtAddress;
    public TextField txtTel;
    public TextField txtEmail;
    public TableColumn<?, ?> colId;
    public TableColumn<?, ?> colName;
    public TableColumn<?, ?> colNic;
    public TableColumn<?, ?> colAddress;
    public TableColumn<?, ?> colTel;
    public TableColumn<?, ?> colEmail;
    public TableView<SupplierTm> tblSupplierCart;
    public TextField txtSearchHere;

    SupplierBO supplierBO = (SupplierBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.SUPPLIERBO);

    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
        getSupplierTels();
        getCurrentId();
    }

    private void getSupplierTels() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> telList = supplierBO.getSupplierTels();

            for(String tel : telList) {
                obList.add(tel);
            }
            TextFields.bindAutoCompletion(txtSearchHere, obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    private void loadAllCustomers() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
    }

    private void setCellValueFactory() {
        ObservableList<SupplierTm> obList = FXCollections.observableArrayList();

        try {
            List<SupplierDTO> supplierList = supplierBO.getAllSupplier();
            for (SupplierDTO supplier : supplierList) {
                SupplierTm tm = new SupplierTm(
                        supplier.getId(),
                        supplier.getName(),
                        supplier.getNic(),
                        supplier.getAddress(),
                        supplier.getEmail(),
                        supplier.getTel()
                );

                obList.add(tm);
            }

            tblSupplierCart.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
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

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException {
        String id = txtId.getText();
        String name = txtName.getText();
        String nic = txtNIC.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String tel = txtTel.getText();

        if (isValied()) {
            SupplierDTO supplier = new SupplierDTO(id, name, nic, address, email, tel);

            try {
                boolean isSaved = supplierBO.saveSupplier(supplier);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Supplier Saved!").show();
                    clearFields();
                    initialize();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            new Alert(Alert.AlertType.CONFIRMATION, "Wrong Input!").show();
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtId.getText();
        String name = txtName.getText();
        String nic = txtNIC.getText();
        String address = txtAddress.getText();
        String email = txtEmail.getText();
        String tel = txtTel.getText();

        SupplierDTO supplier = new SupplierDTO(id, name, nic, address, email, tel);

        boolean isUpdated = supplierBO.updateSupplier(supplier);
        if(isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "Supplier Updated!").show();
            clearFields();
            initialize();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String tel = txtTel.getText();

        boolean isDeleted = supplierBO.deleteSupplier(tel);
        if(isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "Supplier Deleted!").show();
            clearFields();
            initialize();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    public static List<String> getIds() throws SQLException {
        String sql = "SELECT id FROM supplier";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }

    public void txtSearchHereOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String tel = txtSearchHere.getText();

        SupplierDTO supplier = supplierBO.searchSupplierByTel(tel);
        if (supplier != null) {
            txtId.setText(supplier.getId());
            txtName.setText(supplier.getName());
            txtNIC.setText(supplier.getNic());
            txtAddress.setText(supplier.getAddress());
            txtEmail.setText(supplier.getEmail());
            txtTel.setText(supplier.getTel());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
        }
    }

    private String getCurrentId() {
        String nextId = "";

        try {

            nextId = supplierBO.generateNewID();
            txtId.setText(nextId);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return nextId;
    }

    public void txtIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.SID, txtId);
    }

    public void txtNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.NAME, txtName);
    }

    public void txtNicOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.NIC, txtNIC);
    }

    public void txtEmailOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.EMAIL, txtEmail);
    }

    public void txtTelOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.TEL, txtTel);
    }

    public void txtAddressOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.ADDRESS, txtAddress);
    }

    public boolean isValied(){
        if (!Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.SID, txtId)) return false;
        if (!Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.NAME, txtName)) return false;
        if (!Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.NIC, txtNIC)) return false;
        if (!Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.ADDRESS, txtAddress)) return false;
        if (!Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.TEL, txtTel)) return false;
        if (!Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.EMAIL, txtEmail)) return false;
        return true;
    }
}
