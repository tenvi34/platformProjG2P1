package musicStreaming._global.event;

import musicStreaming._global.infra.AbstractEvent;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// 음악파일이 업로드되었음을 알리는 이벤트
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class MusicFileUploaded extends AbstractEvent {
    private Long id;
    private Long createrId;
    private String path;
    private Date createdDate;
    private Date updatedDate;
    private Integer totalSeconds;

    public MusicFileUploaded() {
        super();
    }
}
