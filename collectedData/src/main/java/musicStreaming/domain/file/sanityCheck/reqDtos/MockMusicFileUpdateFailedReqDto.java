package musicStreaming.domain.file.sanityCheck.reqDtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MockMusicFileUpdateFailedReqDto {
    private Long id;
    private Long musicId;
}
