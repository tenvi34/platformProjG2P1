package musicStreaming.domain.file.sanityCheck.reqDtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MockMusicFileUploadFailedReqDto {
    private Long id;
    private Long musicId;
    private String dataUrlCode;
}
