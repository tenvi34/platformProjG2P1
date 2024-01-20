package musicStreaming.domain.comment.sanityCheck.reqDtos;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MockCommentUpdatedReqDto {
    private Long id;
    private String content;
    private Date updatedDate;
}
