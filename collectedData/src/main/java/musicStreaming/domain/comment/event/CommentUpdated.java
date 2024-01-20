package musicStreaming.domain.comment.event;

import musicStreaming._global.infra.AbstractEvent;

import musicStreaming.domain.comment.sanityCheck.reqDtos.MockCommentUpdatedReqDto;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// 코멘트가 수정되었을 경우 발생하는 이벤트
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class CommentUpdated extends AbstractEvent {
    private Long id;
    private String content;
    private Date updatedDate;

    public CommentUpdated(MockCommentUpdatedReqDto mockData) {
        super();
        this.id = mockData.getId();
        this.content = mockData.getContent();
        this.updatedDate = mockData.getUpdatedDate();
    }

    public CommentUpdated() {
        super();
    }
}
