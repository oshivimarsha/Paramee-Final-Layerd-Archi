package lk.ijse.parameeIceCream.bo.custom;

import lk.ijse.parameeIceCream.bo.SuperBO;
import lk.ijse.parameeIceCream.dto.DepartmentDTO;
import lk.ijse.parameeIceCream.dto.EmployeeDTO;
import lk.ijse.parameeIceCream.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeBO extends SuperBO {
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException;

    public boolean saveEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    public boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException;

    public boolean existEmployee(String id) throws SQLException, ClassNotFoundException;

    public String generateNewID() throws SQLException, ClassNotFoundException;

    public boolean deleteEmployee(String nic) throws SQLException, ClassNotFoundException;

    public EmployeeDTO searchEmployee(String nic) throws SQLException, ClassNotFoundException;
    public EmployeeDTO searchByNic(String nic) throws SQLException, ClassNotFoundException;

    public List<String> getEmployeeTels() throws SQLException, ClassNotFoundException;
    public EmployeeDTO searchEmployeeByTel(String tel) throws SQLException, ClassNotFoundException;

    EmployeeDTO searchEmployeeById(String id) throws SQLException, ClassNotFoundException;
    List<String> getDepartmentIds() throws SQLException, ClassNotFoundException;
    DepartmentDTO searchDepartmentById(String id) throws SQLException, ClassNotFoundException;
}
