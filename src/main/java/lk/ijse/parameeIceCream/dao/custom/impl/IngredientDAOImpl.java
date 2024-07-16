package lk.ijse.parameeIceCream.dao.custom.impl;

import lk.ijse.parameeIceCream.dao.SQLUtill;
import lk.ijse.parameeIceCream.dao.custom.IngredientDAO;
import lk.ijse.parameeIceCream.dto.IngredientDTO;
import lk.ijse.parameeIceCream.entity.Ingredient;
import lk.ijse.parameeIceCream.entity.IngredientsProduct;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientDAOImpl implements IngredientDAO {
    @Override
    public ArrayList<Ingredient> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM ingredient");

        ArrayList<Ingredient> ingList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String iName = resultSet.getString(2);
            String qtyInStock = resultSet.getString(3);
            String unitOfMeasure = resultSet.getString(4);
            double unitPrice = Double.parseDouble(resultSet.getString(5));
            double price = Double.parseDouble(resultSet.getString(6));
            String supplierId = resultSet.getString(7);
            // String departmentName = resultSet.getString(12);

            Ingredient ingredient = new Ingredient(id, iName, qtyInStock, unitOfMeasure, unitPrice, price, supplierId);
            ingList.add(ingredient);
        }
        return ingList;
    }

    @Override
    public boolean save(Ingredient ingredient) throws SQLException, ClassNotFoundException {
        boolean result = SQLUtill.execute("INSERT INTO ingredient VALUES(?, ?, ?, ?, ?, ?, ?)", ingredient.getId(), ingredient.getName(), ingredient.getQtyInStock(), ingredient.getUnitOfMeasure(), ingredient.getUnitPrice(), ingredient.getPrice(), ingredient.getSupplierId());
        return result;
    }

    @Override
    public boolean update(Ingredient ingredient) throws SQLException, ClassNotFoundException {
        boolean result = SQLUtill.execute("UPDATE ingredient SET ingredientId = ?, qtyInStock = ?, unitOfMeasure = ?, unitPrice = ?, price = ?, supplierId = ? WHERE name = ?", ingredient.getId(), ingredient.getQtyInStock(), ingredient.getUnitOfMeasure(), ingredient.getUnitPrice(), ingredient.getPrice(), ingredient.getSupplierId(), ingredient.getName());
        return result;
    }

    @Override
    public boolean updateMin(List<IngredientsProduct> ipList) throws SQLException, ClassNotFoundException {
        for (IngredientsProduct ip : ipList) {
            System.out.println("qtyUpdate Item");
            boolean isUpdateQty = updateQtyMin(ip.getIngredientId(), ip.getQty());
            System.out.println("isupdateQty - "+isUpdateQty);
            if(!isUpdateQty) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updatePlus(List<IngredientsProduct> ipList) throws SQLException, ClassNotFoundException {
        for (IngredientsProduct ip : ipList) {
            System.out.println("qtyUpdate Item");
            boolean isUpdateQty = updateQtyPlus(ip.getIngredientId(), ip.getQty());
            if(!isUpdateQty) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateQtyMin(String id, int qty) throws SQLException , ClassNotFoundException{
        boolean result = SQLUtill.execute("UPDATE ingredient SET qtyInStock = qtyInStock - ? WHERE ingredientId = ?", qty,id);
        return result;
    }

    @Override
    public boolean updateQtyPlus(String id, int qty) throws SQLException, ClassNotFoundException {
        boolean result = SQLUtill.execute("UPDATE ingredient SET qtyInStock = qtyInStock + ? WHERE ingredientId = ?", qty,id);
        return result;
    }

    @Override
    public List<String> getNames() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT name FROM ingredient");

        List<String> nameList = new ArrayList<>();

        while (resultSet.next()) {
            String name = resultSet.getString(1);
            nameList.add(name);
        }
        return nameList;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT ingredientId FROM ingredient WHERE ingredientId=?",id);
        return rst.next();
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT ingredientId FROM ingredient ORDER BY CAST(SUBSTRING(ingredientId, 2) AS UNSIGNED) DESC LIMIT 1");

        if (rst.next()) {
            String id = rst.getString(1);
            String[] split = id.split("I");
            int idNum = Integer.parseInt(split[1]);

            if(idNum >= 1){
                return "I" + 0 + 0 + ++idNum;
            }else if(idNum >= 9){
                return "I" + 0 + ++idNum;
            } else if(idNum >= 99){
                return "I" + ++idNum;
            }
        }
        return "I001";
    }

    @Override
    public boolean delete(String name) throws SQLException, ClassNotFoundException {
        boolean result = SQLUtill.execute("DELETE FROM ingredient WHERE name = ?");
        return result;
    }

    @Override
    public Ingredient search(String name) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM ingredient WHERE name = ?", name);

        if (resultSet.next()) {

            String id = resultSet.getString(1);
            String iName = resultSet.getString(2);
            String qtyInStock = resultSet.getString(3);
            String unitOfMeasure = resultSet.getString(4);
            double unitPrice = Double.parseDouble(resultSet.getString(5));
            double price = Double.parseDouble(resultSet.getString(6));
            String supplierId = resultSet.getString(7);

            Ingredient ingredient = new Ingredient(id, iName, qtyInStock, unitOfMeasure, unitPrice, price, supplierId);

            return ingredient;
        }

        return null;
    }

    @Override
    public List<IngredientDTO> searchById(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM ingredient WHERE ingredientId = ?", id);

        List<Ingredient> list = new ArrayList<>();

        if (resultSet.next()) {

            String iId = resultSet.getString(1);
            String iName = resultSet.getString(2);
            String qtyInStock = resultSet.getString(3);
            String unitOfMeasure = resultSet.getString(4);
            double unitPrice = Double.parseDouble(resultSet.getString(5));
            double price = Double.parseDouble(resultSet.getString(6));
            String supplierId = resultSet.getString(7);

            Ingredient ingredient = new Ingredient(iId, iName, qtyInStock, unitOfMeasure, unitPrice, price, supplierId);

            list.add(ingredient);
        }

        return null;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT supplierId FROM supplier");

        List<String> idList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }
}
