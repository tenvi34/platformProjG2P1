package musicStreaming._global.event;

import musicStreaming._global.infra.AbstractEvent;

import musicStreaming.domain.playListMusic.PlayListMusic;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// 플레이 리스트 음악이 업데이트 되었음을 알리는 이벤트
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class PlayListMusicUpdated extends AbstractEvent {
    private Long id;
    private String title;
    private Date updatedDate;

    public PlayListMusicUpdated(PlayListMusic aggregate) {
        super(aggregate);
    }

    public PlayListMusicUpdated() {
        super();
    }
}
