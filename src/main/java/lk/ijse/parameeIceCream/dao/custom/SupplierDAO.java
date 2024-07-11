package lk.ijse.parameeIceCream.dao.custom;

import lk.ijse.parameeIceCream.dao.CrudDAO;
import lk.ijse.parameeIceCream.entity.Supplier;

import java.sql.SQLException;
import java.util.List;

public interface SupplierDAO extends CrudDAO<Supplier> {
    Supplier searchByTel(String tel) throws SQLException, ClassNotFoundException;
    List<String> getIds() throws SQLException, ClassNotFoundException;
    List<String> getTels() throws SQLException, ClassNotFoundException;
}
