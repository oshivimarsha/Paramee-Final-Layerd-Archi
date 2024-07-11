package lk.ijse.parameeIceCream.bo.custom.impl;

import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import lk.ijse.parameeIceCream.bo.custom.DashboardBO;
import lk.ijse.parameeIceCream.dao.DAOFactory;
import lk.ijse.parameeIceCream.dao.SQLUtill;
import lk.ijse.parameeIceCream.dao.custom.*;
import lk.ijse.parameeIceCream.dto.CustomDTO;
import lk.ijse.parameeIceCream.dto.ProductDTO;
import lk.ijse.parameeIceCream.entity.CreateProduct;
import lk.ijse.parameeIceCream.entity.Custom;
import lk.ijse.parameeIceCream.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DashboardBOImpl implements DashboardBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMERDAO);
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEEDAO);
    ProductDAO productDAO = (ProductDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PRODUCTDAO);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDERDAO);

    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILSDAO);

    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public int getCustomerCount() throws SQLException, ClassNotFoundException {
        return customerDAO.getCustomerCount();
    }

    @Override
    public int getEmployeeCount() throws SQLException, ClassNotFoundException {
        return employeeDAO.getEmployeeCount();
    }

    @Override
    public int getProductCount() throws SQLException, ClassNotFoundException {
        return productDAO.getProductCount();
    }

    @Override
    public int getOrderCount() throws SQLException, ClassNotFoundException {
        return orderDAO.getOrderCount();
    }

    @Override
    public XYChart.Series getlinChart() throws SQLException, ClassNotFoundException {
        XYChart.Series series1 = new XYChart.Series();  // represent a series of data points on the chart.
        series1.setName("Paramee Company");
        List<CustomDTO> dailyRevenueList = new ArrayList<>();/*DashboardRepo.getDateCount();*/
        try {
            List<Custom> customs = queryDAO.getlinChart();
            List<CustomDTO> customEntities = new ArrayList<>();
            for (Custom customEntity:customs) {
                customEntities.add(new CustomDTO(customEntity.getDate(),customEntity.getCount()));
            }
            for (CustomDTO customEntity:customEntities) {
                dailyRevenueList.add(new CustomDTO(customEntity.getDate(),customEntity.getCount()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        for (CustomDTO dailyRevenue: dailyRevenueList) {
            series1.getData().add(new XYChart.Data<>(dailyRevenue.getDate(),dailyRevenue.getCount()));  //xy chart class eke thiyana static innerclass ekak
        }
        return series1;
    }

    @Override
    public List<CustomDTO> getMostProduct() throws SQLException,ClassNotFoundException {
        List<Custom> custom = orderDetailDAO.getMostProduct();
        List<CustomDTO> customDTOS = new ArrayList<>();

        for (Custom custom1: custom){
            customDTOS.add(new CustomDTO(custom1.getItemId(),custom1.getOrderCount(),custom1.getSumQty()));
        }
        return customDTOS;
    }

    @Override
    public ProductDTO searchProduct(String id) throws SQLException, ClassNotFoundException {
        Product product = productDAO.search(id);
        ProductDTO productDTO = new ProductDTO(product.getId(), product.getName(), product.getCategory(),product.getDescription(),product.getQtyAvailable(),product.getUnitPrice(),product.getDepartmentId(),product.getPath());
        return productDTO;
    }
}
