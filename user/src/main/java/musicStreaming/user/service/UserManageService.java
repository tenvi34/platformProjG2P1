package musicStreaming.user.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import musicStreaming.domain.User;
import musicStreaming.domain.UserRepository;
import musicStreaming.user.UserNotFoundException;

@Service
@RequiredArgsConstructor
public class UserManageService {
    private final UserRepository userRepository;

    public User findByEmail(String email) {
        return this.userRepository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException());
    }
}
