package model.modelDTO.userDTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserSaveRequestDTO {
    private String firstName;
    private String lastName;
    private String password;
    private BigDecimal balance;
}
