package model.modelDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ManagerDeleteDTO extends EntranceDTO {
    private Long productId;
}
