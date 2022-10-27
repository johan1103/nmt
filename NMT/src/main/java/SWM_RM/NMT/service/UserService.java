package SWM_RM.NMT.service;

import SWM_RM.NMT.domain.User;
import SWM_RM.NMT.domain.dto.UserDTO;
import SWM_RM.NMT.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserDTO findUserService(Long userId){
        User user= userRepository.findUserById(userId);
        return UserDTO.userDtoConverter(user);
    }
}
