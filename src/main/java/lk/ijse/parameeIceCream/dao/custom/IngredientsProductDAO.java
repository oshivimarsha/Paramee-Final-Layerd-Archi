package lk.ijse.parameeIceCream.dao.custom;

import lk.ijse.parameeIceCream.dao.CrudDAO;
import lk.ijse.parameeIceCream.entity.IngredientsProduct;

import java.sql.SQLException;
import java.util.List;

public interface IngredientsProductDAO extends CrudDAO<IngredientsProduct> {
    boolean save(List<IngredientsProduct> ipList) throws SQLException, ClassNotFoundException;
    boolean update(List<IngredientsProduct> ipList) throws SQLException, ClassNotFoundException;
    List<IngredientsProduct> searchById(String id) throws SQLException, ClassNotFoundException;
}
