package lk.ijse.parameeIceCream.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class DepartmentDTO {
    private String id;
    private String name;
    private String description;
    private int numOfEmp;

}
