package lk.ijse.parameeIceCream.dao.custom.impl;

import lk.ijse.parameeIceCream.dao.SQLUtill;
import lk.ijse.parameeIceCream.dao.custom.MachineDAO;
import lk.ijse.parameeIceCream.db.DbConnection;
import lk.ijse.parameeIceCream.entity.Machine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MachineDAOImpl implements MachineDAO {
    @Override
    public ArrayList<Machine> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM machine");

        ArrayList<Machine> masList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String type = resultSet.getString(2);
            int serialNumber = Integer.parseInt(resultSet.getString(3));
            Date purchaseDate = Date.valueOf(resultSet.getString(4));
            double purchaseCost = Double.parseDouble(resultSet.getString(5));
            double maintainCost = Double.parseDouble(resultSet.getString(6));
            String departmentId = resultSet.getString(7);
            String path = resultSet.getString(8);

            Machine machine = new Machine(id, type, serialNumber, purchaseDate, purchaseCost, maintainCost, departmentId, path);
            masList.add(machine);
        }
        return masList;
    }

    @Override
    public boolean save(Machine machine) throws SQLException, ClassNotFoundException {
        boolean result = SQLUtill.execute("INSERT INTO machine VALUES(?, ?, ?, ?, ?, ?, ?, ?)", machine.getId(), machine.getType(), machine.getSerialNumber(), machine.getPurchaseDate(), machine.getPurchaseCost(), machine.getMaintainCost(), machine.getDepartmentId(), machine.getPath());
        return result;
    }

    @Override
    public boolean update(Machine machine) throws SQLException, ClassNotFoundException {
        boolean result = SQLUtill.execute("UPDATE machine SET machineId = ?, type = ?, purchaseDate = ?, purchaseCost = ?, maintenanceCost = ?, departmentId = ?, path = ? WHERE serialNumber = ?", machine.getId(), machine.getType(), machine.getPurchaseDate(), machine.getPurchaseCost(), machine.getMaintainCost(), machine.getDepartmentId(), machine.getPath(), machine.getSerialNumber());
        return result;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT machineId FROM machine WHERE machineId=?",id);
        return rst.next();
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT machineId FROM machine ORDER BY CAST(SUBSTRING(machineId, 2) AS UNSIGNED) DESC LIMIT 1");

        if (rst.next()) {
            String id = rst.getString(1);
            String[] split = id.split("M");
            int idNum = Integer.parseInt(split[1]);

            if(idNum >= 1){
                return "M" + 0 + 0 + ++idNum;
            }else if(idNum >= 9){
                return "M" + 0 + ++idNum;
            } else if(idNum >= 99){
                return "M" + ++idNum;
            }
        }
        return "M001";
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        boolean result = SQLUtill.execute("DELETE FROM machine WHERE serialNumber = ?", id);
        return result;
    }

    @Override
    public Machine search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Machine searchByNum(String serialNumber) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM machine WHERE serialNumber = ?", serialNumber);

        if (resultSet.next()) {
            String id = resultSet.getString(1);
            String type = resultSet.getString(2);
            int mSerialNumber = Integer.parseInt(resultSet.getString(3));
            Date purchaseDate = Date.valueOf(resultSet.getString(4));
            double purchaseCost = Double.parseDouble(resultSet.getString(5));
            double maintainCost = Double.parseDouble(resultSet.getString(6));
            String departmentId = resultSet.getString(7);
            String path = resultSet.getString(8);

            Machine machine = new Machine(id, type, mSerialNumber, purchaseDate, purchaseCost, maintainCost, departmentId, path);

            return machine;
        }
        return null;
    }

    @Override
    public List<String> getNum() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT serialNumber FROM machine");

        List<String> numList = new ArrayList<>();

        while (resultSet.next()) {
            String num = resultSet.getString(1);
            numList.add(num);
        }
        return numList;
    }

}
