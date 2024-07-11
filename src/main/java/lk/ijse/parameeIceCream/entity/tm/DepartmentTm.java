package lk.ijse.parameeIceCream.entity.tm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class DepartmentTm {
    private String id;
    private String name;
    private String description;
    private int numOfEmp;
}
