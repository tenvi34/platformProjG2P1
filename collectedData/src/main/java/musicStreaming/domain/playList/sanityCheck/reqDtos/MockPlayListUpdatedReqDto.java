package musicStreaming.domain.playList.sanityCheck.reqDtos;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MockPlayListUpdatedReqDto {
    private Long id;
    private String title;
    private Date updatedDate;
}
