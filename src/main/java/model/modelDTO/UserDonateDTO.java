package model.modelDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDonateDTO extends EntranceDTO {
    private BigDecimal balance;
}
