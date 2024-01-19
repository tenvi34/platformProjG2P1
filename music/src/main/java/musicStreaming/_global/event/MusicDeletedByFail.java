package musicStreaming._global.event;

import musicStreaming._global.infra.AbstractEvent;

import musicStreaming.domain.Music;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// 음악 파일 업로드 실패시에 이로 인해서 음악 정보가 삭제되었음을 알리는 이벤트
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class MusicDeletedByFail extends AbstractEvent {
    private Long id;

    public MusicDeletedByFail(Music aggregate) {
        super(aggregate);
    }

    public MusicDeletedByFail() {
        super();
    }
}
