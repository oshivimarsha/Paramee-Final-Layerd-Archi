package lk.ijse.parameeIceCream.dao.custom.impl;

import lk.ijse.parameeIceCream.dao.SQLUtill;
import lk.ijse.parameeIceCream.dao.custom.OrderDAO;
import lk.ijse.parameeIceCream.db.DbConnection;
import lk.ijse.parameeIceCream.entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Order order) throws SQLException, ClassNotFoundException {
        boolean result = SQLUtill.execute( "INSERT INTO orders VALUES(?, ?, ?, ?)", order.getId(), order.getDate(), order.getUnitPrice(), order.getCustomrId());
        return result;
    }

    @Override
    public boolean update(Order dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT orderId FROM orders ORDER BY CAST(SUBSTRING(orderId, 2) AS UNSIGNED) DESC LIMIT 1");

        if (rst.next()) {
            String id = rst.getString("orderId");
            String[] split = id.split("O");  //" ", "2"
            int idNum = Integer.parseInt(split[1]);

            if(idNum >= 1){
                return "O" + 0 + 0 + ++idNum;
            }else if(idNum >= 9){
                return "O" + 0 + ++idNum;
            } else if(idNum >= 99){
                return "O" + ++idNum;
            }
        }
        return "O001";
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Order search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT CONCAT('O', MAX(CAST(SUBSTRING(orderId, 2) AS UNSIGNED))) AS max_order_id FROM orders");
        if(resultSet.next()) {
            String orderId = resultSet.getString("orderId");
            return orderId;
        }
        return null;
    }

    @Override
    public String getLastOId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT orderId FROM orders ORDER BY CAST(SUBSTRING(orderId, 2) AS UNSIGNED) DESC LIMIT 1;");

        if(resultSet.next()) {
            String orderId = resultSet.getString("orderId");
            return orderId;
        }
        return null;
    }

    @Override
    public double getNetTot(String oId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT SUM(od.Qty * od.unitPrice) AS net_total FROM orders o JOIN orderProductDetail od ON o.orderId = od.orderId WHERE o.orderId = ? GROUP BY o.orderId;";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);
        System.out.println(oId);
        pstm.setString(1, oId);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            double netTot = resultSet.getDouble(1);
            System.out.println(netTot);
            return netTot;
        }
        return 0.0;
    }

    @Override
    public int getOrderCount() throws SQLException, ClassNotFoundException {
        /*ResultSet resultSet = SQLUtill.execute("SELECT COUNT(*) AS orderCount FROM orders");

        if(resultSet.next()) {
            return resultSet.getInt("orderCount");
        }
        return 0;*/
        String sql = "SELECT COUNT(*) AS orderCount FROM orders";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("orderCount");
        }
        return 0;
    }
}
