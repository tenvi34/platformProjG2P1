package musicStreaming.sanityCheck.reqDtos;

import java.util.Date;

import lombok.Data;

@Data
public class MockMusicFileUpdateRequestedReqDto {
    private Long id;
    private Long fileId;
    private Date updatedDate;
    private String dataUrlCode;
}
