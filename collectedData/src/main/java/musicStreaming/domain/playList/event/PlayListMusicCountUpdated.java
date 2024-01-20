package musicStreaming.domain.playList.event;

import musicStreaming._global.infra.AbstractEvent;

import musicStreaming.domain.playList.sanityCheck.reqDtos.MockPlayListMusicCountUpdatedReqDto;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// 플레이 리스트 음악의 개수와 관련된 정보가 업데이트되었음을 알리는 이벤트
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class PlayListMusicCountUpdated extends AbstractEvent {
    private Long id;
    private Integer musicCount;
    private Date updatedDate;

    public PlayListMusicCountUpdated(MockPlayListMusicCountUpdatedReqDto mockData) {
        super();
        this.id = mockData.getId();
        this.musicCount = mockData.getMusicCount();
        this.updatedDate = mockData.getUpdatedDate();
    }

    public PlayListMusicCountUpdated() {
        super();
    }
}
