package lk.ijse.parameeIceCream.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class IngredientsProduct {
    private String productId;
    private String ingredientId;
    private int qty;
    private double unitPrice;
}
