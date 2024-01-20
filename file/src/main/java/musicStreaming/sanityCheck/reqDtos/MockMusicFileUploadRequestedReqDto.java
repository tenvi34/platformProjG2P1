package musicStreaming.sanityCheck.reqDtos;

import java.util.Date;

import lombok.Data;

@Data
public class MockMusicFileUploadRequestedReqDto {
    private Long id;
    private String dataUrlCode;
    private Long createrId;
    private String title;
    private String creater;
    private Integer likes;
    private Date createdDate;
    private Date updatedDate;
}
