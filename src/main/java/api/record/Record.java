package api.record;


import api.config.CustomUserDetails;
import api.notebook.Notebook;
import api.config.users.Customer;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Long id;
    private Date date;
    private String explanation;
    private BigDecimal price;
    private Boolean active;


    @ManyToOne
    @JoinColumn(name = "notebook_id")
    private Notebook notebook;

    @Enumerated(EnumType.STRING)
    private RecordStatus status = RecordStatus.Waiting;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private CustomUserDetails customer;

}
