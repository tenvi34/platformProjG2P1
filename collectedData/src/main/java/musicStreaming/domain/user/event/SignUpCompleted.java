package musicStreaming.domain.user.event;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import musicStreaming._global.infra.AbstractEvent;

import musicStreaming.domain.user.sanityCheck.reqDtos.MockSignUpCompletedReqDto;

// 회원가입 정보 등록이 완료됨을 알리기 위한 이벤트
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class SignUpCompleted extends AbstractEvent {
    private Long id;
    private String email;
    private String name;
    private String role;
    private Date createdDate;
    private Date updatedDate;

    public SignUpCompleted(MockSignUpCompletedReqDto mockData) {
        super();
        this.id = mockData.getId();
        this.email = mockData.getEmail();
        this.name = mockData.getName();
        this.role = mockData.getRole();
        this.createdDate = mockData.getCreatedDate();
        this.updatedDate = mockData.getUpdatedDate();
    }

    public SignUpCompleted() {
        super();
    }
}
