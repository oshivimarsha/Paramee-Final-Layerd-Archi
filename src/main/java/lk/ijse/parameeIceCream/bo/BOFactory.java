package lk.ijse.parameeIceCream.bo;

import lk.ijse.parameeIceCream.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {}

    public static BOFactory getBOFactory() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes{
        ITEMBO,CUSTOMERBO,PLACEORDERBO,QUERYBO,EMPLOYEEBO,MACHINEBO,SALARYBO,DEPARTMENTBO,SUPPLIERBO,LOGINBO,INGREDIENTPRODUCTBO,DASHBOARDBO,MANAGEPRODUCTBO
    }

    public SuperBO getBO(BOTypes boTypes){

        switch (boTypes){
            case CUSTOMERBO:
                return new CustomerBOImpl();
            case EMPLOYEEBO:
                return new EmployeeBOImpl();
            case MACHINEBO:
                return new MachineBOImpl();
            case SALARYBO:
                return new SalaryBOImpl();
            case DEPARTMENTBO:
                return new DepartmentBOImpl();
            case SUPPLIERBO:
                return new SupplierBOImpl();
            case LOGINBO:
                return new LoginBOImpl();
            case INGREDIENTPRODUCTBO:
                return new IngredientsBOImpl();
            case DASHBOARDBO:
                return new DashboardBOImpl();
            case MANAGEPRODUCTBO:
                return new ManageProductBOImpl();
            case PLACEORDERBO:
                return new PlaceOrderBOImpl();
            default:
                return null;
        }
    }


}
