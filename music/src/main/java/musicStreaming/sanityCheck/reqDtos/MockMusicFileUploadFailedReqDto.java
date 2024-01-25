package musicStreaming.sanityCheck.reqDtos;

import lombok.Data;

@Data
public class MockMusicFileUploadFailedReqDto {
    private Long id;
    private Long musicId;
    private String dataUrlCode;
}
