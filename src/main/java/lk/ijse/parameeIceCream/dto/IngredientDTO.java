package lk.ijse.parameeIceCream.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class IngredientDTO {
    private String id;
    private String name;
    private String qtyInStock;
    private String unitOfMeasure;
    private double unitPrice;
    private double price;
    private String supplierId;

}
