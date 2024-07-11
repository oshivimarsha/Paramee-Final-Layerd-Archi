package lk.ijse.parameeIceCream.bo.custom;

import lk.ijse.parameeIceCream.bo.SuperBO;
import lk.ijse.parameeIceCream.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SupplierBO extends SuperBO {
    ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException;
    boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;
    boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException;
    boolean existSupplier(String id) throws SQLException, ClassNotFoundException;
    String generateNewID() throws SQLException, ClassNotFoundException;
    boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException;
    SupplierDTO searchSupplier(String id) throws SQLException, ClassNotFoundException;
    SupplierDTO searchSupplierByTel(String tel) throws SQLException, ClassNotFoundException;
    List<String> getSupplierIds() throws SQLException, ClassNotFoundException;
    List<String> getSupplierTels() throws SQLException, ClassNotFoundException;

}
