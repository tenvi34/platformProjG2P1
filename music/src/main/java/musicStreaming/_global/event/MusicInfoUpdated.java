package musicStreaming._global.event;

import musicStreaming._global.infra.AbstractEvent;

import musicStreaming.domain.Music;

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

    public MusicInfoUpdated(Music aggregate) {
        super(aggregate);
    }

    public MusicInfoUpdated() {
        super();
    }
}
