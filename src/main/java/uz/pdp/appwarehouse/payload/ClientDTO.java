package uz.pdp.appwarehouse.payload;

import lombok.Data;

@Data
public class ClientDTO {
    private String name;
    private Boolean active;
    private String phoneNumber;
}
