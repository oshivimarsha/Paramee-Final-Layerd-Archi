package lk.ijse.parameeIceCream.dao.custom.impl;

import lk.ijse.parameeIceCream.dao.SQLUtill;
import lk.ijse.parameeIceCream.dao.custom.QueryDAO;
import lk.ijse.parameeIceCream.db.DbConnection;
import lk.ijse.parameeIceCream.entity.Custom;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {
    /*@Override
    public double getLastMonthIncome() throws SQLException,ClassNotFoundException {
        String sql = "WITH LastMonth AS (SELECT DATE_FORMAT(MAX(order_date), '%Y-%m') AS last_month FROM orders ),MonthlySalesRevenue AS (SELECT DATE_FORMAT(o.order_date, '%Y-%m') AS month,SUM(od.qty * od.unit_price) AS total_revenue FROM orders o JOIN order_detail od ON o.order_id = od.order_id GROUP BY DATE_FORMAT(o.order_date, '%Y-%m') ), MonthlyCosts AS (SELECT DATE_FORMAT(o.order_date, '%Y-%m') AS month, SUM(od.qty * isd.unit_price) AS total_cost FROM orders o JOIN order_detail od ON o.order_id = od.order_id JOIN item_supplier_detail isd ON od.item_id = isd.item_id GROUP BY DATE_FORMAT(o.order_date, '%Y-%m')) SELECT msr.total_revenue - COALESCE(mc.total_cost, 0) AS last_month_profit FROM MonthlySalesRevenue msr JOIN LastMonth lm ON msr.month = lm.last_month LEFT JOIN MonthlyCosts mc ON msr.month = mc.month";

        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        while (resultSet.next()) {
            double profit = resultSet.getDouble(1);

            return profit;
        }
        return 0.0;
    }*/

    @Override
    public ArrayList<Custom> getlinChart() throws SQLException,ClassNotFoundException {
        ArrayList<Custom> dailyRevenueTmList = new ArrayList<>();

        ResultSet rst = SQLUtill.execute("SELECT\n" +
                "    DATE_FORMAT(o.orderDate, '%Y-%m-%d') AS OrderDate,\n" +
                "    SUM(opd.qty * opd.unitPrice) AS TotalAmount\n" +
                "FROM\n" +
                "    orders o\n" +
                "JOIN\n" +
                "    orderProductDetail opd ON o.orderId = opd.orderId\n" +
                "WHERE\n" +
                "    o.orderDate BETWEEN (SELECT MIN(orderDate) FROM orders) AND (SELECT MAX(orderDate) FROM orders)\n" +
                "GROUP BY\n" +
                "    DATE_FORMAT(o.orderDate, '%Y-%m-%d')\n" +
                "ORDER BY\n" +
                "    OrderDate");

        while (rst.next()) {
            String date = rst.getString(1);
            int count = rst.getInt(2);

            Custom dailyRevenueTm = new Custom(date,count);
            dailyRevenueTmList.add(dailyRevenueTm);
        }
        return dailyRevenueTmList;

    }


}
