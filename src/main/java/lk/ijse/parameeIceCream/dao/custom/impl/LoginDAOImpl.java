package lk.ijse.parameeIceCream.dao.custom.impl;


import lk.ijse.parameeIceCream.dao.SQLUtill;
import lk.ijse.parameeIceCream.dao.custom.LoginDAO;
import lk.ijse.parameeIceCream.dao.custom.LoginDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOImpl implements LoginDAO {

    @Override
    public boolean saveUser(String userName, String password,String email) throws SQLException,ClassNotFoundException {
        boolean result = SQLUtill.execute("INSERT INTO users VALUES(?, ?, ?)",userName,password,email);
        return result;
    }
    @Override
    public String Checkcredential(String username) throws SQLException,ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT userName, password, email FROM users WHERE userName = ?",username);
        if (resultSet.next()) {
            String dbpw = resultSet.getString("password");
            return dbpw;
        }
        return null;
    }


}