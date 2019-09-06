package api.config;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomUserDetailRepository extends JpaRepository<CustomUserDetails, Long> {

    public CustomUserDetails findByUsername(String userName);

    @Query(value = "select * from CUSTOM_USER_DETAILS  where username = ?1 ", nativeQuery = true)
    CustomUserDetails findByUserName(String currentUserName);


}
