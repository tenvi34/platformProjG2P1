package musicStreaming.domain.user.event;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import musicStreaming._global.infra.AbstractEvent;

import musicStreaming.domain.user.sanityCheck.reqDtos.MockUserNameUpdatedReqDto;

// 유저 이름이 업데이트 되었음을 알리기 위한 이벤트
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class UserNameUpdated extends AbstractEvent {
    private Long id;
    private String name;
    private Date updatedDate;

    public UserNameUpdated(MockUserNameUpdatedReqDto mockData) {
        super();
        this.id = mockData.getId();
        this.name = mockData.getName();
        this.updatedDate = mockData.getUpdatedDate();
    }

    public UserNameUpdated() {
        super();
    }
}
