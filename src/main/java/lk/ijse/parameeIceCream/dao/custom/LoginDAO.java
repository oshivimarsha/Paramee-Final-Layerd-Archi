package lk.ijse.parameeIceCream.dao.custom;

import lk.ijse.parameeIceCream.dao.SuperDAO;

import java.sql.SQLException;

public interface LoginDAO extends SuperDAO {

    boolean saveUser(String userName, String password,String email) throws SQLException,ClassNotFoundException;
    String Checkcredential(String username) throws SQLException,ClassNotFoundException;

}