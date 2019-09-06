package api.config.users;

import api.config.CustomUserDetails;
import api.record.Record;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class Customer extends CustomUserDetails {
    //@Id
   // @GeneratedValue(strategy = GenerationType.IDENTITY)
    /*@Column(name = "costomer_id")
    private Long id;
    private String name;*/





}
