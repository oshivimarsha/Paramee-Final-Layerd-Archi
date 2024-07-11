package lk.ijse.parameeIceCream.dao.custom;

import lk.ijse.parameeIceCream.dao.CrudDAO;
import lk.ijse.parameeIceCream.dto.IngredientDTO;
import lk.ijse.parameeIceCream.entity.Ingredient;
import lk.ijse.parameeIceCream.entity.IngredientsProduct;

import java.sql.SQLException;
import java.util.List;

public interface IngredientDAO extends CrudDAO<Ingredient> {
    boolean updateMin(List<IngredientsProduct> ipList) throws SQLException, ClassNotFoundException;
    boolean updatePlus(List<IngredientsProduct> ipList) throws SQLException, ClassNotFoundException;
    boolean updateQtyMin(String id, int qty) throws SQLException, ClassNotFoundException;
    boolean updateQtyPlus(String id, int qty) throws SQLException, ClassNotFoundException;
    List<String> getNames() throws SQLException,ClassNotFoundException;
    public List<IngredientDTO> searchById(String id) throws SQLException,ClassNotFoundException;
    List<String> getIds() throws SQLException, ClassNotFoundException;
}
