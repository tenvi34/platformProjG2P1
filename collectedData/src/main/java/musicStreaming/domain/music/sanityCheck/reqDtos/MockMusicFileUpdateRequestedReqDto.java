package musicStreaming.domain.music.sanityCheck.reqDtos;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MockMusicFileUpdateRequestedReqDto {
    private Long id;
    private String dataUrlCode;
    private String fileId;
    private Date updatedDate;
}
