package lk.ijse.parameeIceCream.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
public class CustomDTO {
    private String date;
    private int count;

    private String productId;
    private int orderCount;
    private int sumQty;

    public CustomDTO(String date,int count){
        this.date = date;
        this.count = count;
    }
    public CustomDTO(String productId, int orderCount,int sumQty){
        this.productId = productId;
        this.orderCount = orderCount;
        this.sumQty = sumQty;
    }
}
