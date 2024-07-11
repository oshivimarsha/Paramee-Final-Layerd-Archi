package lk.ijse.parameeIceCream.dao.custom;

import lk.ijse.parameeIceCream.dao.CrudDAO;
import lk.ijse.parameeIceCream.entity.Custom;
import lk.ijse.parameeIceCream.entity.OrderDetail;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailDAO extends CrudDAO<OrderDetail> {
    boolean save(List<OrderDetail> odList) throws SQLException, ClassNotFoundException;
    List<Custom> getMostProduct() throws SQLException,ClassNotFoundException;
}
