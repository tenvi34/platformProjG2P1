package musicStreaming.music.reqDtos;

import lombok.Data;

@Data
public class UpdateMusicFileReqDto {
    private Long musicId;
    private String dataUrl;

    public String toString() { 
        return String.format("%s(musicId=%d, dataUrlLength=%d)",
            this.getClass().getSimpleName(), this.musicId, this.dataUrl.length());
    }
}
