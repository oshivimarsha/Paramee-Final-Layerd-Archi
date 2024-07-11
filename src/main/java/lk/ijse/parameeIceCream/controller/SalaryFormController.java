package lk.ijse.parameeIceCream.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.parameeIceCream.bo.BOFactory;
import lk.ijse.parameeIceCream.bo.custom.SalaryBO;
import lk.ijse.parameeIceCream.db.DbConnection;
import lk.ijse.parameeIceCream.dto.EmployeeDTO;
import lk.ijse.parameeIceCream.dto.SalaryDTO;
import lk.ijse.parameeIceCream.entity.Salary;
import lk.ijse.parameeIceCream.entity.tm.SalaryTm;
import lk.ijse.parameeIceCream.util.Regex;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SalaryFormController {

    public AnchorPane rootNode;

    public TextField txtId;

    public TextField txtName;
    public TextField txtBasicSalary;
    public TextField txtAllowances;
    public TextField txtGrossSalary;
    public TextField txtAdvance;
    public TextField txtNetPayable;
    public TextField txtNic;
    public TableView<SalaryTm> tblSalaryCart;

    public TableColumn<?, ?> colId;

    public TableColumn<?, ?> colName;
    public TableColumn<?, ?> colNic;
    public TableColumn<?, ?> colBasicSalary;
    public TableColumn<?, ?> colAllowances;
    public TableColumn<?, ?> colGrossSalary;
    public TableColumn<?, ?> colAdvance;
    public TableColumn<?, ?> colNetPayable;

    public TextField txtSearchHere;

    public Image image;
    public ImageView imageView;
    public JFXButton btnBack;
    public JFXButton btnReport;
    public JFXButton btnSave;

    SalaryBO salaryBO = (SalaryBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.SALARYBO);

    public void initialize() {
        setCellValueFactory();
        loadAllEmployees();
        getCurrentId();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colBasicSalary.setCellValueFactory(new PropertyValueFactory<>("basicSalary"));
        colAllowances.setCellValueFactory(new PropertyValueFactory<>("allowences"));
        colGrossSalary.setCellValueFactory(new PropertyValueFactory<>("grossSal"));
        colAdvance.setCellValueFactory(new PropertyValueFactory<>("advance"));
        colNetPayable.setCellValueFactory(new PropertyValueFactory<>("netPay"));

    }

    private void loadAllEmployees() {
        ObservableList<SalaryTm> obList = FXCollections.observableArrayList();

        try {
            List<SalaryDTO> salaryList = salaryBO.getAllSalary();
            EmployeeDTO employee;
            List<Double> netGrossSal;
            SalaryTm tm;
            for (SalaryDTO salary : salaryList) {
                employee = salaryBO.searchEmployeeById(salary.getId());
                netGrossSal = salaryBO.getSalaryDetail(employee.getId());


                tm = new SalaryTm(
                        salary.getId(),
                        employee.getName(),
                        employee.getNic(),
                        salary.getBasicSalary(),
                        salary.getAllowences(),
                        netGrossSal.get(0),
                        salary.getAdvance(),
                        netGrossSal.get(1)
                );

                obList.add(tm);
            }
            tblSalaryCart.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    public void btnSaveOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        double basicSalary = Double.parseDouble(txtBasicSalary.getText());
        double allowences = Double.parseDouble((txtAllowances.getText()));
        double advance = Double.parseDouble(txtAdvance.getText());

        if (isValied()) {
            SalaryDTO salary = new SalaryDTO(id, basicSalary, allowences, advance);

            try {
                boolean isSaved = salaryBO.saveSalary(salary);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Salary saved!").show();
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
        txtNic.setText("");
        txtBasicSalary.setText("");
        txtAllowances.setText("");
        txtAdvance.setText("");

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtId.getText();

        double basicSalary = Double.parseDouble(txtBasicSalary.getText());
        double allowences = Double.parseDouble((txtAllowances.getText()));
        double advance = Double.parseDouble(txtAdvance.getText());

        SalaryDTO salary = new SalaryDTO(id, basicSalary, allowences, advance);

        boolean isUpdated = salaryBO.updateSalary(salary);
        if(isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "Salary updated!").show();
            clearFields();
            initialize();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtId.getText();

        boolean isDeleted = salaryBO.deleteSalary(id);
        if(isDeleted) {
            new Alert(Alert.AlertType.CONFIRMATION, "Salary deleted!").show();
            clearFields();
            initialize();
        }
    }

    public void btnClearOnAction(ActionEvent actionEvent) throws SQLException {
        clearFields();
    }

    public void txtIdOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        try {
            EmployeeDTO employee = salaryBO.searchEmployeeById(id);

            txtName.setText(employee.getName());
            txtNic.setText(employee.getNic());
            Image image = new Image(employee.getPath());
            imageView.setImage(image);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        txtName.requestFocus();
    }

    private String getCurrentId() {
        String nextId = "";

        try {

            nextId = salaryBO.generateNewID();
            txtId.setText(nextId);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return nextId;
    }

    public void txtNameOnAction(ActionEvent actionEvent) {
        txtNic.requestFocus();
    }

    public void txtBasicSalaryOnAction(ActionEvent actionEvent) {
        txtAllowances.requestFocus();
    }

    public void txtAllowencesOnAction(ActionEvent actionEvent) {
        txtAdvance.requestFocus();
    }

    public void txtAdvanceOnAction(ActionEvent actionEvent) {

    }

    public void txtNicOnAction(ActionEvent actionEvent) {
        txtBasicSalary.requestFocus();
    }

    public void txtSearchHereOnAction(ActionEvent actionEvent) {
        btnSave.requestFocus();
    }

    public void btnReportPrintOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/reports/salary.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

        Map<String,Object> data = new HashMap<>();
        data.put("id",txtId.getText());


        JasperPrint jasperPrint =
                JasperFillManager.fillReport(jasperReport, data, DbConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane employeePane = FXMLLoader.load(this.getClass().getResource("/view/employee_form.fxml"));
        rootNode.getChildren().clear();
        rootNode.getChildren().add(employeePane);
    }

    public void txtIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.EID, txtId);
    }

    public void txtNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.NAME, txtName);
    }

    public void txtSalaryOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.PRICE, txtBasicSalary);
    }

    public void txtAllowencesOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.PRICE, txtAllowances);
    }

    public void txtAdvance(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.PRICE, txtAdvance);
    }

    public void txtNicOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.NIC, txtNic);
    }

    public boolean isValied(){
        if (!Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.EID, txtId)) return false;
        if (!Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.NAME, txtName)) return false;
        if (!Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.NIC, txtNic)) return false;
        if (!Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.PRICE, txtBasicSalary)) return false;
        if (!Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.PRICE, txtAllowances)) return false;
        if (!Regex.setTextColor(lk.ijse.parameeIceCream.util.TextField.PRICE, txtAdvance)) return false;
        return true;
    }
}
