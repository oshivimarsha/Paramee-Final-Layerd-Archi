package lk.ijse.parameeIceCream.dao.custom.impl;

import lk.ijse.parameeIceCream.dao.SQLUtill;
import lk.ijse.parameeIceCream.dao.custom.CustomerDAO;
import lk.ijse.parameeIceCream.db.DbConnection;
import lk.ijse.parameeIceCream.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM customer");

        ArrayList<Customer> cusList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String nic = resultSet.getString(3);
            String address = resultSet.getString(4);
            String email = resultSet.getString(5);
            String tel = resultSet.getString(6);

            Customer customer = new Customer(id, name, nic, address, email, tel);
            cusList.add(customer);
        }
        return cusList;
    }

    @Override
    public boolean save(Customer customer) throws SQLException, ClassNotFoundException {
        boolean result = SQLUtill.execute("INSERT INTO customer VALUES(?, ?, ?, ?, ?, ?)", customer.getId() ,customer.getName(), customer.getNic(), customer.getAddress(), customer.getEmail(), customer.getTel());
        return result;
    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        boolean result = SQLUtill.execute("UPDATE customer SET customerId = ?, name = ?, nic = ?, address = ?, email = ? WHERE tel = ?", customer.getId() ,customer.getName(), customer.getNic(), customer.getAddress(), customer.getEmail(), customer.getTel());
        return result;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT customerId FROM customer WHERE customerId=?",id);
        return rst.next();
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT customerId FROM customer ORDER BY CAST(SUBSTRING(customerId, 2) AS UNSIGNED) DESC LIMIT 1");

        if (rst.next()) {
            String id = rst.getString(1);
            String[] split = id.split("C");
            int idNum = Integer.parseInt(split[1]);

            if(idNum >= 1){
                return "C" + 0 + 0 + ++idNum;
            }else if(idNum >= 9){
                return "C" + 0 + ++idNum;
            } else if(idNum >= 99){
                return "C" + ++idNum;
            }
        }
        return "C001";
    }

    @Override
    public boolean delete(String nic) throws SQLException, ClassNotFoundException {
        boolean result = SQLUtill.execute("DELETE FROM customer WHERE nic = ?",nic);
        return result;
    }

    @Override
    public Customer search(String tel) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM customer WHERE tel = ?",tel);
        if (resultSet.next()) {
            String cId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String nic = resultSet.getString(3);
            String address = resultSet.getString(4);
            String email = resultSet.getString(5);
            String cTel = resultSet.getString(6);

            Customer customer = new Customer(cId, name, nic, address, email, cTel);

            return customer;
        }

        return null;
    }

    @Override
    public List<String> getTels() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT tel FROM customer");

        List<String> telList = new ArrayList<>();

        while (resultSet.next()) {
            String tel = resultSet.getString(1);
            telList.add(tel);
        }
        return telList;
    }

    @Override
    public int getCustomerCount() throws SQLException, ClassNotFoundException {
        /*ResultSet resultSet = SQLUtill.execute("SELECT COUNT(*) AS customerCount FROM customer");

        if(resultSet.next()) {
            return resultSet.getInt("customerCount");
        }
        return 0;*/
        String sql = "SELECT COUNT(*) AS customerCount FROM customer";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return resultSet.getInt("customerCount");
        }
        return 0;
    }
}
