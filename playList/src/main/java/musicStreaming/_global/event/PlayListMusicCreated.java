package musicStreaming._global.event;

import musicStreaming._global.infra.AbstractEvent;

import musicStreaming.domain.playListMusic.PlayListMusic;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// 플레이 리스트 음악이 생성됨을 알리는 이벤트
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class PlayListMusicCreated extends AbstractEvent {
    private Long id;
    private Long playListId;
    private Long musicId;
    private Long createrId;
    private String title;
    private Date createdDate;
    private Date updatedDate;

    public PlayListMusicCreated(PlayListMusic aggregate) {
        super(aggregate);
    }

    public PlayListMusicCreated() {
        super();
    }
}
