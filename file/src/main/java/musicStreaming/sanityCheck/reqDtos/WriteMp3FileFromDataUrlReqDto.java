package musicStreaming.sanityCheck.reqDtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WriteMp3FileFromDataUrlReqDto {
    private String dataUrl = "";
}
