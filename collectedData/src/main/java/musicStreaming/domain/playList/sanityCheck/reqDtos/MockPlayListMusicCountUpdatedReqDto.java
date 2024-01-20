package musicStreaming.domain.playList.sanityCheck.reqDtos;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MockPlayListMusicCountUpdatedReqDto {
    private Long id;
    private Integer musicCount;
    private Date updatedDate;
}
