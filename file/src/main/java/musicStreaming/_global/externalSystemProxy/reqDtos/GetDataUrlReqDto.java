package musicStreaming._global.externalSystemProxy.reqDtos;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

@Getter
public class GetDataUrlReqDto implements ExternalSystemProxyReqDto {
    private final String dataUrlCode;

    public GetDataUrlReqDto(String dataUrlCode) {
        this.dataUrlCode = dataUrlCode;
    }

    public Map<String, Object> hashMap() {
        Map<String, Object> hashMap = new HashMap<>();
        hashMap.put("dataUrlCode", this.dataUrlCode);
        return hashMap;
    }
}
