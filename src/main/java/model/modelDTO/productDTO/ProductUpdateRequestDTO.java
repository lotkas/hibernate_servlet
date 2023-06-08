package model.modelDTO.productDTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductUpdateRequestDTO {
    private String name;
    private BigDecimal price;
    private Long available;
}
