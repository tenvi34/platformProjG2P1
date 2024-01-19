package musicStreaming._global.event;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import musicStreaming._global.infra.AbstractEvent;

import musicStreaming.domain.User;

// 유저 이름이 업데이트 되었음을 알리기 위한 이벤트
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class UserNameUpdated extends AbstractEvent {
    private Long id;
    private String name;
    private Date updatedDate;

    public UserNameUpdated(User aggregate) {
        super(aggregate);
    }

    public UserNameUpdated() {
        super();
    }
}
