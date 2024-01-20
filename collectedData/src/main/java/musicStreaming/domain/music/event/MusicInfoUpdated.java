package musicStreaming.domain.music.event;

import musicStreaming._global.infra.AbstractEvent;

import musicStreaming.domain.music.sanityCheck.reqDtos.MockMusicInfoUpdatedReqDto;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// 음악 정보가 업데이트 되었음을 알리는 이벤트
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class MusicInfoUpdated extends AbstractEvent {
    private Long id;
    private String title;
    private Date updatedDate;

    public MusicInfoUpdated(MockMusicInfoUpdatedReqDto mockData) {
        super();
        this.id = mockData.getId();
        this.title = mockData.getTitle();
        this.updatedDate = mockData.getUpdatedDate();
    }

    public MusicInfoUpdated() {
        super();
    }
}
