package musicStreaming._global.event;

import musicStreaming._global.infra.AbstractEvent;

import musicStreaming.domain.Music;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// 음악 정보가 좋아요가 눌러졌음을 알리는 이벤트
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class MusicLiked extends AbstractEvent {
    private Long id;
    private Integer likes;

    public MusicLiked(Music aggregate) {
        super(aggregate);
    }

    public MusicLiked() {
        super();
    }
}
