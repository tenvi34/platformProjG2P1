package musicStreaming.sanityCheck.resDtos;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class WriteMp3FileFromDataUrlResDto {
    private final String fileName;

    public WriteMp3FileFromDataUrlResDto(String fileName) {
        this.fileName = fileName;
    }
}
