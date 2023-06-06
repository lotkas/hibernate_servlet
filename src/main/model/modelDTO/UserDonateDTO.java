package model.modelDTO;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class UserDonateDTO extends EntranceDTO {
    private BigDecimal balance;
}
