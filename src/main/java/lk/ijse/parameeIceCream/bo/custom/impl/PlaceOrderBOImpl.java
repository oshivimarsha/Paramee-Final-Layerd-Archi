package lk.ijse.parameeIceCream.bo.custom.impl;

import lk.ijse.parameeIceCream.bo.custom.PlaceOrderBO;
import lk.ijse.parameeIceCream.dao.DAOFactory;
import lk.ijse.parameeIceCream.dao.custom.CustomerDAO;
import lk.ijse.parameeIceCream.dao.custom.OrderDAO;
import lk.ijse.parameeIceCream.dao.custom.OrderDetailDAO;
import lk.ijse.parameeIceCream.dao.custom.ProductDAO;
import lk.ijse.parameeIceCream.db.DbConnection;
import lk.ijse.parameeIceCream.dto.CustomerDTO;
import lk.ijse.parameeIceCream.dto.OrderDTO;
import lk.ijse.parameeIceCream.dto.OrderDetailDTO;
import lk.ijse.parameeIceCream.dto.ProductDTO;
import lk.ijse.parameeIceCream.entity.Customer;
import lk.ijse.parameeIceCream.entity.Order;
import lk.ijse.parameeIceCream.entity.OrderDetail;
import lk.ijse.parameeIceCream.entity.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDERDAO);
    ProductDAO productDAO = (ProductDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PRODUCTDAO);

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.CUSTOMERDAO);

    OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILSDAO);


    @Override
    public String generateNewOrderID() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewID();
    }

    @Override
    public String getLastOId() throws SQLException, ClassNotFoundException {
        return orderDAO.getLastOId();
    }

    @Override
    public double getNetTot(String oId) throws SQLException, ClassNotFoundException {
        return orderDAO.getNetTot(oId);
    }

    @Override
    public boolean placeOrder(OrderDTO orderDTO, List<OrderDetailDTO> orderDetails) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        Order order = new Order(orderDTO.getId(),orderDTO.getDate(),orderDTO.getUnitPrice(),orderDTO.getCustomrId());
        ArrayList<OrderDetail> orderDetailEn = new ArrayList<>();

        System.out.println(order);

        for (OrderDetailDTO order1: orderDetails){
            orderDetailEn.add(new OrderDetail(order1.getOrderId(),order1.getProductId(),order1.getQty(),order1.getUnitPrice()));
        }

        try {
            System.out.println("in order - "+order);
            boolean isOrderSaved = orderDAO.save(order);//OrderRepo.save(order);
            if (isOrderSaved) {
                System.out.println("in od up - "+orderDetailEn);
                boolean isQtyUpdated = productDAO.updateQ(orderDetailEn);//ProductRepo.update(orderDetailEn);

                if (isQtyUpdated) {
                    System.out.println(isQtyUpdated  +"\n -- in od up -- "+orderDetailEn);
                    boolean isOrderDetailSaved = orderDetailDAO.save(orderDetailEn);
                    System.out.println( "is save orDetail - " + isOrderDetailSaved);
                    if (isOrderDetailSaved) {
                        System.out.println("OrderDetail save  "+isOrderDetailSaved);
                        connection.commit();
                        return true;
                    }
                }
            }
            connection.rollback();
            return false;
        } catch (Exception e) {
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public List<String> getProductName() throws SQLException, ClassNotFoundException {
        return productDAO.getName();
    }

    @Override
    public ProductDTO searchByProductName(String name) throws SQLException, ClassNotFoundException {
        Product product = productDAO.searchByName(name);
        ProductDTO productDTO = new ProductDTO(product.getId(), product.getName(), product.getCategory(),product.getDescription(),product.getQtyAvailable(),product.getUnitPrice(),product.getDepartmentId(),product.getPath());
        return productDTO;
    }

    @Override
    public List<String> getCustomerTels() throws SQLException, ClassNotFoundException {
        return customerDAO.getTels();
    }

    @Override
    public CustomerDTO searchCustomerTel(String tel) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.search(tel);
        CustomerDTO customerDTO = new CustomerDTO(customer.getId(),customer.getName(),customer.getNic(),customer.getAddress(),customer.getEmail(),customer.getTel());
        return customerDTO;
    }

    @Override
    public String getCurrentId() throws SQLException, ClassNotFoundException {
        return orderDAO.getCurrentId();
    }
}
