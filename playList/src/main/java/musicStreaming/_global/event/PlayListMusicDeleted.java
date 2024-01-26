package musicStreaming._global.event;

import musicStreaming._global.infra.AbstractEvent;

import musicStreaming.domain.playListMusic.PlayListMusic;

import musicStreaming.sanityCheck.reqDtos.MockPlayListMusicDeletedReqDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// 플레이 리스트 음악이 삭제됨을 알리는 이벤트
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class PlayListMusicDeleted extends AbstractEvent {
    private Long id;
    private Long playListId;

    public PlayListMusicDeleted(PlayListMusic aggregate) {
        super(aggregate);
    }

    public PlayListMusicDeleted(MockPlayListMusicDeletedReqDto mockData) {
        super();
        this.id = mockData.getId();
        this.playListId = mockData.getPlayListId();
    }

    public PlayListMusicDeleted() {
        super();
    }
}
