package api.config.users;

import api.config.CustomUserDetailRepository;
import api.config.CustomUserDetails;
import api.config.CustomUserDetailsService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    CustomUserDetailRepository customUserDetail;


    public CustomUserDetails getCurrentUSer() {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails userDetail = null;
        if ( !(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            if (Strings.isNotEmpty(currentUserName)) {
                userDetail = customUserDetail.findByUsername(currentUserName);
            }
        }
        return userDetail;
    }

}


