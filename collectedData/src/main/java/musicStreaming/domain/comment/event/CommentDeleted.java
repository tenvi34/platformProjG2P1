package musicStreaming.domain.comment.event;

import musicStreaming._global.infra.AbstractEvent;

import musicStreaming.domain.comment.sanityCheck.reqDtos.MockCommentDeletedReqDto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

// 코멘트가 삭제되었을 경우 발생하는 이벤트
@Data
@ToString
@EqualsAndHashCode(callSuper=false)
public class CommentDeleted extends AbstractEvent {
    private Long id;

    public CommentDeleted(MockCommentDeletedReqDto mockData) {
        super();
        this.id = mockData.getId();
    }

    public CommentDeleted() {
        super();
    }
}
