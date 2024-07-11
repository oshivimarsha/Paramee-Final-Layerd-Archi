package lk.ijse.parameeIceCream.bo.custom.impl;


import lk.ijse.parameeIceCream.bo.custom.LoginBO;
import lk.ijse.parameeIceCream.dao.DAOFactory;
import lk.ijse.parameeIceCream.dao.custom.LoginDAO;

import java.sql.SQLException;

public class LoginBOImpl implements LoginBO {

    LoginDAO userDAO = (LoginDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.LOGINDAO);

    @Override
    public boolean saveUser(String userName, String password,String email) throws SQLException,ClassNotFoundException {
        return userDAO.saveUser(userName,password,email);
    }

    @Override
    public String Checkcredential(String username) throws SQLException,ClassNotFoundException {
        return userDAO.Checkcredential(username);
    }
}