package musicStreaming.domain.playList.event;

import musicStreaming._global.infra.AbstractEvent;

import musicStreaming.domain.playList.sanityCheck.reqDtos.MockPlayListCreatedReqDto;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// 플레이 리스트가 생성됨을 알리는 이벤트
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class PlayListCreated extends AbstractEvent {
    private Long id;
    private Long createrId;
    private String title;
    private Integer musicCount;
    private Date createdDate;
    private Date updatedDate;

    public PlayListCreated(MockPlayListCreatedReqDto mockData) {
        super();
        this.id = mockData.getId();
        this.createrId = mockData.getCreaterId();
        this.title = mockData.getTitle();
        this.musicCount = mockData.getMusicCount();
        this.createdDate = mockData.getCreatedDate();
        this.updatedDate = mockData.getUpdatedDate();
    }

    public PlayListCreated() {
        super();
    }
}
