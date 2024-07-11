package lk.ijse.parameeIceCream.dao.custom.impl;

import lk.ijse.parameeIceCream.dao.SQLUtill;
import lk.ijse.parameeIceCream.dao.custom.ProductDAO;
import lk.ijse.parameeIceCream.db.DbConnection;
import lk.ijse.parameeIceCream.entity.Customer;
import lk.ijse.parameeIceCream.entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public ArrayList<Product> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM product");

        ArrayList<Product> proList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String category = resultSet.getString(3);
            String description = resultSet.getString(4);
            int qtyAvailable = Integer.parseInt(resultSet.getString(5));
            double unitPrice = Double.parseDouble(resultSet.getString(6));
            String departmentId = resultSet.getString(7);
            String path = resultSet.getString(8);

            Product product = new Product(id, name, category, description, qtyAvailable, unitPrice, departmentId, path);
            proList.add(product);
        }
        return proList;
    }

    @Override
    public boolean save(Product product) throws SQLException, ClassNotFoundException {
        boolean result = SQLUtill.execute( "INSERT INTO product VALUES(?, ?, ?, ?, ?, ?, ?, ?)", product.getId(), product.getName(), product.getCategory(), product.getDescription(), product.getQtyAvailable(), product.getUnitPrice(), product.getDescription(), product.getPath());
        return result;
    }

    @Override
    public boolean update(Product product) throws SQLException, ClassNotFoundException {
        boolean result = SQLUtill.execute("UPDATE product SET  name = ?, category = ?, description = ?, qtyAvailable = ?, unitPrice = ?, departmentId = ?, path = ? WHERE productId = ?", product.getName(), product.getCategory(), product.getDescription(), product.getQtyAvailable(), product.getUnitPrice(), product.getDescription(), product.getPath(), product.getId());
        return result;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT productId FROM product WHERE productId=?",id);
        return rst.next();
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT productId FROM product ORDER BY CAST(SUBSTRING(productId, 2) AS UNSIGNED) DESC LIMIT 1");

        if (rst.next()) {
            String id = rst.getString(1);
            String[] split = id.split("P");
            int idNum = Integer.parseInt(split[1]);

            if(idNum >= 1){
                return "P" + 0 + 0 + ++idNum;
            }else if(idNum >= 9){
                return "P" + 0 + ++idNum;
            } else if(idNum >= 99){
                return "P" + ++idNum;
            }
        }
        return "P001";
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        boolean result = SQLUtill.execute("DELETE FROM product WHERE name = ?", id);
        return result;
    }

    @Override
    public Product search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM product WHERE productId = ?", id);

        if (resultSet.next()) {
            String pId = resultSet.getString(1);
            String pName = resultSet.getString(2);
            String category = resultSet.getString(3);
            String description = resultSet.getString(4);
            int qtyAvailable = Integer.parseInt(resultSet.getString(5));
            double unitPrice = Double.parseDouble(resultSet.getString(6));
            String departmentId = resultSet.getString(7);
            String path = resultSet.getString(8);

            Product product = new Product(pId, pName, category, description, qtyAvailable, unitPrice, departmentId, path);

            return product;
        }

        return null;
    }


    @Override
    public Product searchByName(String name) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM product WHERE name = ?", name);

        if (resultSet.next()) {
            String pId = resultSet.getString(1);
            String pName = resultSet.getString(2);
            String category = resultSet.getString(3);
            String description = resultSet.getString(4);
            int qtyAvailable = Integer.parseInt(resultSet.getString(5));
            double unitPrice = Double.parseDouble(resultSet.getString(6));
            String departmentId = resultSet.getString(7);
            String path = resultSet.getString(8);

            Product product = new Product(pId, pName, category, description, qtyAvailable, unitPrice, departmentId, path);

            return product;
        }

        return null;
    }

    @Override
    public List<String> getName() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT name FROM product");

        List<String> nameList = new ArrayList<>();

        while (resultSet.next()) {
            String name = resultSet.getString(1);
            nameList.add(name);
        }
        return nameList;
    }

    @Override
    public boolean updateQty(String Code, int qty) throws SQLException, ClassNotFoundException {
        boolean result = SQLUtill.execute("UPDATE product SET qtyAvailable = qtyAvailable - ? WHERE productId = ?", Code, qty);
        return result;
    }

    @Override
    public int getProductCount() throws SQLException, ClassNotFoundException {
        /*ResultSet resultSet = SQLUtill.execute("SELECT COUNT(*) AS productCount FROM product");

        if(resultSet.next()) {
            return resultSet.getInt("productCount");
        }
        return 0;*/
        String sql = "SELECT COUNT(*) AS productCount FROM product";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("productCount");
        }
        return 0;
    }


}
