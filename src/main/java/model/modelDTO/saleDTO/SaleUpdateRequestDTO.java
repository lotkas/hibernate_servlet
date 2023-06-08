package model.modelDTO.saleDTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SaleUpdateRequestDTO {
    private Long userId;
    private Long productId;
    private LocalDateTime addDate;
}
