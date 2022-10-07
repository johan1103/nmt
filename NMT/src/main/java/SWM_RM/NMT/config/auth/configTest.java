package SWM_RM.NMT.config.auth;

import SWM_RM.NMT.domain.dto.UserDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class configTest {
    @Bean
    public UserDTO configurationTest(){
        return new UserDTO();
    }
}
