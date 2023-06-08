package model.modelDTO.managerDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import model.modelDTO.EntranceDTO;

@EqualsAndHashCode(callSuper = true)
@Data
public class ManagerUpdateRequestDTO extends EntranceDTO {
    private String updatedFirstName;
    private String updatedLastName;
    private String updatedPassword;
}
