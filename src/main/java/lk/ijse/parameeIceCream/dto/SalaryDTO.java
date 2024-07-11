package lk.ijse.parameeIceCream.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class SalaryDTO {
    private String id;
    private double basicSalary;
    private double allowences;
    private double advance;

}
