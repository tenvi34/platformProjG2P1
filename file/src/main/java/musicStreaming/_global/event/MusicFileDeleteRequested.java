package musicStreaming._global.event;

import musicStreaming._global.infra.AbstractEvent;

import musicStreaming.sanityCheck.reqDtos.MockMusicFileDeleteRequestedReqDto;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// 음악 파일 삭제가 요청되었음을 알리는 이벤트
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class MusicFileDeleteRequested extends AbstractEvent {
    private Long id;
    private Long fileId;
    private Date updatedDate;

    public MusicFileDeleteRequested(MockMusicFileDeleteRequestedReqDto mockData) {
        super();
        this.id = mockData.getId();
        this.fileId = mockData.getFileId();
        this.updatedDate = mockData.getUpdatedDate();
    }

    public MusicFileDeleteRequested() {
        super();
    }
}
