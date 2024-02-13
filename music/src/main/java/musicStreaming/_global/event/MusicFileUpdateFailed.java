package musicStreaming._global.event;

import musicStreaming._global.infra.AbstractEvent;

import musicStreaming.sanityCheck.reqDtos.MockMusicFileUpdateFailedReqDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// 음악파일이 업데이트에 실패했음을 알리는 이벤트
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class MusicFileUpdateFailed extends AbstractEvent {
    private Long id;
    private Long musicId;
    private String dataUrlCode;

    public MusicFileUpdateFailed(MockMusicFileUpdateFailedReqDto mockData) {
        super();
        this.id = mockData.getId();
        this.musicId = mockData.getMusicId();
        this.dataUrlCode = mockData.getDataUrlCode();
    }

    public MusicFileUpdateFailed() {
        super();
    }
}
