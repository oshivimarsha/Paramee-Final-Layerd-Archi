package lk.ijse.parameeIceCream.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Ingredient {
    private String id;
    private String name;
    private String qtyInStock;
    private String unitOfMeasure;
    private double unitPrice;
    private double price;
    private String supplierId;

}
