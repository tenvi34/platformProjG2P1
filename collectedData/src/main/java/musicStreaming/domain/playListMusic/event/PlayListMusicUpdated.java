package musicStreaming.domain.playListMusic.event;

import musicStreaming._global.infra.AbstractEvent;

import musicStreaming.domain.playListMusic.sanityCheck.reqDtos.MockPlayListMusicUpdatedReqDto;

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

    public PlayListMusicUpdated(MockPlayListMusicUpdatedReqDto mockData) {
        super();
        this.id = mockData.getId();
        this.title = mockData.getTitle();
        this.updatedDate = mockData.getUpdatedDate();
    }

    public PlayListMusicUpdated() {
        super();
    }
}
