package musicStreaming._global.event;

import musicStreaming._global.infra.AbstractEvent;

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

    public MusicFileUploadFailed() {
        super();
    }
}
