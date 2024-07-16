package lk.ijse.parameeIceCream.dao.custom;

import lk.ijse.parameeIceCream.dao.CrudDAO;
import lk.ijse.parameeIceCream.entity.OrderDetail;
import lk.ijse.parameeIceCream.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO extends CrudDAO<Product> {
    Product searchByName(String name) throws SQLException, ClassNotFoundException;
    List<String> getName() throws SQLException, ClassNotFoundException;
    public boolean updateQ(List<OrderDetail> isList) throws SQLException,ClassNotFoundException;
    boolean updateQty(String Code, int qty) throws SQLException, ClassNotFoundException;
    int getProductCount() throws SQLException, ClassNotFoundException;
}
