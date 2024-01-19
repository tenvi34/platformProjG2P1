package musicStreaming._global.event;

import musicStreaming._global.infra.AbstractEvent;
import musicStreaming.domain.File;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// 음악파일이 삭제에 실패했음을 알리는 이벤트
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class MusicFileDeleteFailed extends AbstractEvent {
    private Long id;
    private Long musicId;
    
    public MusicFileDeleteFailed(File aggregate, Long musicId) {
        super(aggregate);
        this.musicId = musicId;
    }

    public MusicFileDeleteFailed() {
        super();
    }
}
