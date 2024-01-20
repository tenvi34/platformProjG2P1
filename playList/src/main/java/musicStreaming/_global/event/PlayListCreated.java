package musicStreaming._global.event;

import musicStreaming._global.infra.AbstractEvent;

import musicStreaming.domain.playList.PlayList;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// 플레이 리스트가 생성됨을 알리는 이벤트
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class PlayListCreated extends AbstractEvent {
    private Long id;
    private Long createrId;
    private String title;
    private Integer musicCount;
    private Date createdDate;
    private Date updatedDate;

    public PlayListCreated(PlayList aggregate) {
        super(aggregate);
    }

    public PlayListCreated() {
        super();
    }
}
