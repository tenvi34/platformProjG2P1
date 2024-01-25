package musicStreaming.domain.file.event;

import musicStreaming._global.infra.AbstractEvent;

import musicStreaming.domain.file.sanityCheck.reqDtos.MockMusicFileUploadFailedReqDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// 음악파일이 업로드에 실패했음을 알리는 이벤트
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class MusicFileUploadFailed extends AbstractEvent {
    private Long id;
    private Long musicId;
    private String dataUrlCode;

    public MusicFileUploadFailed(MockMusicFileUploadFailedReqDto mockData) {
        super();
        this.id = mockData.getId();
        this.musicId = mockData.getMusicId();
        this.dataUrlCode = mockData.getDataUrlCode();
    }

    public MusicFileUploadFailed() {
        super();
    }
}
