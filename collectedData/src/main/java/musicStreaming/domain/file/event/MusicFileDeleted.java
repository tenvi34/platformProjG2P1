package musicStreaming.domain.file.event;

import musicStreaming._global.infra.AbstractEvent;

import musicStreaming.domain.file.sanityCheck.reqDtos.MockMusicFileDeletedReqDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// 음악파일이 삭제되었음을 알리는 이벤트
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class MusicFileDeleted extends AbstractEvent {
    private Long id;
    private Long musicId;

    public MusicFileDeleted(MockMusicFileDeletedReqDto mockData) {
        super();
        this.id = mockData.getId();
        this.musicId = mockData.getMusicId();
    }

    public MusicFileDeleted() {
        super();
    }
}
