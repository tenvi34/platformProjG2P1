package musicStreaming.music.reqDtos;

import lombok.Data;

@Data
public class CreateMusicReqDto {
    private String title;
    private String creater;
    private String dataUrl;

    public String toString() { 
        return String.format("%s(title=%s, creater=%s, dataUrlLength=%d)",
            this.getClass().getSimpleName(), this.title, this.creater, this.dataUrl.length());
    }
}
