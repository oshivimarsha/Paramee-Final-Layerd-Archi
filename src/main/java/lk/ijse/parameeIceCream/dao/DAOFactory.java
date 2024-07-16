package lk.ijse.parameeIceCream.dao;


import lk.ijse.parameeIceCream.dao.custom.SalaryDAO;
import lk.ijse.parameeIceCream.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {}

    public static DAOFactory getDAOFactory() {
        return daoFactory == null ? daoFactory= new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMERDAO,ITEMDAO,ORDERDAO,ORDERDETAILSDAO,QUERY,EMPLOYEEDAO, MACHINEDAO,SALARYDAO,DEPARTMENTDAO,SUPPLIERDAO,LOGINDAO,INGREDIENTDAO,PRODUCTDAO,INGREDIENTPRODUCTDAO
    }

    public SuperDAO getDAO(DAOTypes daoTypes){

        switch (daoTypes){

            case CUSTOMERDAO:
                return  new CustomerDAOImpl();
            case EMPLOYEEDAO:
                return new EmployeeDAOImpl();
            case MACHINEDAO:
                return new MachineDAOImpl();
            case SALARYDAO:
                return new SalaryDAOImpl();
            case DEPARTMENTDAO:
                return new DepartmentDAOImpl();
            case SUPPLIERDAO:
                return new SupplierDAOImpl();
            case LOGINDAO:
                return new LoginDAOImpl();
            case INGREDIENTDAO:
                return new IngredientDAOImpl();
            case ORDERDETAILSDAO:
                return new OrderDetailDAOImpl();
            case PRODUCTDAO:
                return new ProductDAOImpl();
            case ORDERDAO:
                return new OrderDAOImpl();
            case INGREDIENTPRODUCTDAO:
                return new IngredientsProductDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }
}
