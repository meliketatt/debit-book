package api.record;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class RecordDto {
    private Long id;
    private Date date;
    private String explanation;
    private BigDecimal price;
    private Boolean active;

}
