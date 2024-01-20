package musicStreaming.domain.playListMusic.sanityCheck.reqDtos;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MockPlayListMusicUpdatedReqDto {
    private Long id;
    private String title;
    private Date updatedDate;
}
