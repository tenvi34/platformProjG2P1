package musicStreaming.domain.comment.event;

import musicStreaming._global.infra.AbstractEvent;

import musicStreaming.domain.comment.sanityCheck.reqDtos.MockCommentCreatedReqDto;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// 코멘트가 생성되었을 경우 발생하는 이벤트
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class CommentCreated extends AbstractEvent {
    private Long id;
    private Long createrId;
    private Long musicId;
    private String content;
    private Date createdDate;
    private Date updatedDate;

    public CommentCreated(MockCommentCreatedReqDto mockData) {
        super();
        this.id = mockData.getId();
        this.createrId = mockData.getCreaterId();
        this.musicId = mockData.getMusicId();
        this.content = mockData.getContent();
        this.createdDate = mockData.getCreatedDate();
        this.updatedDate = mockData.getUpdatedDate();
    }

    public CommentCreated() {
        super();
    }
}
