package lk.ijse.parameeIceCream.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class EmployeeDTO {
    private String id;
    private String name;
    private String nic;
    private String address;
    private String email;
    private String tel;
    private Date dob;
    private Date registerDate;
    private String position;
    private double salary;
    private String departmentId;
    private String path;
}
