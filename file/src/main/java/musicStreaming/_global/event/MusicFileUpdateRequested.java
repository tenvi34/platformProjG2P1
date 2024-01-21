package musicStreaming._global.event;

import musicStreaming._global.infra.AbstractEvent;

import musicStreaming.sanityCheck.reqDtos.MockMusicFileUpdateRequestedReqDto;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// 음악 파일 업데이트가 요청되었음을 알리는 이벤트
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class MusicFileUpdateRequested extends AbstractEvent {
    private Long id;
    private Long fileId;
    private Date updatedDate;
    private String dataUrlCode;

    public MusicFileUpdateRequested(MockMusicFileUpdateRequestedReqDto mockData) {
        super();
        this.id = mockData.getId();
        this.fileId = mockData.getFileId();
        this.updatedDate = mockData.getUpdatedDate();
        this.dataUrlCode = mockData.getDataUrlCode();
    }

    public MusicFileUpdateRequested() {
        super();
    }
}
