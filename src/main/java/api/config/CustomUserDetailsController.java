package api.config;

import api.config.CustomUserDetails;
import api.config.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/debit")
public class CustomUserDetailsController {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @GetMapping("/get-customers")
    public List<CustomUserDetails> getCustomers()  {
        return customUserDetailsService.getCustomers();
    }
    @GetMapping("/get-producers")
    public List<CustomUserDetails> getProducers()  {
        return customUserDetailsService.getProducers();
    }

}
