package lk.ijse.parameeIceCream.bo.custom.impl;

import lk.ijse.parameeIceCream.bo.custom.DashboardBO;
import lk.ijse.parameeIceCream.bo.custom.MachineBO;
import lk.ijse.parameeIceCream.dao.DAOFactory;
import lk.ijse.parameeIceCream.dao.custom.DepartmentDAO;
import lk.ijse.parameeIceCream.dao.custom.MachineDAO;
import lk.ijse.parameeIceCream.dto.DepartmentDTO;
import lk.ijse.parameeIceCream.dto.MachineDTO;
import lk.ijse.parameeIceCream.entity.Department;
import lk.ijse.parameeIceCream.entity.Machine;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MachineBOImpl implements MachineBO {

    MachineDAO machineDAO = (MachineDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.MACHINEDAO);

    DepartmentDAO departmentDAO = (DepartmentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DEPARTMENTDAO);

    @Override
    public ArrayList<MachineDTO> getAllMachine() throws SQLException, ClassNotFoundException {
        ArrayList<Machine> machines = machineDAO.getAll();
        ArrayList<MachineDTO> machineDTOS = new ArrayList<>();

        for (Machine machine : machines) {
            machineDTOS.add(new MachineDTO(machine.getId(), machine.getType(), machine.getSerialNumber(), machine.getPurchaseDate(), machine.getPurchaseCost(), machine.getMaintainCost(), machine.getDepartmentId(), machine.getPath()));
        }
        return machineDTOS;
    }

    @Override
    public boolean saveMachine(MachineDTO machine) throws SQLException, ClassNotFoundException {
        return machineDAO.save(new Machine(machine.getId(), machine.getType(), machine.getSerialNumber(), machine.getPurchaseDate(), machine.getPurchaseCost(), machine.getMaintainCost(), machine.getDepartmentId(), machine.getPath()));
    }

    @Override
    public boolean updateMachine(MachineDTO machine) throws SQLException, ClassNotFoundException {
        return machineDAO.update(new Machine(machine.getId(), machine.getType(), machine.getSerialNumber(), machine.getPurchaseDate(), machine.getPurchaseCost(), machine.getMaintainCost(), machine.getDepartmentId(), machine.getPath()));
    }

    @Override
    public boolean existMachine(String id) throws SQLException, ClassNotFoundException {
        return machineDAO.exist(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return machineDAO.generateNewID();
    }

    @Override
    public boolean deleteMachine(String id) throws SQLException, ClassNotFoundException {
        return machineDAO.delete(id);
    }

    @Override
    public MachineDTO searchMachine(String id) throws SQLException, ClassNotFoundException {
        Machine machine = machineDAO.search(id);
        MachineDTO machineDTO = new MachineDTO(machine.getId(), machine.getType(), machine.getSerialNumber(), machine.getPurchaseDate(), machine.getPurchaseCost(), machine.getMaintainCost(), machine.getDepartmentId(), machine.getPath());
        return machineDTO;
    }

    @Override
    public MachineDTO searchByNumMachine(String serialNumber) throws SQLException, ClassNotFoundException {
        Machine machine = machineDAO.search(serialNumber);
        MachineDTO machineDTO = new MachineDTO(machine.getId(), machine.getType(), machine.getSerialNumber(), machine.getPurchaseDate(), machine.getPurchaseCost(), machine.getMaintainCost(), machine.getDepartmentId(), machine.getPath());
        return machineDTO;
    }

    @Override
    public List<String> getNumMachine() throws SQLException, ClassNotFoundException {
        return machineDAO.getNum();
    }

    @Override
    public List<String> getDepartmentIds() throws SQLException, ClassNotFoundException {
        return departmentDAO.getIds();
    }

    @Override
    public DepartmentDTO searchDepartment(String name) throws SQLException, ClassNotFoundException {
        Department department = departmentDAO.search(name);
        DepartmentDTO departmentDTO = new DepartmentDTO(department.getId(), department.getName(), department.getDescription(), department.getNumOfEmp());
        return departmentDTO;
    }

    @Override
    public DepartmentDTO searchDepartmentById(String id) throws SQLException, ClassNotFoundException {
        Department department = departmentDAO.search(id);
        DepartmentDTO departmentDTO = new DepartmentDTO(department.getId(), department.getName(), department.getDescription(), department.getNumOfEmp());
        return departmentDTO;
    }
}
