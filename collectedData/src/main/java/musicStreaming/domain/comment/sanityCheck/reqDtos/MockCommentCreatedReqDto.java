package musicStreaming.domain.comment.sanityCheck.reqDtos;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MockCommentCreatedReqDto {
    private Long id;
    private Long createrId;
    private Long musicId;
    private String content;
    private Date createdDate;
    private Date updatedDate;
}
