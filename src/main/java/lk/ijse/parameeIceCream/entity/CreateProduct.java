package lk.ijse.parameeIceCream.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

public class CreateProduct {
    private Product product;
    private List<IngredientsProduct> IPList;
}
