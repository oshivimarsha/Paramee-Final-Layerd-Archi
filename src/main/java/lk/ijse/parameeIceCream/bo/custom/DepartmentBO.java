package lk.ijse.parameeIceCream.bo.custom;

import lk.ijse.parameeIceCream.bo.SuperBO;
import lk.ijse.parameeIceCream.dto.DepartmentDTO;
import lk.ijse.parameeIceCream.entity.Department;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DepartmentBO extends SuperBO {
    ArrayList<DepartmentDTO> getAllDepartment() throws SQLException, ClassNotFoundException;
    boolean saveDepartment(DepartmentDTO departmentDTO) throws SQLException, ClassNotFoundException;
    boolean updateDepartment(DepartmentDTO departmentDTO) throws SQLException, ClassNotFoundException;
    boolean existDepartment(String id) throws SQLException, ClassNotFoundException;
    String generateNewID() throws SQLException, ClassNotFoundException;
    boolean deleteDepartment(String name) throws SQLException, ClassNotFoundException;
    DepartmentDTO searchDepartment(String name) throws SQLException, ClassNotFoundException;
    List<String> getDepartmentIds() throws SQLException, ClassNotFoundException;
    List<String> getDepartmentName() throws SQLException, ClassNotFoundException;
    DepartmentDTO searchDepartmentById(String id) throws SQLException, ClassNotFoundException;
}
