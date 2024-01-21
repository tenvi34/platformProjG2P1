package musicStreaming.sanityCheck.reqDtos;

import lombok.Data;

@Data
public class WriteMp3FileFromDataUrlReqDto {
    private String dataUrl = "";

    public String toString() { 
        return String.format("%s(dataUrlLength=%d)",
            this.getClass().getSimpleName(), this.dataUrl.length());
    }
}
