package musicStreaming.sanityCheck.reqDtos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class GetDataUrlSanityCheckReqDto {
    private String dataUrlCode = "";
}
