package lk.ijse.parameeIceCream.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import lk.ijse.parameeIceCream.bo.BOFactory;
import lk.ijse.parameeIceCream.bo.custom.IngredientsBO;
import lk.ijse.parameeIceCream.dto.IngredientDTO;
import lk.ijse.parameeIceCream.dto.SupplierDTO;
import lk.ijse.parameeIceCream.entity.tm.IngredientTm;
import lk.ijse.parameeIceCream.util.Regex;
import org.controlsfx.control.textfield.TextFields;

import java.sql.SQLException;
import java.util.List;

public class IngredientsFormController {
    public TableView<IngredientTm> tblIngredientsCart;
    public TableColumn<?, ?> colId;
    public TableColumn<?, ?> colName;
    public TableColumn<?, ?> colQtyInStock;
    public TableColumn<?, ?> colUnitOfMeasure;
    public TableColumn<?, ?> colUnitPrice;
    public TableColumn<?, ?> colPrice;
    public TableColumn<?, ?> colSupplierId;

    public TextField txtId;

    public TextField txtName;
    public TextField txtQtyInStock;
    public TextField txtUnitOfMeasure;
    public TextField txtPrice;
    public TextField txtUnitPrice;
    public JFXComboBox<String> cmbSupplierId;
    public Label lblSupplierName;
    public TextField txtSearchHere;

    IngredientsBO ingredientsBO = (IngredientsBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.INGREDIENTPRODUCTBO);

    public void initialize() {
        setCellValueFactory();
        loadAllEmployees();
        getSupplierId();
        getIngredientsNames();
        getCurrentId();
    }

    private void getIngredientsNames() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> nameList = ingredientsBO.getIngredientNames();

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

    private void loadAllEmployees() {
        ObservableList<IngredientTm> obList = FXCollections.observableArrayList();

        try {
            List<IngredientDTO> ingredientList = ingredientsBO.getAllIngredient();
            for (IngredientDTO ingredient : ingredientList) {
                IngredientTm tm = new IngredientTm(
                        ingredient.getId(),
                        ingredient.getName(),
                        ingredient.getQtyInStock(),
                        ingredient.getUnitOfMeasure(),
                        ingredient.getUnitPrice(),
                        ingredient.getPrice(),
                        ingredient.getSupplierId()
                        //employee.getDepartmentName()
                );

                obList.add(tm);
            }

            tblIngredientsCart.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colQtyInStock.setCellValueFactory(new PropertyValueFactory<>("qtyInStock"));
        colUnitOfMeasure.setCellValueFactory(new PropertyValueFactory<>("unitOfMeasure"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("SupplierId"));
    }

    public void txtSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String name = txtName.getText();

        IngredientDTO ingredient = ingredientsBO.searchIngredient(name);
        if (ingredient != null) {
            txtId.setText(ingredient.getId());
            txtName.setText(ingredient.getName());
            txtQtyInStock.setText(ingredient.getQtyInStock());
            txtUnitOfMeasure.setText(ingredient.getUnitOfMeasure());
            txtUnitPrice.setText(String.valueOf(ingredient.getUnitPrice()));
            txtPrice.setText(String.valueOf(ingredient.getPrice()));
            cmbSupplierId.setValue(ingredient.getSupplierId());
            // lblDepartmentName.setText(employee.getDepartmentName());

        } else {
            new Alert(Alert.AlertType.INFORMATION, "employee not found!").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String qtyInStock = txtQtyInStock.getText();
        String unitOfMeasure = txtUnitOfMeasure.getText();
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        double price = Double.parseDouble(txtPrice.getText());
        String supplierId = cmbSupplierId.getValue();
        // String departmentName = lblDepartmentName.getText();

        if (isValied()) {
            IngredientDTO ingredient = new IngredientDTO(id, name, qtyInStock, unitOfMeasure, unitPrice, price, supplierId);

            try {
                boolean isSaved = ingredientsBO.saveIngredient(ingredient);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "ingredient saved!").show();
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

    private void clearFields() {
        txtId.setText("");
        txtName.setText("");
        txtQtyInStock.setText("");
        txtUnitOfMeasure.setText("");
        txtUnitPrice.setText("");
        txtPrice.setText("");
        cmbSupplierId.setValue("");
        txtSearchHere.setText("");
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtId.getText();
        String name = txtName.getText();
        String qtyInStock = txtQtyInStock.getText();
        String unitOfMeasure = txtUnitOfMeasure.getText();
        double unitPrice = Double.parseDouble(((txtUnitPrice.getText())));
        double price = Double.parseDouble(txtPrice.getText());
        String supplierId = cmbSupplierId.getValue();
        //String departmentName = lblDepartmentName.getText();

        IngredientDTO ingredient = new IngredientDTO(id, name, qtyInStock, unitOfMeasure, unitPrice, price, supplierId);

        boolean isUpdated = ingredientsBO.updateIngredient(ingredient);
        if(isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "ingredient updated!").show();
            clearFields();
            initialize();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String name = txtName.getText();

        boolean isDeleted = ingredientsBO.deleteIngredient(name);
        if(isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "ingredient deleted!").show();
            clearFields();
            initialize();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void getSupplierId() {
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> supList = ingredientsBO.getSupplierIds();

            for(String supplier : supList) {
                obList.add(supplier);
            }

            cmbSupplierId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void cmbSupplierIdOnAction(ActionEvent actionEvent) {
        String id = cmbSupplierId.getValue();
        try {
            SupplierDTO supplier = ingredientsBO.searchSupplier(id);

            lblSupplierName.setText(supplier.getName());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void txtSearchHereOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String name = txtSearchHere.getText();

        IngredientDTO ingredient = ingredientsBO.searchIngredient(name);
        if (ingredient != null) {
            txtId.setText(ingredient.getId());
            txtName.setText(ingredient.getName());
            txtQtyInStock.setText(ingredient.getQtyInStock());
            txtUnitOfMeasure.setText(ingredient.getUnitOfMeasure());
            txtUnitPrice.setText(String.valueOf(ingredient.getUnitPrice()));
            txtPrice.setText(String.valueOf(ingredient.getPrice()));
            cmbSupplierId.setValue(ingredient.getSupplierId());
        } else {
            new Alert(Alert.AlertType.INFORMATION, "customer not found!").show();
        }
    }

    private String getCurrentId() {
        String nextId = "";

        try {

            nextId = ingredientsBO.generateNewID();
            txtId.setText(nextId);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return nextId;
    }

    public void txtIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.IID, txtId);
    }

    public void txtNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.NAME, txtName);
    }

    public void txtQtyOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.QTY, txtQtyInStock);
    }

    public void txtPriceOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.PRICE, txtPrice);
    }

    public void txtUnitPriceOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.PRICE, txtUnitPrice);
    }

    public boolean isValied() {
        if (!Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.IID, txtId)) return false;
        if (!Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.NAME, txtName)) return false;
        if (!Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.QTY, txtQtyInStock)) return false;
        if (!Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.PRICE, txtPrice)) return false;
        if (!Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.PRICE, txtUnitPrice)) return false;
        return true;
    }
}
