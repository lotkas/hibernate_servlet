package model.modelDTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ManagerAddDTO extends EntranceDTO {
    private String productName;
    private BigDecimal productPrice;
    private Long productAvailable;
}
