package lk.ijse.parameeIceCream.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetailDTO {
    private String orderId;
    private String productId;
    private int qty;
    private double unitPrice;
}
