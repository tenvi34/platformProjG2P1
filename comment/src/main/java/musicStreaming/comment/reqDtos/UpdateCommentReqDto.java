package musicStreaming.comment.reqDtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UpdateCommentReqDto {
    private Long commentId;
    private String content;
}
