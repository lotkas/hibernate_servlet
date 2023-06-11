package model.modelDTO.userDTO;

import lombok.Data;

@Data
public class UserIdRequestDTO {
    private Long userId;
    private boolean getAll;
}
