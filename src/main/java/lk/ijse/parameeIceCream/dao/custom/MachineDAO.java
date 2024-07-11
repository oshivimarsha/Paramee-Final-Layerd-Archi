package lk.ijse.parameeIceCream.dao.custom;

import lk.ijse.parameeIceCream.dao.CrudDAO;
import lk.ijse.parameeIceCream.entity.Machine;

import java.sql.SQLException;
import java.util.List;

public interface MachineDAO extends CrudDAO<Machine> {
    Machine searchByNum(String serialNumber) throws SQLException, ClassNotFoundException;
    List<String> getNum() throws SQLException, ClassNotFoundException;
    //List<String> getIds() throws SQLException,ClassNotFoundException;

}
