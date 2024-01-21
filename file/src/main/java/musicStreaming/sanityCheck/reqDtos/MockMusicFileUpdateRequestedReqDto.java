package musicStreaming.sanityCheck.reqDtos;

import java.util.Date;

import lombok.Data;

@Data
public class MockMusicFileUpdateRequestedReqDto {
    private Long id;
    private String dataUrlCode;
    private String fileId;
    private Date updatedDate;
}
