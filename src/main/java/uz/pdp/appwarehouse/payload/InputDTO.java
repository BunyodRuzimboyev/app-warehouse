package uz.pdp.appwarehouse.payload;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class InputDTO {
    private Timestamp date;
    private Integer supplierId;
    private Integer warehouseId;
    private Integer currencyId;
    private String factureNumber;
    private String code;
}
