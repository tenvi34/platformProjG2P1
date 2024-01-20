package musicStreaming._global.event;

import musicStreaming._global.infra.AbstractEvent;

import musicStreaming.domain.playList.PlayList;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// 플레이 리스트가 업데이트됨을 알리는 이벤트
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class PlayListUpdated extends AbstractEvent {
    private Long id;
    private String title;
    private Date updatedDate;

    public PlayListUpdated(PlayList aggregate) {
        super(aggregate);
    }

    public PlayListUpdated() {
        super();
    }
}
