package model.modelDTO.productDTO;

import lombok.Data;

@Data
public class ProductIdRequestDTO {
    private Long productId;
    private boolean getAll;
}
