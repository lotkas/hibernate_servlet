package model.modelDTO.userDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import model.modelDTO.EntranceDTO;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserUpdateDTO extends EntranceDTO {
    private String updatedFirstName;
    private String updatedLastName;
    private String updatedPassword;
    private BigDecimal updatedBalance;
}
