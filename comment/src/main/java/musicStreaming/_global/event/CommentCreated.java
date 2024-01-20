package musicStreaming._global.event;

import musicStreaming._global.infra.AbstractEvent;

import musicStreaming.domain.Comment;

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

    public CommentCreated(Comment aggregate) {
        super(aggregate);
    }

    public CommentCreated() {
        super();
    }
}
