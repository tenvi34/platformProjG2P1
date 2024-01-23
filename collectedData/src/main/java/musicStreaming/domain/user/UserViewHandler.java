package musicStreaming.domain.user;

import java.util.Optional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import musicStreaming._global.config.kafka.KafkaProcessor;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;
import musicStreaming.domain.user.event.SignUpCompleted;
import musicStreaming.domain.user.event.UserNameUpdated;
import musicStreaming.domain.user.exceptions.UserNotFoundException;

@Service
@RequiredArgsConstructor
public class UserViewHandler {
    private final UserRepository userRepository;

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='SignUpCompleted'"
    )
    public void whenSignUpCompleted_then_createUser(
        @Payload SignUpCompleted signUpCompleted
    ) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", signUpCompleted.getClass().getSimpleName(), signUpCompleted.toString()));
            if (!signUpCompleted.validate()) return;

            User savedUser = this.userRepository.save(
                User.builder()
                    .userId(signUpCompleted.getId())
                    .email(signUpCompleted.getEmail())
                    .name(signUpCompleted.getName())
                    .role(signUpCompleted.getRole())
                    .createdDate(signUpCompleted.getCreatedDate())
                    .updatedDate(signUpCompleted.getUpdatedDate())
                    .build()
            );

            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{savedUser: %s}", savedUser.toString()));

        } catch (Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", signUpCompleted.getClass().getSimpleName(), signUpCompleted.toString()));
        }
    }

    
    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='UserNameUpdated'"
    )
    public void whenUserNameUpdated_then_updateUserName(
        @Payload UserNameUpdated userNameUpdated
    ) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", userNameUpdated.getClass().getSimpleName(), userNameUpdated.toString()));
            if (!userNameUpdated.validate()) return;

            Optional<User> optionalUser = this.userRepository.findByUserId(userNameUpdated.getId());
            if(!optionalUser.isPresent()) throw new UserNotFoundException();


            User userToUpdate = optionalUser.get();
            userToUpdate.setName(userNameUpdated.getName());
            userToUpdate.setUpdatedDate(userNameUpdated.getUpdatedDate());
            User savedUser = this.userRepository.save(userToUpdate);


            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{savedUser: %s}", savedUser.toString()));

        } catch (Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", userNameUpdated.getClass().getSimpleName(), userNameUpdated.toString()));
        }
    }
}
