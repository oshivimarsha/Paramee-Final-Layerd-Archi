package lk.ijse.parameeIceCream.bo.custom.impl;

import lk.ijse.parameeIceCream.bo.custom.EmployeeBO;
import lk.ijse.parameeIceCream.dao.DAOFactory;
import lk.ijse.parameeIceCream.dao.SQLUtill;
import lk.ijse.parameeIceCream.dao.custom.DepartmentDAO;
import lk.ijse.parameeIceCream.dao.custom.EmployeeDAO;
import lk.ijse.parameeIceCream.dto.CustomerDTO;
import lk.ijse.parameeIceCream.dto.DepartmentDTO;
import lk.ijse.parameeIceCream.dto.EmployeeDTO;
import lk.ijse.parameeIceCream.entity.Customer;
import lk.ijse.parameeIceCream.entity.Department;
import lk.ijse.parameeIceCream.entity.Employee;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {

    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEEDAO);
    DepartmentDAO departmentDAO = (DepartmentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DEPARTMENTDAO);
    @Override
    public ArrayList<EmployeeDTO> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> employees = employeeDAO.getAll();
        ArrayList<EmployeeDTO> employeeDTOS = new ArrayList<>();

        for (Employee employee: employees){
            employeeDTOS.add(new EmployeeDTO(employee.getId(),employee.getName(),employee.getNic(),employee.getAddress(),employee.getEmail(), employee.getTel(), employee.getDob(), employee.getRegisterDate(), employee.getPosition(), employee.getSalary(), employee.getDepartmentId(), employee.getPath()));
        }
        return employeeDTOS;
    }

    @Override
    public boolean saveEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.save(new Employee(employeeDTO.getId(),employeeDTO.getName(),employeeDTO.getNic(),employeeDTO.getAddress(),employeeDTO.getEmail(), employeeDTO.getTel(), employeeDTO.getDob(), employeeDTO.getRegisterDate(), employeeDTO.getPosition(), employeeDTO.getSalary(), employeeDTO.getDepartmentId(), employeeDTO.getPath()));
    }

    @Override
    public boolean updateEmployee(EmployeeDTO employeeDTO) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(employeeDTO.getId(),employeeDTO.getName(),employeeDTO.getNic(),employeeDTO.getAddress(),employeeDTO.getEmail(), employeeDTO.getTel(), employeeDTO.getDob(), employeeDTO.getRegisterDate(), employeeDTO.getPosition(), employeeDTO.getSalary(), employeeDTO.getDepartmentId(), employeeDTO.getPath()));
    }

    @Override
    public boolean existEmployee(String id) throws SQLException, ClassNotFoundException {
        return employeeDAO.exist(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return employeeDAO.generateNewID();
    }

    @Override
    public boolean deleteEmployee(String nic) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(nic);
    }

    @Override
    public EmployeeDTO searchEmployee(String nic) throws SQLException, ClassNotFoundException {
        Employee employee = employeeDAO.search(nic);
        EmployeeDTO employeeDTO = new EmployeeDTO(employee.getId(),employee.getName(),employee.getNic(),employee.getAddress(),employee.getEmail(), employee.getTel(), employee.getDob(), employee.getRegisterDate(), employee.getPosition(), employee.getSalary(), employee.getDepartmentId(), employee.getPath());
        return employeeDTO;
    }

    @Override
    public EmployeeDTO searchByNic(String nic) throws SQLException, ClassNotFoundException {
        Employee employee = employeeDAO.searchByNic(nic);
        EmployeeDTO employeeDTO = new EmployeeDTO(employee.getId(),employee.getName(),employee.getNic(),employee.getAddress(),employee.getEmail(), employee.getTel(), employee.getDob(), employee.getRegisterDate(), employee.getPosition(), employee.getSalary(), employee.getDepartmentId(), employee.getPath());
        return employeeDTO;
    }

    @Override
    public List<String> getEmployeeTels() throws SQLException, ClassNotFoundException {
        return employeeDAO.getTels();
    }

    @Override
    public EmployeeDTO searchEmployeeByTel(String tel) throws SQLException, ClassNotFoundException {
        Employee employee = employeeDAO.searchByTel(tel);
        EmployeeDTO employeeDTO = new EmployeeDTO(employee.getId(),employee.getName(),employee.getNic(),employee.getAddress(),employee.getEmail(), employee.getTel(), employee.getDob(), employee.getRegisterDate(), employee.getPosition(), employee.getSalary(), employee.getDepartmentId(), employee.getPath());
        return employeeDTO;
    }

    @Override
    public EmployeeDTO searchEmployeeById(String id) throws SQLException, ClassNotFoundException {
        Employee employee = employeeDAO.searchById(id);
        EmployeeDTO employeeDTO = new EmployeeDTO(employee.getId(),employee.getName(),employee.getNic(),employee.getAddress(),employee.getEmail(), employee.getTel(), employee.getDob(), employee.getRegisterDate(), employee.getPosition(), employee.getSalary(), employee.getDepartmentId(), employee.getPath());
        return employeeDTO;
    }

    @Override
    public List<String> getDepartmentIds() throws SQLException, ClassNotFoundException {
        return departmentDAO.getIds();
    }

    @Override
    public DepartmentDTO searchDepartmentById(String id) throws SQLException, ClassNotFoundException {
        Department department = departmentDAO.search(id);
        DepartmentDTO departmentDTO = new DepartmentDTO(department.getId(), department.getName(), department.getDescription(), department.getNumOfEmp());
        return departmentDTO;
    }
}
