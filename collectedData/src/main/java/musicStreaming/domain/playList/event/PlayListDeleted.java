package musicStreaming.domain.playList.event;

import musicStreaming._global.infra.AbstractEvent;

import musicStreaming.domain.playList.sanityCheck.reqDtos.MockPlayListDeletedReqDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// 플레이 리스트가 삭제됨을 알리는 이벤트
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class PlayListDeleted extends AbstractEvent {
    private Long id;

    public PlayListDeleted(MockPlayListDeletedReqDto mockData) {
        super();
        this.id = mockData.getId();
    }

    public PlayListDeleted() {
        super();
    }
}
