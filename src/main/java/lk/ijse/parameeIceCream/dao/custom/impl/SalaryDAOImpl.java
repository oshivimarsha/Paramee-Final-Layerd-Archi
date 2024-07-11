package lk.ijse.parameeIceCream.dao.custom.impl;

import lk.ijse.parameeIceCream.dao.SQLUtill;
import lk.ijse.parameeIceCream.dao.custom.SalaryDAO;
import lk.ijse.parameeIceCream.db.DbConnection;
import lk.ijse.parameeIceCream.entity.Salary;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryDAOImpl implements SalaryDAO {
    @Override
    public ArrayList<Salary> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM salary");

        ArrayList<Salary> salaryList = new ArrayList<>();

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            double basicSalary = Double.parseDouble(resultSet.getString(2));
            double allowences = Double.parseDouble(resultSet.getString(3));
            double advance = Double.parseDouble(resultSet.getString(4));

            Salary salary = new Salary(id, basicSalary, allowences, advance);
            salaryList.add(salary);
        }
        return salaryList;
    }

    @Override
    public boolean save(Salary salary) throws SQLException, ClassNotFoundException {
        boolean result = SQLUtill.execute("INSERT INTO salary VALUES(?, ?, ?, ?)", salary.getId(), salary.getBasicSalary(), salary.getAllowences(), salary.getAdvance());
        return result;
    }

    @Override
    public boolean update(Salary salary) throws SQLException, ClassNotFoundException {
        boolean result = SQLUtill.execute("UPDATE salary SET basicSalary = ?, allowences = ?, grossSalary = ?, advance = ? WHERE employeeId = ?", salary.getId(), salary.getBasicSalary(), salary.getAllowences(), salary.getAdvance());
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
        boolean result = SQLUtill.execute("DELETE FROM salary WHERE employeeId = ?", id);
        return result;
    }

    @Override
    public Salary search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Double> getSalaryDetail(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT (s.basicSalary + s.allowences) AS grossSalary, (s.basicSalary + s.allowences - s.advance) AS netPayable FROM employee e JOIN salary s ON e.employeeId = s.employeeId WHERE e.employeeId = ?", id);

        List<Double> salaryList = new ArrayList<>();

        while (resultSet.next()) {
            double grossSalary = Double.parseDouble(resultSet.getString(1));
            double netPayable = Double.parseDouble(resultSet.getString(2));

            salaryList.add(grossSalary);
            salaryList.add(netPayable);

        }
        return salaryList;
    }
}
