package musicStreaming.domain.user.sanityCheck.reqDtos;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MockUserNameUpdatedReqDto {
    private Long id;
    private String name;
    private Date updatedDate;
}
