package musicStreaming.domain.playList.sanityCheck.reqDtos;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MockPlayListCreatedReqDto {
    private Long id;
    private Long createrId;
    private String title;
    private Integer musicCount;
    private Date createdDate;
    private Date updatedDate;
}
