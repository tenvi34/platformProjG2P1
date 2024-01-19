package musicStreaming._global.event;

import musicStreaming._global.infra.AbstractEvent;
import musicStreaming.sanityCheck.reqDtos.MockMusicFileUploadedReqDto;

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
    private String dataUrlCode;

    public MusicFileUploaded(MockMusicFileUploadedReqDto mockData) {
        super();
        this.id = mockData.getId();
        this.createrId = mockData.getCreaterId();
        this.path = mockData.getPath();
        this.createdDate = mockData.getCreatedDate();
        this.updatedDate = mockData.getUpdatedDate();
        this.totalSeconds = mockData.getTotalSeconds();
        this.dataUrlCode = mockData.getDataUrlCode();
    }

    public MusicFileUploaded() {
        super();
    }
}
