package musicStreaming.sanityCheck.reqDtos;

import java.util.Date;

import lombok.Data;

@Data
public class MockMusicFileUploadedReqDto {
    private Long id;
    private Long createrId;
    private String path;
    private Date createdDate;
    private Date updatedDate;
    private Integer totalSeconds;
    private String dataUrlCode;
}
