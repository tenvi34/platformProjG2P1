package musicStreaming._global.event;

import musicStreaming._global.infra.AbstractEvent;
import musicStreaming.domain.File;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// 음악파일이 업데이트되었음을 알리는 이벤트
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class MusicFileUpdated extends AbstractEvent {
    private Long id;
    private Long createrId;
    private Long musicId;
    private String path;
    private Date createdDate;
    private Date updatedDate;
    private Integer totalSeconds;
    private String dataUrlCode;

    public MusicFileUpdated(File aggregate, Long musicId, Integer totalSeconds, String dataUrlCode) {
        super(aggregate);
        this.musicId = musicId;
        this.totalSeconds = totalSeconds;
        this.dataUrlCode = dataUrlCode;
    }

    public MusicFileUpdated() {
        super();
    }
}
