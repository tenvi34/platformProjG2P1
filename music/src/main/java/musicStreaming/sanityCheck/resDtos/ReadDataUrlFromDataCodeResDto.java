package musicStreaming.sanityCheck.resDtos;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ReadDataUrlFromDataCodeResDto {
    private final String dataUrl;

    public ReadDataUrlFromDataCodeResDto(String dataUrl) {
        this.dataUrl = dataUrl;
    }
}
