package musicStreaming.domain.music.sanityCheck.reqDtos;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MockMusicFileDeleteRequestedReqDto {
    private Long id;
    private Long fileId;
    private Date updatedDate;
}
