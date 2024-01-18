package musicStreaming.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import musicStreaming._global.security.JwtTokenService;

import musicStreaming.domain.User;
import musicStreaming.domain.UserRepository;
import musicStreaming.user.reqDtos.SignInReqDto;
import musicStreaming.user.reqDtos.SignUpReqDto;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtTokenService jwtTokenService;
    private final PasswordEncoder passwordEncoder; // Spring Security에서 사용하는 PasswordEncoder를 활용하기 위해서

    
    public User signUp(SignUpReqDto signUpReqDto) {
        User savedUser = this.userRepository.save(
                User.builder()
                    .email(signUpReqDto.getEmail())
                    .password(this.passwordEncoder.encode(signUpReqDto.getPassword()))
                    .name(signUpReqDto.getName())
                    .build()
            );

        return savedUser;
    }

    public String tokenBySignIn(SignInReqDto signInReqDto) {
        return this.jwtTokenService.tokenValue(signInReqDto);
    }
}
