package musicStreaming.domain.music.event;

import musicStreaming._global.infra.AbstractEvent;

import musicStreaming.domain.music.sanityCheck.reqDtos.MockMusicCreatedReqDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// 음악 파일 업로드가 완료되고, 최종적으로 음악 정보 생성이 완료되었을 경우 발생하는 이벤트
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class MusicCreated extends AbstractEvent {
    private Long id;
    private Long fileId;
    private Integer totalSeconds;

    public MusicCreated(MockMusicCreatedReqDto mockData) {
        super();
        this.id = mockData.getId();
        this.fileId = mockData.getFileId();
        this.totalSeconds = mockData.getTotalSeconds();
    }

    public MusicCreated() {
        super();
    }
}
