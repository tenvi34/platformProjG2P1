package musicStreaming.comment.resDtos;

import lombok.Getter;
import lombok.ToString;

import musicStreaming.domain.Comment;

@Getter
@ToString
public class UpdateCommentResDto {
    private final Long id;

    public UpdateCommentResDto(Comment comment) {
        this.id = comment.getId();
    }
}
