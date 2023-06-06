package model.modelDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class ManagerAddDTO extends EntranceDTO {
    private String productName;
    private BigDecimal productPrice;
    private Long productAvailable;
}
