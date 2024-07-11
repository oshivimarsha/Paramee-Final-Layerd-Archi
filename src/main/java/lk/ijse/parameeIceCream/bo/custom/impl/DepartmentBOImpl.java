package lk.ijse.parameeIceCream.bo.custom.impl;

import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import lk.ijse.parameeIceCream.bo.BOFactory;
import lk.ijse.parameeIceCream.bo.custom.DepartmentBO;
import lk.ijse.parameeIceCream.dao.DAOFactory;
import lk.ijse.parameeIceCream.dao.SQLUtill;
import lk.ijse.parameeIceCream.dao.custom.DepartmentDAO;
import lk.ijse.parameeIceCream.db.DbConnection;
import lk.ijse.parameeIceCream.dto.CustomDTO;
import lk.ijse.parameeIceCream.dto.DepartmentDTO;
import lk.ijse.parameeIceCream.entity.Customer;
import lk.ijse.parameeIceCream.entity.Department;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentBOImpl implements DepartmentBO {

    DepartmentDAO departmentDAO = (DepartmentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DEPARTMENTDAO);
    @Override
    public ArrayList<DepartmentDTO> getAllDepartment() throws SQLException, ClassNotFoundException {
        ArrayList<Department> departments = departmentDAO.getAll();
        ArrayList<DepartmentDTO> departmentDTOS = new ArrayList<>();

        for (Department department : departments) {
           departmentDTOS.add(new DepartmentDTO(department.getId(), department.getName(), department.getDescription(), department.getNumOfEmp()));
        }
        return departmentDTOS;
    }

    @Override
    public boolean saveDepartment(DepartmentDTO departmentDTO) throws SQLException, ClassNotFoundException {
        return departmentDAO.save(new Department(departmentDTO.getId(), departmentDTO.getName(), departmentDTO.getDescription(), departmentDTO.getNumOfEmp()));
    }

    @Override
    public boolean updateDepartment(DepartmentDTO departmentDTO) throws SQLException, ClassNotFoundException {
        return departmentDAO.update(new Department(departmentDTO.getId(), departmentDTO.getName(), departmentDTO.getDescription(), departmentDTO.getNumOfEmp()));
    }

    @Override
    public boolean existDepartment(String id) throws SQLException, ClassNotFoundException {
        return departmentDAO.exist(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return departmentDAO.generateNewID();
    }

    @Override
    public boolean deleteDepartment(String name) throws SQLException, ClassNotFoundException {
        return departmentDAO.delete(name);
    }

    @Override
    public DepartmentDTO searchDepartment(String name) throws SQLException, ClassNotFoundException {
        Department department = departmentDAO.search(name);
        DepartmentDTO departmentDTO = new DepartmentDTO(department.getId(), department.getName(), department.getDescription(), department.getNumOfEmp());
        return departmentDTO;
    }

    @Override
    public List<String> getDepartmentIds() throws SQLException, ClassNotFoundException {
        return departmentDAO.getIds();
    }

    @Override
    public List<String> getDepartmentName() throws SQLException, ClassNotFoundException {
        return departmentDAO.getName();
    }

    @Override
    public DepartmentDTO searchDepartmentById(String id) throws SQLException, ClassNotFoundException {
        Department department = departmentDAO.search(id);
        DepartmentDTO departmentDTO = new DepartmentDTO(department.getId(), department.getName(), department.getDescription(), department.getNumOfEmp());
        return departmentDTO;
    }

}
