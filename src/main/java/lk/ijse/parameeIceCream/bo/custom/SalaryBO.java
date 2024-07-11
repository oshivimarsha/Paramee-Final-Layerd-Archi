package lk.ijse.parameeIceCream.bo.custom;

import lk.ijse.parameeIceCream.bo.SuperBO;
import lk.ijse.parameeIceCream.dto.EmployeeDTO;
import lk.ijse.parameeIceCream.dto.SalaryDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SalaryBO extends SuperBO {
    ArrayList<SalaryDTO> getAllSalary() throws SQLException, ClassNotFoundException;
    boolean saveSalary(SalaryDTO salaryDTO) throws SQLException, ClassNotFoundException;
    boolean updateSalary(SalaryDTO salaryDTO) throws SQLException, ClassNotFoundException;
    boolean existSalary(String id) throws SQLException, ClassNotFoundException;
    String generateNewID() throws SQLException, ClassNotFoundException;
    boolean deleteSalary(String id) throws SQLException, ClassNotFoundException;
    SalaryDTO searchSalary(String id) throws SQLException, ClassNotFoundException;
    List<Double> getSalaryDetail(String id) throws SQLException, ClassNotFoundException;
    EmployeeDTO searchEmployeeById(String id) throws SQLException, ClassNotFoundException;
}
