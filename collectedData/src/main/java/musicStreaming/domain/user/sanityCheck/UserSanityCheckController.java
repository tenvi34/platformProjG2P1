package musicStreaming.domain.user.sanityCheck;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

import musicStreaming.domain.user.sanityCheck.reqDtos.MockSignUpCompletedReqDto;
import musicStreaming.domain.user.sanityCheck.reqDtos.MockUserNameUpdatedReqDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/sanityCheck")
public class UserSanityCheckController {
    private final UserSanityCheckService userSanityCheckService;

    // Policy 테스트용으로 SignUpCompleted 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/SignUpCompleted")
    public void mockSignUpCompleted(@RequestBody MockSignUpCompletedReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.userSanityCheckService.mockSignUpCompleted(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }

    // Policy 테스트용으로 UserNameUpdated 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/UserNameUpdated")
    public void mockUserNameUpdated(@RequestBody MockUserNameUpdatedReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.userSanityCheckService.mockUserNameUpdated(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }
}
