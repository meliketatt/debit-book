package api.config;

import api.record.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomUserDetailRepository extends JpaRepository<CustomUserDetails, Long> {

    public CustomUserDetails findByUsername(String userName);

    @Query(value = "select * from users  where username = ?1 ", nativeQuery = true)
    CustomUserDetails findByUserName(String currentUserName);

    /*@Query(value = "select * from users  where username LIKE ?1 ", nativeQuery = true)
    CustomUserDetails findByUserName(String userName);*/
    @Query("FROM CustomUserDetails c WHERE c.type = 'CUSTOMER' ")
    public List<CustomUserDetails> getCustomers();

    @Query("FROM CustomUserDetails c WHERE c.type = 'PRODUCER' ")
    public List<CustomUserDetails> getProducers();

}
