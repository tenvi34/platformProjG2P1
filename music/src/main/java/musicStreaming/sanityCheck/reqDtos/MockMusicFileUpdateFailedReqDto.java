package musicStreaming.sanityCheck.reqDtos;

import lombok.Data;

@Data
public class MockMusicFileUpdateFailedReqDto {
    private Long id;
    private Long musicId;
    private String dataUrlCode;
}
