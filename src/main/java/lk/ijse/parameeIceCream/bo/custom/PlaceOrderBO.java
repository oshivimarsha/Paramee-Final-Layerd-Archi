package lk.ijse.parameeIceCream.bo.custom;

import lk.ijse.parameeIceCream.bo.SuperBO;
import lk.ijse.parameeIceCream.dao.SQLUtill;
import lk.ijse.parameeIceCream.dto.CustomerDTO;
import lk.ijse.parameeIceCream.dto.OrderDTO;
import lk.ijse.parameeIceCream.dto.OrderDetailDTO;
import lk.ijse.parameeIceCream.dto.ProductDTO;
import lk.ijse.parameeIceCream.entity.Customer;
import lk.ijse.parameeIceCream.entity.PlaceOrder;
import lk.ijse.parameeIceCream.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PlaceOrderBO extends SuperBO {
    String generateNewOrderID() throws SQLException, ClassNotFoundException;

    String getLastOId() throws SQLException, ClassNotFoundException;
    double getNetTot(String oId) throws SQLException, ClassNotFoundException;

    boolean placeOrder(OrderDTO orderDTO, List<OrderDetailDTO> orderDetails) throws SQLException,ClassNotFoundException;

    List<String> getProductName() throws SQLException, ClassNotFoundException;

    ProductDTO searchByProductName(String name) throws SQLException, ClassNotFoundException;

    List<String> getCustomerTels() throws SQLException, ClassNotFoundException;
    CustomerDTO searchCustomerTel(String tel) throws SQLException, ClassNotFoundException;

    String getCurrentId() throws SQLException, ClassNotFoundException;
}
