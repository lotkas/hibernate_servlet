package model.modelDTO;

import lombok.Data;

@Data
public class ManagerUpdateDTO extends EntranceDTO {
    private Long productId;
    private Long available;
}
