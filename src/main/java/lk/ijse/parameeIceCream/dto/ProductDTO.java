package lk.ijse.parameeIceCream.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductDTO {
    private String id;
    private String name;
    private String category;
    private String description;
    private int qtyAvailable;
    private double unitPrice;
    private String departmentId;
    private String path;
}
