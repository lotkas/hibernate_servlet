package model.modelDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ManagerUpdateDTO extends EntranceDTO {
    private Long productId;
    private Long available;
}
