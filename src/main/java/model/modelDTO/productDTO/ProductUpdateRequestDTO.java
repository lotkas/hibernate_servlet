package model.modelDTO.productDTO;

import lombok.Data;
import model.modelDTO.EntranceDTO;

import java.math.BigDecimal;

@Data
public class ProductUpdateRequestDTO extends EntranceDTO {
    private Long productId;
    private String updatedName;
    private BigDecimal updatedPrice;
    private Long updatedAvailable;
}
