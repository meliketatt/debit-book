package api.record;


import api.config.CustomUserDetails;
import api.notebook.Notebook;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
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
    @JoinColumn(name = "customer_id")
    private CustomUserDetails customer;

    @ManyToOne
    @JoinColumn(name = "producer_id")
    private CustomUserDetails producer;

}
