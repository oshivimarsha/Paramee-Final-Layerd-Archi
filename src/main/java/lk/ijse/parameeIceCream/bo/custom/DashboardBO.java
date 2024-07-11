package lk.ijse.parameeIceCream.bo.custom;

import javafx.scene.chart.XYChart;
import lk.ijse.parameeIceCream.bo.SuperBO;
import lk.ijse.parameeIceCream.dto.CustomDTO;
import lk.ijse.parameeIceCream.dto.ProductDTO;
import lk.ijse.parameeIceCream.entity.Custom;
import lk.ijse.parameeIceCream.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface DashboardBO extends SuperBO {
    int getCustomerCount() throws SQLException, ClassNotFoundException;
    int getEmployeeCount() throws SQLException, ClassNotFoundException;
    int getProductCount() throws SQLException, ClassNotFoundException;
    int getOrderCount() throws SQLException, ClassNotFoundException;

    XYChart.Series getlinChart() throws SQLException, ClassNotFoundException;

    List<CustomDTO> getMostProduct() throws SQLException,ClassNotFoundException;

    public ProductDTO searchProduct(String id) throws SQLException, ClassNotFoundException;
}
