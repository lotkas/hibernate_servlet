package model.modelDTO.saleDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import model.modelDTO.EntranceDTO;

@EqualsAndHashCode(callSuper = true)
@Data
public class SaleSaveRequestDTO extends EntranceDTO {
    private Long productId;
}
