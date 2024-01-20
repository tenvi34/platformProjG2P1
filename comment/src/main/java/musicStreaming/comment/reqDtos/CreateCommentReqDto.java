package musicStreaming.comment.reqDtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CreateCommentReqDto {
    private Long musicId;
    private String content;
}
