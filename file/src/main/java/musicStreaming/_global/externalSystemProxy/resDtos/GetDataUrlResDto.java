package musicStreaming._global.externalSystemProxy.resDtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class GetDataUrlResDto implements ExternalSystemProxyResDto {
    @JsonProperty
    private String dataUrl;

    public String toString() { 
        return String.format("%s(dataUrl=%d)",
            this.getClass().getSimpleName(), this.dataUrl.length());
    }
}
