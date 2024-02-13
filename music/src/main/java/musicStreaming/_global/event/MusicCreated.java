package musicStreaming._global.event;

import musicStreaming._global.infra.AbstractEvent;
import musicStreaming.domain.Music;

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

    public MusicCreated(Music aggregate, Integer totalSeconds) {
        super(aggregate);
        this.totalSeconds = totalSeconds;
    }

    public MusicCreated() {
        super();
    }
}
