package lk.ijse.parameeIceCream.bo.custom.impl;

import lk.ijse.parameeIceCream.bo.custom.CustomerBO;
import lk.ijse.parameeIceCream.dao.DAOFactory;
import lk.ijse.parameeIceCream.dao.custom.CustomerDAO;
import lk.ijse.parameeIceCream.dao.custom.impl.CustomerDAOImpl;
import lk.ijse.parameeIceCream.dto.CustomerDTO;
import lk.ijse.parameeIceCream.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMERDAO);//new CustomerDAOImpl();
    @Override
    public ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customers = customerDAO.getAll();
        ArrayList<CustomerDTO> customerDTO = new ArrayList<>();

        for (Customer customer: customers){
            customerDTO.add(new CustomerDTO(customer.getId(),customer.getName(),customer.getNic(),customer.getAddress(),customer.getEmail(),customer.getTel()));
        }
        return customerDTO;
    }

    @Override
    public boolean saveCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(customerDTO.getId(),customerDTO.getName(), customerDTO.getNic(),customerDTO.getAddress(),customerDTO.getEmail(),customerDTO.getTel()));
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(customerDTO.getId(),customerDTO.getName(), customerDTO.getNic(),customerDTO.getAddress(),customerDTO.getEmail(),customerDTO.getTel()));
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.exist(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNewID();
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    @Override
    public CustomerDTO searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.search(id);
        CustomerDTO customerDTO = new CustomerDTO(customer.getId(),customer.getName(),customer.getNic(),customer.getAddress(),customer.getEmail(),customer.getTel());
        return customerDTO;
    }

    @Override
    public List<String> getTelsCustomers() throws SQLException, ClassNotFoundException {
        return customerDAO.getTels();
    }
}
