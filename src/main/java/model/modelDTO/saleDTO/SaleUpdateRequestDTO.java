package model.modelDTO.saleDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import model.modelDTO.EntranceDTO;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class SaleUpdateRequestDTO extends EntranceDTO {
    private Long saleId;
    private Long userId;
    private Long productId;
    private LocalDateTime addDate;
}
