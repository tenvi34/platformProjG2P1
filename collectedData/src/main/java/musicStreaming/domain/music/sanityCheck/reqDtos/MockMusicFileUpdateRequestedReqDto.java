package musicStreaming.domain.music.sanityCheck.reqDtos;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MockMusicFileUpdateRequestedReqDto {
    private Long id;
    private Long fileId;
    private Date updatedDate;
    private String dataUrlCode;
}
