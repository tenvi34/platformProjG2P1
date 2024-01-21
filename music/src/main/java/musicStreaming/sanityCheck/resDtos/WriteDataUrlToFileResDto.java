package musicStreaming.sanityCheck.resDtos;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class WriteDataUrlToFileResDto {
    private final String dataUrlCode;

    public WriteDataUrlToFileResDto(String dataUrlCode) {
        this.dataUrlCode = dataUrlCode;
    }
}
