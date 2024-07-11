package lk.ijse.parameeIceCream.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetail {
    private String orderId;
    private String productId;
    private int qty;
    private double unitPrice;
}
