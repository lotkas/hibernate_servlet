package model.modelDTO.productDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import model.modelDTO.EntranceDTO;

@EqualsAndHashCode(callSuper = true)
@Data
public class ProductDeleteByIdRequestDTO extends EntranceDTO {
    private Long productId;
}
