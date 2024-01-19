package musicStreaming._global.event;

import musicStreaming._global.infra.AbstractEvent;

import musicStreaming.domain.Music;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// 음악 정보가 삭제되었음을 알리는 이벤트
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class MusicDeleted extends AbstractEvent {
    private Long id;

    public MusicDeleted(Music aggregate) {
        super(aggregate);
    }

    public MusicDeleted() {
        super();
    }
}
