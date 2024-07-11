package lk.ijse.parameeIceCream.dao.custom.impl;

import lk.ijse.parameeIceCream.dao.SQLUtill;
import lk.ijse.parameeIceCream.dao.custom.DepartmentDAO;
import lk.ijse.parameeIceCream.db.DbConnection;
import lk.ijse.parameeIceCream.entity.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDAOImpl implements DepartmentDAO {
    @Override
    public ArrayList<Department> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM department");

        ArrayList<Department> depList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String description = resultSet.getString(3);
            int emp = Integer.parseInt(resultSet.getString(4));

            Department department = new Department(id, name, description, emp);
            depList.add(department);
        }
        return depList;
    }

    @Override
    public boolean save(Department department) throws SQLException, ClassNotFoundException {
        boolean result = SQLUtill.execute("INSERT INTO department VALUES(?, ?, ?, ?)", department.getId(), department.getName(), department.getDescription(), department.getNumOfEmp());
        return result;
    }

    @Override
    public boolean update(Department department) throws SQLException, ClassNotFoundException {
        boolean result = SQLUtill.execute("UPDATE department SET departmentId = ?, description = ?, numberOfEmployees = ? WHERE name = ?", department.getId(), department.getName(), department.getDescription(), department.getNumOfEmp());
        return result;
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT departmentId FROM department WHERE departmentId=?",id);
        return rst.next();
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtill.execute("SELECT departmentId FROM department ORDER BY CAST(SUBSTRING(departmentId, 2) AS UNSIGNED) DESC LIMIT 1");

        if (rst.next()) {
            String id = rst.getString(1);
            String[] split = id.split("D");
            int idNum = Integer.parseInt(split[1]);

            if(idNum >= 1){
                return "D" + 0 + 0 + ++idNum;
            }else if(idNum >= 9){
                return "D" + 0 + ++idNum;
            } else if(idNum >= 99){
                return "D" + ++idNum;
            }
        }
        return "D001";
    }

    @Override
    public boolean delete(String name) throws SQLException, ClassNotFoundException {
        boolean result = SQLUtill.execute("DELETE FROM department WHERE name = ?", name);
        return result;
    }

    @Override
    public Department search(String name) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM department WHERE name = ?", name);

        if (resultSet.next()) {

            String dId = resultSet.getString(1);
            String dName = resultSet.getString(2);
            String description = resultSet.getString(3);
            int emp = Integer.parseInt(resultSet.getString(4));

            Department department = new Department(dId, dName, description, emp);

            return department;
        }

        return null;
    }

    @Override
    public Department searchId(String name) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM department WHERE name = ?", name);

        if (resultSet.next()) {

            String dId = resultSet.getString(1);
            String dName = resultSet.getString(2);
            String description = resultSet.getString(3);
            int emp = Integer.parseInt(resultSet.getString(4));

            Department department = new Department(dId, dName, description, emp);

            return department;
        }

        return null;
    }

    @Override
    public List<String> getIds() throws SQLException, ClassNotFoundException {
        String sql = "SELECT departmentId FROM department";
        PreparedStatement pstm = DbConnection.getInstance().getConnection()
                .prepareStatement(sql);

        List<String> idList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }

    @Override
    public List<String> getName() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT name FROM department");

        List<String> nameList = new ArrayList<>();

        while (resultSet.next()) {
            String name = resultSet.getString(1);
            nameList.add(name);
        }
        return nameList;
    }
}
