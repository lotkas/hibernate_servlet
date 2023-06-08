package model.modelDTO.productDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import model.modelDTO.EntranceDTO;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductSaveRequestDTO extends EntranceDTO {
    private String name;
    private BigDecimal price;
    private Long available;
}
