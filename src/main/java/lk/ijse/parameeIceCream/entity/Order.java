package lk.ijse.parameeIceCream.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {
    private String id;
    private Date date;
    private double unitPrice;
    private String customrId;
}
