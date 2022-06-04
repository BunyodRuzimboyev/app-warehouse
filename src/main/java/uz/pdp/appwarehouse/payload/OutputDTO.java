package uz.pdp.appwarehouse.payload;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class OutputDTO {
    private Timestamp date;
    private Integer clientId;
    private Integer warehouseId;
    private String factureNumber;
    private String code;
}
