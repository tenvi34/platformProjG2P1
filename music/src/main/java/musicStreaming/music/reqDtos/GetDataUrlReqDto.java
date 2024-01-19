package musicStreaming.music.reqDtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GetDataUrlReqDto {
    private String dataUrlCode;
}
