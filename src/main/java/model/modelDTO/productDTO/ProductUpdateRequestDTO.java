package model.modelDTO.productDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import model.modelDTO.EntranceDTO;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductUpdateRequestDTO extends EntranceDTO {
    private Long productId;
    private String updatedName;
    private BigDecimal updatedPrice;
    private Long updatedAvailable;
}
