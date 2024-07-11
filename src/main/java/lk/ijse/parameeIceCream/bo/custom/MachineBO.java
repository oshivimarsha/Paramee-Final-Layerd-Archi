package lk.ijse.parameeIceCream.bo.custom;

import lk.ijse.parameeIceCream.bo.SuperBO;
import lk.ijse.parameeIceCream.dto.DepartmentDTO;
import lk.ijse.parameeIceCream.dto.MachineDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface MachineBO extends SuperBO {
    ArrayList<MachineDTO> getAllMachine() throws SQLException, ClassNotFoundException;
    boolean saveMachine(MachineDTO machine) throws SQLException, ClassNotFoundException;
    boolean updateMachine(MachineDTO machine) throws SQLException, ClassNotFoundException;
    boolean existMachine(String id) throws SQLException, ClassNotFoundException;
    String generateNewID() throws SQLException, ClassNotFoundException;
    boolean deleteMachine(String id) throws SQLException, ClassNotFoundException;
    MachineDTO searchMachine(String id) throws SQLException, ClassNotFoundException;
    MachineDTO searchByNumMachine(String serialNumber) throws SQLException, ClassNotFoundException;
    List<String> getNumMachine() throws SQLException, ClassNotFoundException;
    List<String> getDepartmentIds() throws SQLException, ClassNotFoundException;
    DepartmentDTO searchDepartment(String name) throws SQLException, ClassNotFoundException;
    DepartmentDTO searchDepartmentById(String id) throws SQLException, ClassNotFoundException;
}
