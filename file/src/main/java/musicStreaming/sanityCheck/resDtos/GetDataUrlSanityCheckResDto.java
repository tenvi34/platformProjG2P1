package musicStreaming.sanityCheck.resDtos;

import lombok.Getter;

@Getter
public class GetDataUrlSanityCheckResDto {
    private final String dataUrl;

    public GetDataUrlSanityCheckResDto(String dataUrl) {
        this.dataUrl = dataUrl;
    }

    public String toString() { 
        return String.format("%s(dataUrl=%d)",
            this.getClass().getSimpleName(), this.dataUrl.length());
    }
}
