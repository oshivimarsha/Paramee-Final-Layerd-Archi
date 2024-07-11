package lk.ijse.parameeIceCream.dao.custom;

import lk.ijse.parameeIceCream.dao.CrudDAO;
import lk.ijse.parameeIceCream.entity.Department;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentDAO extends CrudDAO<Department> {

    Department searchId(String name) throws SQLException, ClassNotFoundException;
    List<String> getIds() throws SQLException,ClassNotFoundException;
    List<String> getName() throws SQLException,ClassNotFoundException;

}
