package musicStreaming.music.resDtos;

import lombok.Getter;

@Getter
public class GetDataUrlResDto {
    private final String dataUrl;

    public GetDataUrlResDto(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    public String toString() { 
        return String.format("%s(dataUrlLength=%d)",
            this.getClass().getSimpleName(), this.dataUrl.length());
    }
}
