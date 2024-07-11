package lk.ijse.parameeIceCream.controller;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.parameeIceCream.bo.BOFactory;
import lk.ijse.parameeIceCream.bo.custom.DashboardBO;
import lk.ijse.parameeIceCream.bo.custom.impl.Dashboard;
import lk.ijse.parameeIceCream.db.DbConnection;
import lk.ijse.parameeIceCream.dto.CustomDTO;
import lk.ijse.parameeIceCream.dto.ProductDTO;
import lk.ijse.parameeIceCream.entity.Product;
import lk.ijse.parameeIceCream.entity.tm.DashboardTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class DashboardFormController {

    public Label lblCustomerCount;
    @FXML
    public AnchorPane rootNode;
    public Label lblEmployeeCount;
    public LineChart<?, ?> lineChart;
    public PieChart pieChart;
    public Label lblIncomeCount;
    public Label lblOrderCount;
    public Label lblOrderDate;
    public Label lblOrderTime;
    public Label lblProductCount;

    private int customerCount;
    private int employeeCount;
    private int orderCount;

    private int productCount;

    public int orderList;

    DashboardBO dashboardBO = (DashboardBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.DASHBOARDBO);

    public void initialize() {
        try {
            customerCount = getCustomerCount();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        setCustomerCount(customerCount);



        try {
            employeeCount = getEmployeeCount();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        setEmployeeCount(employeeCount);



        try {
            orderCount = getOrderCount();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        setOrderCount(orderCount);


        try {
            productCount = getProductCount();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        serProductCount(productCount);


        lineChart();
        try {
            pieChartConnect();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        setDate();
        setTime();
    }

    public void lineChart(){
        try {
            XYChart.Series series1 = dashboardBO.getlinChart();
            lineChart.getData().addAll(series1);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    private void setDate() {
        LocalDate now = LocalDate.now();
        lblOrderDate.setText(String.valueOf(now));
    }

    private void setTime() {
        LocalTime now = LocalTime.now();
        lblOrderTime.setText(String.valueOf(now));
    }

    private void setCustomerCount(int customerCount) {
        lblCustomerCount.setText(String.valueOf(customerCount));
    }

    private int getCustomerCount() throws SQLException, ClassNotFoundException {
        return dashboardBO.getCustomerCount();
    }

    private void setEmployeeCount(int employeeCount) {
        lblEmployeeCount.setText(String.valueOf(employeeCount));
    }

    private int getEmployeeCount() throws SQLException, ClassNotFoundException {
        return dashboardBO.getEmployeeCount();
    }

    private void serProductCount(int employeeCount) {
        lblProductCount.setText(String.valueOf(employeeCount));
    }

    private int getProductCount() throws SQLException, ClassNotFoundException {
        return dashboardBO.getProductCount();
    }

    private int getOrderCount() throws SQLException, ClassNotFoundException {
        return 6;//dashboardBO.getOrderCount();
    }

    private void setOrderCount(int orderCount) {
        lblOrderCount.setText(String.valueOf(orderCount));
    }

    public void pieChartConnect() throws SQLException,ClassNotFoundException {
        List<DashboardTm> productList = Dashboard.getMostProduct();
        ProductDTO product;
        for (DashboardTm sellProduct : productList) {
            product = dashboardBO.searchProduct(sellProduct.getProductId());

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data(product.getName(), sellProduct.getQty())
            );
            pieChartData.forEach(data ->
                    data.nameProperty().bind(
                            Bindings.concat(
                                    data.getName(), " amount: ", data.pieValueProperty()
                            )
                    )
            );

            pieChart.getData().addAll(pieChartData);
        }
        /*List<CustomDTO> productList = dashboardBO.getMostProduct();//DashboardRepo.getMostProduct();
        ProductDTO product;
        for (CustomDTO sellProduct : productList) {
            product = dashboardBO.searchProduct(sellProduct.getProductId());//ProductRepo.searchById(sellProduct.getProductId());

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data(product.getName(), sellProduct.getSumQty())
            );
            pieChartData.forEach(data ->
                    data.nameProperty().bind(
                            Bindings.concat(
                                    data.getName(), " amount: ", data.pieValueProperty()
                            )
                    )
            );

            pieChart.getData().addAll(pieChartData);
        }*/
    }
}
