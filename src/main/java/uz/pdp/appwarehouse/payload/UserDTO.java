package uz.pdp.appwarehouse.payload;

import lombok.Data;

@Data
public class UserDTO {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String code;
    private String password;
    private Integer warehouseId;
    private Boolean active;
}
