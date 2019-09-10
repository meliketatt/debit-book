package api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    @Autowired
    CustomUserDetailRepository customUserDetailRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return customUserDetailRepository.findByUserName(userName);
    }
    public List<CustomUserDetails> getCustomers() {
        return customUserDetailRepository.getCustomers();
    }
    public List<CustomUserDetails> getProducers() {
        return customUserDetailRepository.getProducers();
    }
}
