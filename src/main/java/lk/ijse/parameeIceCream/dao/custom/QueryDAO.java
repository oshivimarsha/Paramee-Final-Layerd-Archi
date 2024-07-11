package lk.ijse.parameeIceCream.dao.custom;

import lk.ijse.parameeIceCream.dao.SQLUtill;
import lk.ijse.parameeIceCream.dao.SuperDAO;
import lk.ijse.parameeIceCream.db.DbConnection;
import lk.ijse.parameeIceCream.entity.Custom;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    public ArrayList<Custom> getlinChart() throws SQLException,ClassNotFoundException;
}
