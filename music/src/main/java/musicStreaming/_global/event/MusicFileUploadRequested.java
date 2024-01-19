package musicStreaming._global.event;

import musicStreaming._global.infra.AbstractEvent;

import musicStreaming.domain.Music;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// 음악 정보 생성시에 음악 파일 업로드를 요구하기 위한 이벤트
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class MusicFileUploadRequested extends AbstractEvent {
    private Long id;
    private String dataUrlCode;
    private Long createrId;
    private String title;
    private String creater;
    private Integer likes;
    private Date createdDate;
    private Date updatedDate;

    public MusicFileUploadRequested(Music aggregate, String dataUrlCode) {
        super(aggregate);
        this.dataUrlCode = dataUrlCode;
    }

    public MusicFileUploadRequested() {
        super();
    }
}
