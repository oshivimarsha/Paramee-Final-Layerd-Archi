package lk.ijse.parameeIceCream.bo.custom;

import lk.ijse.parameeIceCream.bo.SuperBO;
import lk.ijse.parameeIceCream.dto.*;
import lk.ijse.parameeIceCream.entity.CreateProduct;
import lk.ijse.parameeIceCream.entity.IngredientsProduct;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface IngredientsBO extends SuperBO {
    ArrayList<IngredientDTO> getAllIngredient() throws SQLException, ClassNotFoundException;
    boolean saveIngredient(IngredientDTO ingredient) throws SQLException, ClassNotFoundException;
    boolean updateIngredient(IngredientDTO ingredient) throws SQLException, ClassNotFoundException;
    boolean updateQtyMinIngredient(String id, int qty) throws SQLException , ClassNotFoundException;
    boolean updateQtyPlusIngredient(String id, int qty) throws SQLException, ClassNotFoundException;
    boolean existIngredient(String id) throws SQLException, ClassNotFoundException;
    String generateNewID() throws SQLException, ClassNotFoundException;
    boolean deleteIngredient(String name) throws SQLException, ClassNotFoundException;
    IngredientDTO searchIngredient(String name) throws SQLException, ClassNotFoundException;
    List<IngredientDTO> searchIngredientById(String id) throws SQLException, ClassNotFoundException;
    List<String> getIngredientNames() throws SQLException, ClassNotFoundException;
    boolean updatePlusIngredient(List<IngredientsProduct> ipList) throws SQLException, ClassNotFoundException;
    boolean updateMinIngredient(List<IngredientsProduct> ipList) throws SQLException, ClassNotFoundException;
    ArrayList<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException;
    boolean saveProduct(ProductDTO product) throws SQLException, ClassNotFoundException;
    boolean updateProduct(ProductDTO product) throws SQLException, ClassNotFoundException;
    boolean existProduct(String id) throws SQLException, ClassNotFoundException;
    String generateProductNewID() throws SQLException, ClassNotFoundException;
    boolean deleteProduct(String id) throws SQLException, ClassNotFoundException;
    ProductDTO searchProduct(String id) throws SQLException, ClassNotFoundException;
    ProductDTO searchProductByName(String name) throws SQLException, ClassNotFoundException;
    List<String> getProductName() throws SQLException, ClassNotFoundException;
    boolean updateProductQty(String Code, int qty) throws SQLException, ClassNotFoundException;
    List<String> getSupplierIds() throws SQLException, ClassNotFoundException;
    SupplierDTO searchSupplier(String id) throws SQLException, ClassNotFoundException;
    List<String> getDepartmentIds() throws SQLException, ClassNotFoundException;
    DepartmentDTO searchDepartmentById(String id) throws SQLException, ClassNotFoundException;
    IngredientsProductDTO searchProductById(String id) throws SQLException, ClassNotFoundException;
}
