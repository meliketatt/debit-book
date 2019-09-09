package api.notebook;

import api.config.CustomUserDetails;
import api.record.Record;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;


import javax.persistence.*;
import java.util.List;


@Entity
@Data
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Notebook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notebook_id")
    private Long id;

    /*@OneToOne(mappedBy = "producer",orphanRemoval = true, cascade = CascadeType.ALL)
    private Notebook notebook;*/

    @OneToMany(mappedBy = "notebook",orphanRemoval = true)
    private List<Record> recordList;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private CustomUserDetails producer;
}
