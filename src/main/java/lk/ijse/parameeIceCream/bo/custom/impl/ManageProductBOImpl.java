package lk.ijse.parameeIceCream.bo.custom.impl;

import lk.ijse.parameeIceCream.bo.custom.ManageProductBO;
import lk.ijse.parameeIceCream.dao.DAOFactory;
import lk.ijse.parameeIceCream.dao.custom.DepartmentDAO;
import lk.ijse.parameeIceCream.dao.custom.IngredientDAO;
import lk.ijse.parameeIceCream.dao.custom.IngredientsProductDAO;
import lk.ijse.parameeIceCream.dao.custom.ProductDAO;
import lk.ijse.parameeIceCream.db.DbConnection;
import lk.ijse.parameeIceCream.dto.DepartmentDTO;
import lk.ijse.parameeIceCream.dto.IngredientDTO;
import lk.ijse.parameeIceCream.dto.IngredientsProductDTO;
import lk.ijse.parameeIceCream.dto.ProductDTO;
import lk.ijse.parameeIceCream.entity.Department;
import lk.ijse.parameeIceCream.entity.Ingredient;
import lk.ijse.parameeIceCream.entity.IngredientsProduct;
import lk.ijse.parameeIceCream.entity.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageProductBOImpl implements ManageProductBO {

    DepartmentDAO departmentDAO = (DepartmentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DEPARTMENTDAO);

    IngredientDAO ingredientDAO = (IngredientDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.INGREDIENTDAO);

    IngredientsProductDAO ingredientsProductDAO = (IngredientsProductDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.INGREDIENTPRODUCTDAO);

    ProductDAO productDAO = (ProductDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PRODUCTDAO);

    @Override
    public List<String> getDepartmentIds() throws SQLException, ClassNotFoundException {
        return departmentDAO.getIds();
    }

    @Override
    public DepartmentDTO searchDepartmentById(String id) throws SQLException, ClassNotFoundException {
        Department department = departmentDAO.searchId(id);
        DepartmentDTO departmentDTO = new DepartmentDTO(department.getId(), department.getName(), department.getDescription(), department.getNumOfEmp());
        return departmentDTO;
    }

    @Override
    public List<String> getIngredientNames() throws SQLException, ClassNotFoundException {
        return ingredientDAO.getNames();
    }

    @Override
    public IngredientDTO searchIngredient(String name) throws SQLException, ClassNotFoundException {
        Ingredient ingredient = ingredientDAO.search(name);
        IngredientDTO ingredientDTO = new IngredientDTO(ingredient.getId(), ingredient.getName(), ingredient.getQtyInStock(), ingredient.getUnitOfMeasure(), ingredient.getUnitPrice(), ingredient.getPrice(), ingredient.getSupplierId());
        return ingredientDTO;
    }

    @Override
    public List<IngredientDTO> searchIngredientById(String id) throws SQLException, ClassNotFoundException {
        return ingredientDAO.searchById(id);
    }

    @Override
    public List<String> getProductName() throws SQLException, ClassNotFoundException {
        return productDAO.getName();
    }

    @Override
    public boolean palceProduct(ProductDTO productDTO, List<IngredientsProductDTO> ingredientsProductDTO) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        connection.setAutoCommit(false);

        Product product = new Product(productDTO.getId(),productDTO.getName(),productDTO.getCategory(),productDTO.getDescription(),productDTO.getQtyAvailable(),productDTO.getUnitPrice(),productDTO.getDepartmentId(),productDTO.getPath());
        List<IngredientsProduct> ingredientsProducts = new ArrayList<>();

        for(IngredientsProductDTO ingredientsProduct:ingredientsProductDTO){
            ingredientsProducts.add(new IngredientsProduct(ingredientsProduct.getProductId(),ingredientsProduct.getIngredientId(),ingredientsProduct.getQty(),ingredientsProduct.getUnitPrice()));
        }

        try {
            //  System.out.println("product -  "+cp.getProduct());
            boolean isProductSaved = productDAO.save(product);//ProductRepo.save(cp.getProduct());
            //  System.out.println(isProductSaved);
            if (isProductSaved) {
                //   System.out.println( "IP List = "+cp.getIPList());
                boolean isQtyUpdated = ingredientDAO.updateMin(ingredientsProducts);//IngredientRepo.update(cp.getIPList());

                //   System.out.println(isQtyUpdated);
                if (isQtyUpdated) {
                    //   System.out.println(cp.getIPList());
                    System.out.println(ingredientsProducts);
                    boolean isProductrDetailSaved = ingredientsProductDAO.save(ingredientsProducts);//IngredientsProductRepo.save(cp.getIPList());
                    System.out.println("isProductrDetailSaved - "+isProductrDetailSaved);
                    //  System.out.println(isProductrDetailSaved);
                    if (isProductrDetailSaved) {
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
    public boolean updateProduct(ProductDTO productDTO, List<IngredientsProductDTO> productDTOS) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ProductDTO searchByProductName(String name) throws SQLException, ClassNotFoundException {
        Product product = productDAO.searchByName(name);
        ProductDTO productDTO = new ProductDTO(product.getId(), product.getName(), product.getCategory(),product.getDescription(),product.getQtyAvailable(),product.getUnitPrice(),product.getDepartmentId(),product.getPath());
        return productDTO;
    }

    @Override
    public IngredientsProductDTO searchByIngreProductId(String id) throws SQLException, ClassNotFoundException {
        IngredientsProduct ingredientsProduct = ingredientsProductDAO.search(id);
        IngredientsProductDTO ingredientsProductDTO = new IngredientsProductDTO(ingredientsProduct.getIngredientId(), ingredientsProduct.getProductId(), ingredientsProduct.getQty(), ingredientsProduct.getUnitPrice());
        return  ingredientsProductDTO;
    }

    @Override
    public String generateNewProductID() throws SQLException, ClassNotFoundException {
        return productDAO.generateNewID();
    }
}
