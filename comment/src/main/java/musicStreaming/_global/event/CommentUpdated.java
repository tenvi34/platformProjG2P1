package musicStreaming._global.event;

import musicStreaming._global.infra.AbstractEvent;

import musicStreaming.domain.Comment;

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

    public CommentUpdated(Comment aggregate) {
        super(aggregate);
    }

    public CommentUpdated() {
        super();
    }
}
