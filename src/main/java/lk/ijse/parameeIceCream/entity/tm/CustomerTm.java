package lk.ijse.parameeIceCream.entity.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class CustomerTm {
    private String id;
    private String name;
    private String nic;
    private String address;
    private String tel;
    private String email;
}
