package musicStreaming.domain.user.sanityCheck.reqDtos;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MockSignUpCompletedReqDto {
    private Long id;
    private String email;
    private String name;
    private String role;
    private Date createdDate;
    private Date updatedDate;
}
