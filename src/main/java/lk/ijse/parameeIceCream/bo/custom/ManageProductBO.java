package lk.ijse.parameeIceCream.bo.custom;

import lk.ijse.parameeIceCream.bo.SuperBO;
import lk.ijse.parameeIceCream.dto.DepartmentDTO;
import lk.ijse.parameeIceCream.dto.IngredientDTO;
import lk.ijse.parameeIceCream.dto.IngredientsProductDTO;
import lk.ijse.parameeIceCream.dto.ProductDTO;
import lk.ijse.parameeIceCream.entity.Department;
import lk.ijse.parameeIceCream.entity.Ingredient;
import lk.ijse.parameeIceCream.entity.Product;

import java.sql.SQLException;
import java.util.List;

public interface ManageProductBO extends SuperBO {
    List<String> getDepartmentIds() throws SQLException, ClassNotFoundException;
    DepartmentDTO searchDepartmentById(String id) throws SQLException, ClassNotFoundException;
    public List<String> getIngredientNames() throws SQLException, ClassNotFoundException;
    public IngredientDTO searchIngredient(String name) throws SQLException, ClassNotFoundException;
    public List<IngredientDTO> searchIngredientById(String id) throws SQLException, ClassNotFoundException;

    public String generateNewProductID() throws SQLException, ClassNotFoundException;
    ProductDTO searchByProductName(String name) throws SQLException, ClassNotFoundException;


    IngredientsProductDTO searchByIngreProductId(String id) throws SQLException, ClassNotFoundException;
    List<String> getProductName() throws SQLException, ClassNotFoundException;

    boolean palceProduct(ProductDTO productDTO, List<IngredientsProductDTO> productDTOS) throws SQLException, ClassNotFoundException ;
    boolean updateProduct(ProductDTO productDTO,List<IngredientsProductDTO> productDTOS) throws SQLException, ClassNotFoundException;
}
