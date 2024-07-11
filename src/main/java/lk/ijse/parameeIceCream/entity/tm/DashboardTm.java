package lk.ijse.parameeIceCream.entity.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class DashboardTm {
    private String productId;
    private int orderId;
    private int qty;

}
