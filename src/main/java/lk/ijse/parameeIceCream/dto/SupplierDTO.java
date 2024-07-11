package lk.ijse.parameeIceCream.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class SupplierDTO {
    private String id;
    private String name;
    private String nic;
    private String address;
    private String email;
    private String tel;

}
