package lk.ijse.parameeIceCream.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class IngredientsProductDTO {
    private String productId;
    private String ingredientId;
    private int qty;
    private double unitPrice;
}
