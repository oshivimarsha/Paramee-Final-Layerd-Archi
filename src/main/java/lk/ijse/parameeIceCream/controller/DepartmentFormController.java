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
import lk.ijse.parameeIceCream.bo.custom.DepartmentBO;
import lk.ijse.parameeIceCream.dto.DepartmentDTO;
import lk.ijse.parameeIceCream.entity.tm.DepartmentTm;
import lk.ijse.parameeIceCream.util.Regex;
import org.controlsfx.control.textfield.TextFields;

import java.sql.SQLException;
import java.util.List;

public class DepartmentFormController {

    public TextField txtId;

    public TextField txtName;
    public TextField txtDescription;
    public TableView<DepartmentTm> tblDepartmentCart;
    public TableColumn<?, ?> colId;
    public TableColumn<?, ?> colName;
    public TableColumn<?, ?> colDescription;
    public TextField txtSearchHere;
    public TextField txtNumOfEmployee;
    public TableColumn<?, ?> colNumOfEmp;

    DepartmentBO departmentBO = (DepartmentBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.DEPARTMENTBO);

    public void initialize() {
        setCellValueFactory();
        loadAllDepartment();
        getdepartmentName();
        getCurrentId();
    }

    private void getdepartmentName() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> nameList = departmentBO.getDepartmentName();

            for(String name : nameList) {
                obList.add(name);
            }
            TextFields.bindAutoCompletion(txtSearchHere, obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadAllDepartment() {
        ObservableList<DepartmentTm> obList = FXCollections.observableArrayList();

        try {
            List<DepartmentDTO> departmentList = departmentBO.getAllDepartment();
            for (DepartmentDTO department : departmentList) {
                DepartmentTm tm = new DepartmentTm(
                        department.getId(),
                        department.getName(),
                        department.getDescription(),
                        department.getNumOfEmp()
                );

                obList.add(tm);
            }

            tblDepartmentCart.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colNumOfEmp.setCellValueFactory(new PropertyValueFactory<>("numOfEmp"));
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String description = txtDescription.getText();
        int emp = Integer.parseInt(txtNumOfEmployee.getText());

        if (isValied()) {
            DepartmentDTO department = new DepartmentDTO(id, name, description, emp);

            try {
                boolean isSaved = departmentBO.saveDepartment(department);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Department saved!").show();
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
        String description = txtDescription.getText();
        int emp = Integer.parseInt(txtNumOfEmployee.getText());

        DepartmentDTO department = new DepartmentDTO(id, name, description, emp);

        boolean isUpdated = departmentBO.updateDepartment(department);
        if(isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "Department updated!").show();
            clearFields();
            initialize();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String name = txtName.getText();

        boolean isDeleted = departmentBO.deleteDepartment(name);
        if(isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "Department deleted!").show();
            clearFields();
            initialize();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtDescription.setText("");
        txtNumOfEmployee.setText("");
        txtSearchHere.setText("");
    }

    public void txtSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String name = txtName.getText();

        DepartmentDTO department = departmentBO.searchDepartment(name);
        if (department != null) {
            txtId.setText(department.getId());
            txtName.setText(department.getName());
            txtDescription.setText(department.getDescription());
            txtNumOfEmployee.setText(String.valueOf(department.getNumOfEmp()));
        } else {
            new Alert(Alert.AlertType.INFORMATION, "department not found!").show();
        }
    }


    public void txtSearchHereOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String name = txtSearchHere.getText();

        DepartmentDTO department = departmentBO.searchDepartment(name);
        if (department != null) {
            txtId.setText(department.getId());
            txtName.setText(department.getName());
            txtDescription.setText(department.getDescription());
            txtNumOfEmployee.setText(String.valueOf(department.getNumOfEmp()));
        } else {
            new Alert(Alert.AlertType.INFORMATION, "department not found!").show();
        }
    }

    private String getCurrentId() {
        String nextId = "";

        try {

            nextId = departmentBO.generateNewID();
            txtId.setText(nextId);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return nextId;
    }

    public void txtIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.DID, txtId);
    }

    public void txtNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.NAME, txtName);
    }

    public boolean isValied() {
        if (!Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.DID, txtId)) return false;
        if (!Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.NAME, txtName)) return false;
        return true;
    }
}
