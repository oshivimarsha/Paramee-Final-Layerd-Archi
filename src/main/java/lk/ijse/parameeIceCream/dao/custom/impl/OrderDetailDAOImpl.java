package lk.ijse.parameeIceCream.dao.custom.impl;

import lk.ijse.parameeIceCream.dao.SQLUtill;
import lk.ijse.parameeIceCream.dao.custom.OrderDetailDAO;
import lk.ijse.parameeIceCream.db.DbConnection;
import lk.ijse.parameeIceCream.entity.Custom;
import lk.ijse.parameeIceCream.entity.OrderDetail;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public ArrayList<OrderDetail> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDetail od) throws SQLException, ClassNotFoundException {
        boolean result = SQLUtill.execute( "INSERT INTO orderProductDetail VALUES(?, ?, ?, ?)", od.getOrderId(), od.getProductId(), od.getQty(), od.getUnitPrice());
        return result;
    }
    public List<Custom> getMostProduct() throws SQLException, ClassNotFoundException {
       /* ResultSet resultSet = SQLUtill.execute("SELECT productId,COUNT(orderId),SUM(qty) FROM orderProductDetail GROUP BY productId ORDER BY SUM(qty) DESC LIMIT 5");//pstm.executeQuery();

        List<Custom> sellItem = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            int count = resultSet.getInt(2);
            int sumQty = resultSet.getInt(3);

            Custom mostSellItem = new Custom(id, count, sumQty);
            sellItem.add(mostSellItem);
        }
        return sellItem;*/
        String sql = "SELECT productId,COUNT(orderId),SUM(qty) FROM orderProductDetail GROUP BY productId ORDER BY SUM(qty) DESC LIMIT 5;";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<Custom> sellItem = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            int oId = resultSet.getInt(2);
            int qty = resultSet.getInt(3);

            Custom mostSellItem = new Custom(id, oId, qty);
            sellItem.add(mostSellItem);
        }
        return sellItem;
    }

    @Override
    public boolean update(OrderDetail dto) throws SQLException, ClassNotFoundException {
        return false;
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
    public OrderDetail search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(List<OrderDetail> odList) throws SQLException, ClassNotFoundException {
        System.out.println("save(List<OrderDetail> odList)- "+odList);
        for (OrderDetail od : odList) {

            System.out.println("save or - "+od);

            boolean isSaved = save(od);

            System.out.println("is saved - "+isSaved);
            if(!isSaved) {
                return false;
            }
        }
        return true;
    }
}
