package musicStreaming.domain.music.sanityCheck.reqDtos;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MockMusicInfoUpdatedReqDto {
    private Long id;
    private String title;
    private Date updatedDate;
}
