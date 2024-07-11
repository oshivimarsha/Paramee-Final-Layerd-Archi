package lk.ijse.parameeIceCream.bo.custom.impl;

import lk.ijse.parameeIceCream.bo.custom.SupplierBO;
import lk.ijse.parameeIceCream.dao.DAOFactory;
import lk.ijse.parameeIceCream.dao.SQLUtill;
import lk.ijse.parameeIceCream.dao.custom.SupplierDAO;
import lk.ijse.parameeIceCream.dto.SalaryDTO;
import lk.ijse.parameeIceCream.dto.SupplierDTO;
import lk.ijse.parameeIceCream.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SUPPLIERDAO);

    @Override
    public ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> suppliers = supplierDAO.getAll();
        ArrayList<SupplierDTO> supplierDTOS = new ArrayList<>();

       for (Supplier supplier : suppliers) {
           supplierDTOS.add(new SupplierDTO(supplier.getId(), supplier.getName(), supplier.getNic(), supplier.getAddress(), supplier.getEmail(), supplier.getTel()));
       }
        return supplierDTOS;
    }

    @Override
    public boolean saveSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(supplierDTO.getId(), supplierDTO.getName(), supplierDTO.getNic(), supplierDTO.getAddress(), supplierDTO.getEmail(), supplierDTO.getTel()));
    }

    @Override
    public boolean updateSupplier(SupplierDTO supplierDTO) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(supplierDTO.getId(), supplierDTO.getName(), supplierDTO.getNic(), supplierDTO.getAddress(), supplierDTO.getEmail(), supplierDTO.getTel()));
    }

    @Override
    public boolean existSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.exist(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return supplierDAO.generateNewID();
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);
    }

    @Override
    public SupplierDTO searchSupplier(String id) throws SQLException, ClassNotFoundException {
        Supplier supplier = supplierDAO.search(id);
        SupplierDTO supplierDTO = new SupplierDTO(supplier.getId(), supplier.getName(), supplier.getNic(), supplier.getAddress(), supplier.getEmail(), supplier.getTel());
        return supplierDTO;
    }

    @Override
    public SupplierDTO searchSupplierByTel(String tel) throws SQLException, ClassNotFoundException {
        Supplier supplier = supplierDAO.searchByTel(tel);
        SupplierDTO supplierDTO = new SupplierDTO(supplier.getId(), supplier.getName(), supplier.getNic(), supplier.getAddress(), supplier.getEmail(), supplier.getTel());
        return supplierDTO;
    }

    @Override
    public List<String> getSupplierIds() throws SQLException, ClassNotFoundException {
        return supplierDAO.getIds();
    }

    @Override
    public List<String> getSupplierTels() throws SQLException, ClassNotFoundException {
        return supplierDAO.getTels();
    }
}
