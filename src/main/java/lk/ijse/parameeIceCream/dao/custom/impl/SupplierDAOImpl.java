package lk.ijse.parameeIceCream.dao.custom.impl;

import lk.ijse.parameeIceCream.dao.SQLUtill;
import lk.ijse.parameeIceCream.dao.custom.SupplierDAO;
import lk.ijse.parameeIceCream.db.DbConnection;
import lk.ijse.parameeIceCream.entity.Supplier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {

    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM supplier");

        ArrayList<Supplier> supList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String nic = resultSet.getString(3);
            String address = resultSet.getString(4);
            String email = resultSet.getString(5);
            String tel = resultSet.getString(6);

            Supplier supplier = new Supplier(id, name, nic, address, email, tel);
            supList.add(supplier);
        }
        return supList;
    }

    @Override
    public boolean save(Supplier supplier) throws SQLException, ClassNotFoundException {
        boolean result = SQLUtill.execute("INSERT INTO supplier VALUES(?, ?, ?, ?, ?, ?)", supplier.getId(), supplier.getName(), supplier.getNic(), supplier.getAddress(), supplier.getEmail(), supplier.getTel());
        return result;
    }

    @Override
    public boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {
        boolean result = SQLUtill.execute("UPDATE supplier SET supplierId = ?, name = ?, nic = ?, address = ?, email = ? WHERE tel = ?", supplier.getId(), supplier.getName(), supplier.getNic(), supplier.getAddress(), supplier.getEmail(), supplier.getTel());
        return result;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT supplierId FROM supplier WHERE supplierId=?",id);
        return rst.next();
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT supplierId FROM supplier ORDER BY CAST(SUBSTRING(supplierId, 2) AS UNSIGNED) DESC LIMIT 1");

        if (rst.next()) {
            String id = rst.getString(1);
            String[] split = id.split("S");
            int idNum = Integer.parseInt(split[1]);

            if(idNum >= 1){
                return "S" + 0 + 0 + ++idNum;
            }else if(idNum >= 9){
                return "S" + 0 + ++idNum;
            } else if(idNum >= 99){
                return "S" + ++idNum;
            }
        }
        return "S001";
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        boolean result = SQLUtill.execute("DELETE FROM supplier WHERE tel = ?", id);
        return result;
    }

    @Override
    public Supplier search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM supplier WHERE supplierId = ?", id);

        if (resultSet.next()) {

            String sId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String nic = resultSet.getString(3);
            String address = resultSet.getString(4);
            String email = resultSet.getString(5);
            String tel = resultSet.getString(6);

            Supplier supplier = new Supplier(sId, name, nic, address, email, tel);

            return supplier;
        }

        return null;
    }

    @Override
    public Supplier searchByTel(String tel) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM supplier WHERE tel = ?", tel);
;
        if (resultSet.next()) {

            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String nic = resultSet.getString(3);
            String address = resultSet.getString(4);
            String email = resultSet.getString(5);
            String sTel = resultSet.getString(6);

            Supplier supplier = new Supplier(id, name, nic, address, email, sTel);

            return supplier;
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

    @Override
    public List<String> getTels() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT tel FROM supplier");

        List<String> telList = new ArrayList<>();

        while (resultSet.next()) {
            String tel = resultSet.getString(1);
            telList.add(tel);
        }
        return telList;
    }
}
