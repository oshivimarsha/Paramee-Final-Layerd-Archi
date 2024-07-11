package lk.ijse.parameeIceCream.bo.custom.impl;

import lk.ijse.parameeIceCream.bo.custom.SalaryBO;
import lk.ijse.parameeIceCream.dao.DAOFactory;
import lk.ijse.parameeIceCream.dao.SQLUtill;
import lk.ijse.parameeIceCream.dao.SuperDAO;
import lk.ijse.parameeIceCream.dao.custom.EmployeeDAO;
import lk.ijse.parameeIceCream.dao.custom.SalaryDAO;
import lk.ijse.parameeIceCream.dto.EmployeeDTO;
import lk.ijse.parameeIceCream.dto.SalaryDTO;
import lk.ijse.parameeIceCream.entity.Employee;
import lk.ijse.parameeIceCream.entity.Salary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalaryBOImpl implements SalaryBO {

    SalaryDAO salaryDAO = (SalaryDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SALARYDAO);
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEEDAO);

    @Override
    public ArrayList<SalaryDTO> getAllSalary() throws SQLException, ClassNotFoundException {
        ArrayList<Salary> salaries = salaryDAO.getAll();
        ArrayList<SalaryDTO> salaryDTOS = new ArrayList<>();

        for (Salary salary : salaries) {
            salaryDTOS.add(new SalaryDTO(salary.getId(), salary.getBasicSalary(), salary.getAllowences(), salary.getAdvance()));
        }
        return salaryDTOS;
    }

    @Override
    public boolean saveSalary(SalaryDTO salaryDTO) throws SQLException, ClassNotFoundException {
        return salaryDAO.save(new Salary(salaryDTO.getId(), salaryDTO.getBasicSalary(), salaryDTO.getAllowences(), salaryDTO.getAdvance()));
    }

    @Override
    public boolean updateSalary(SalaryDTO salaryDTO) throws SQLException, ClassNotFoundException {
        return salaryDAO.update(new Salary(salaryDTO.getId(), salaryDTO.getBasicSalary(), salaryDTO.getAllowences(), salaryDTO.getAdvance()));
    }

    @Override
    public boolean existSalary(String id) throws SQLException, ClassNotFoundException {
        return salaryDAO.exist(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return salaryDAO.generateNewID();
    }

    @Override
    public boolean deleteSalary(String id) throws SQLException, ClassNotFoundException {
        return salaryDAO.delete(id);
    }

    @Override
    public SalaryDTO searchSalary(String id) throws SQLException, ClassNotFoundException {
        Salary salary = salaryDAO.search(id);
        SalaryDTO salaryDTO = new SalaryDTO(salary.getId(), salary.getBasicSalary(), salary.getAllowences(), salary.getAdvance());
        return salaryDTO;
    }

    @Override
    public List<Double> getSalaryDetail(String id) throws SQLException, ClassNotFoundException {
        return salaryDAO.getSalaryDetail(id);
    }

    @Override
    public EmployeeDTO searchEmployeeById(String id) throws SQLException, ClassNotFoundException {
        Employee employee = employeeDAO.searchById(id);
        EmployeeDTO employeeDTO = new EmployeeDTO(employee.getId(),employee.getName(),employee.getNic(),employee.getAddress(),employee.getEmail(), employee.getTel(), employee.getDob(), employee.getRegisterDate(), employee.getPosition(), employee.getSalary(), employee.getDepartmentId(), employee.getPath());
        return employeeDTO;
    }
}
