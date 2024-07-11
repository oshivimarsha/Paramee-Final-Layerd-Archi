package lk.ijse.parameeIceCream.bo.custom.impl;

import lk.ijse.parameeIceCream.bo.custom.IngredientsBO;
import lk.ijse.parameeIceCream.dao.DAOFactory;
import lk.ijse.parameeIceCream.dao.custom.*;
import lk.ijse.parameeIceCream.dto.*;
import lk.ijse.parameeIceCream.entity.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IngredientsBOImpl implements IngredientsBO {
    IngredientDAO ingredientDAO = (IngredientDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.INGREDIENTDAO);
    ProductDAO productDAO = (ProductDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PRODUCTDAO);
    IngredientsProductDAO ingredientsProductDAO = (IngredientsProductDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.INGREDIENTPRODUCTDAO);
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SUPPLIERDAO);
    DepartmentDAO departmentDAO = (DepartmentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DEPARTMENTDAO);

    @Override
    public ArrayList<IngredientDTO> getAllIngredient() throws SQLException, ClassNotFoundException {
        ArrayList<Ingredient> ingredients = ingredientDAO.getAll();
        ArrayList<IngredientDTO> ingredientDTOS = new ArrayList<>();

        for (Ingredient ingredient : ingredients) {
            ingredientDTOS.add(new IngredientDTO(ingredient.getId(), ingredient.getName(), ingredient.getQtyInStock(), ingredient.getUnitOfMeasure(), ingredient.getUnitPrice(), ingredient.getPrice(), ingredient.getSupplierId()));
        }
        return ingredientDTOS;
    }

    @Override
    public boolean saveIngredient(IngredientDTO ingredient) throws SQLException, ClassNotFoundException{
        return ingredientDAO.save(new Ingredient(ingredient.getId(), ingredient.getName(), ingredient.getQtyInStock(), ingredient.getUnitOfMeasure(), ingredient.getUnitPrice(), ingredient.getPrice(), ingredient.getSupplierId()));
    }

    @Override
    public boolean updateIngredient(IngredientDTO ingredient) throws SQLException, ClassNotFoundException {
        return ingredientDAO.update(new Ingredient(ingredient.getId(), ingredient.getName(), ingredient.getQtyInStock(), ingredient.getUnitOfMeasure(), ingredient.getUnitPrice(), ingredient.getPrice(), ingredient.getSupplierId()));
    }

    @Override
    public boolean updateMinIngredient(List<IngredientsProduct> ipList) throws SQLException, ClassNotFoundException {
        return ingredientDAO.updateMin(ipList);
    }

    @Override
    public boolean updatePlusIngredient(List<IngredientsProduct> ipList) throws SQLException, ClassNotFoundException {
        return ingredientDAO.updatePlus(ipList);
    }

    @Override
    public boolean updateQtyMinIngredient(String id, int qty) throws SQLException , ClassNotFoundException{
       return ingredientDAO.updateQtyMin(id, qty);
    }

    @Override
    public boolean updateQtyPlusIngredient(String id, int qty) throws SQLException, ClassNotFoundException {
        return ingredientDAO.updateQtyPlus(id, qty);
    }

    @Override
    public List<String> getIngredientNames() throws SQLException, ClassNotFoundException {
        return ingredientDAO.getNames();
    }

    @Override
    public boolean existIngredient(String id) throws SQLException, ClassNotFoundException {
        return ingredientDAO.exist(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return ingredientDAO.generateNewID();
    }

    @Override
    public boolean deleteIngredient(String name) throws SQLException, ClassNotFoundException {
        return ingredientDAO.delete(name);
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
    public ArrayList<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException {
        ArrayList<Product> products = productDAO.getAll();
        ArrayList<ProductDTO> productDTOS = new ArrayList<>();

        for (Product product : products) {
            productDTOS.add(new ProductDTO(product.getId(), product.getName(), product.getCategory(), product.getDescription(), product.getQtyAvailable(), product.getUnitPrice(), product.getDescription(), product.getPath()));
        }
        return productDTOS;
    }

    @Override
    public boolean saveProduct(ProductDTO product) throws SQLException, ClassNotFoundException {
        return productDAO.save(new Product(product.getId(), product.getName(), product.getCategory(), product.getDescription(), product.getQtyAvailable(), product.getUnitPrice(), product.getDescription(), product.getPath()));
    }

    @Override
    public boolean updateProduct(ProductDTO product) throws SQLException, ClassNotFoundException {
        return productDAO.update(new Product(product.getId(), product.getName(), product.getCategory(), product.getDescription(), product.getQtyAvailable(), product.getUnitPrice(), product.getDescription(), product.getPath()));
    }

    @Override
    public boolean existProduct(String id) throws SQLException, ClassNotFoundException {
        return productDAO.exist(id);
    }

    @Override
    public String generateProductNewID() throws SQLException, ClassNotFoundException {
        return productDAO.generateNewID();
    }

    @Override
    public boolean deleteProduct(String id) throws SQLException, ClassNotFoundException {
        return productDAO.delete(id);
    }

    @Override
    public ProductDTO searchProduct(String id) throws SQLException, ClassNotFoundException {
        Product product = productDAO.search(id);
        ProductDTO productDTO = new ProductDTO(product.getId(), product.getName(), product.getCategory(), product.getDescription(), product.getQtyAvailable(), product.getUnitPrice(), product.getDescription(), product.getPath());
        return productDTO;
    }

    @Override
    public ProductDTO searchProductByName(String name) throws SQLException, ClassNotFoundException {
        Product product = productDAO.searchByName(name);
        ProductDTO productDTO = new ProductDTO(product.getId(), product.getName(), product.getCategory(), product.getDescription(), product.getQtyAvailable(), product.getUnitPrice(), product.getDescription(), product.getPath());
        return productDTO;
    }

    @Override
    public List<String> getProductName() throws SQLException, ClassNotFoundException {
        return productDAO.getName();
    }

    @Override
    public boolean updateProductQty(String Code, int qty) throws SQLException, ClassNotFoundException {
        return productDAO.updateQty(Code, qty);
    }

    @Override
    public List<String> getSupplierIds() throws SQLException, ClassNotFoundException {
        return supplierDAO.getIds();
    }

    @Override
    public SupplierDTO searchSupplier(String id) throws SQLException, ClassNotFoundException {
        Supplier supplier = supplierDAO.search(id);
        SupplierDTO supplierDTO = new SupplierDTO(supplier.getId(), supplier.getName(), supplier.getNic(), supplier.getAddress(), supplier.getEmail(), supplier.getTel());
        return supplierDTO;
    }

    @Override
    public List<String> getDepartmentIds() throws SQLException, ClassNotFoundException {
        return departmentDAO.getIds();
    }

    @Override
    public DepartmentDTO searchDepartmentById(String id) throws SQLException, ClassNotFoundException {
        Department department = departmentDAO.search(id);
        DepartmentDTO departmentDTO = new DepartmentDTO(department.getId(), department.getName(), department.getDescription(), department.getNumOfEmp());
        return departmentDTO;
    }

    @Override
    public IngredientsProductDTO searchProductById(String id) throws SQLException, ClassNotFoundException {

        IngredientsProduct ingredientsProduct = ingredientsProductDAO.search(id);
        IngredientsProductDTO ingredientsProductDTO = new IngredientsProductDTO(ingredientsProduct.getIngredientId(), ingredientsProduct.getProductId(), ingredientsProduct.getQty(), ingredientsProduct.getUnitPrice());
        return  ingredientsProductDTO;
    }
}
