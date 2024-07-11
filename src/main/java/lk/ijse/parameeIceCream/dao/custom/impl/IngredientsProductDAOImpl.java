package lk.ijse.parameeIceCream.dao.custom.impl;

import lk.ijse.parameeIceCream.dao.SQLUtill;
import lk.ijse.parameeIceCream.dao.custom.IngredientsProductDAO;
import lk.ijse.parameeIceCream.db.DbConnection;
import lk.ijse.parameeIceCream.entity.IngredientsProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientsProductDAOImpl implements IngredientsProductDAO {
    @Override
    public ArrayList<IngredientsProduct> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(IngredientsProduct io) throws SQLException, ClassNotFoundException {
        boolean result = SQLUtill.execute( "INSERT INTO ingredientProductDetail VALUES(?, ?, ?, ?)", io.getIngredientId(), io.getProductId(), io.getQty(), io.getUnitPrice());
        return result;
    }

    @Override
    public boolean update(IngredientsProduct io) throws SQLException, ClassNotFoundException {
        boolean result = SQLUtill.execute("UPDATE ingredientProductDetail SET productId = ?, qty = ?, unitPrice = ? WHERE ingredientId = ?", io.getProductId(), io.getQty(), io.getUnitPrice(), io.getIngredientId());
        return result;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public IngredientsProduct search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(List<IngredientsProduct> ipList) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(List<IngredientsProduct> ipList) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<IngredientsProduct> searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM ingredientProductDetail WHERE ingredientId = ?", id);

        List<IngredientsProduct> product1 = new ArrayList<>();

        if (resultSet.next()) {
            String ingId = resultSet.getString(1);
            String pId = resultSet.getString(2);
            int qty = resultSet.getInt(3);
            double unitPrice = resultSet.getDouble(4);

            IngredientsProduct product = new IngredientsProduct(ingId, pId, qty, unitPrice);

            product1.add(product);
        }

        return product1;
    }
}
