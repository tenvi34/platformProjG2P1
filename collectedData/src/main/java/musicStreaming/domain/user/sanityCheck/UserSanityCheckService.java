package musicStreaming.domain.user.sanityCheck;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import musicStreaming.domain.user.event.SignUpCompleted;
import musicStreaming.domain.user.event.UserNameUpdated;
import musicStreaming.domain.user.sanityCheck.reqDtos.MockSignUpCompletedReqDto;
import musicStreaming.domain.user.sanityCheck.reqDtos.MockUserNameUpdatedReqDto;

@Service
@RequiredArgsConstructor
public class UserSanityCheckService {

    // Policy 테스트용으로 SignUpCompleted 이벤트를 강제로 발생시키기 위해서
    public void mockSignUpCompleted(MockSignUpCompletedReqDto mockData) {
        (new SignUpCompleted(mockData)).publish();
    }

    // Policy 테스트용으로 UserNameUpdated 이벤트를 강제로 발생시키기 위해서
    public void mockUserNameUpdated(MockUserNameUpdatedReqDto mockData) {
        (new UserNameUpdated(mockData)).publish();
    }

}
