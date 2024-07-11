package lk.ijse.parameeIceCream.bo.custom;


import lk.ijse.parameeIceCream.bo.SuperBO;

import java.sql.SQLException;

public interface LoginBO extends SuperBO {
    boolean saveUser(String userName, String password, String email) throws SQLException,ClassNotFoundException ;
    String Checkcredential(String username) throws SQLException,ClassNotFoundException;
}