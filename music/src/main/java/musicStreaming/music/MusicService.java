package musicStreaming.music;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

import musicStreaming.domain.Music;

import musicStreaming.music.reqDtos.CreateMusicReqDto;
import musicStreaming.music.reqDtos.GetDataUrlReqDto;
import musicStreaming.music.reqDtos.LikeMusicReqDto;
import musicStreaming.music.reqDtos.UpdateMusicFileReqDto;
import musicStreaming.music.reqDtos.UpdateMusicInfoReqDto;
import musicStreaming.music.resDtos.CreateMusicResDto;
import musicStreaming.music.resDtos.GetDataUrlResDto;
import musicStreaming.music.resDtos.LikeMusicResDto;
import musicStreaming.music.resDtos.UpdateMusicFileResDto;
import musicStreaming.music.resDtos.UpdateMusicInfoResDto;

@Service
@RequiredArgsConstructor
public class MusicService {
    // private final MusicRepository musicRepository;

    // 주어진 DataURL을 저장하고, File 서비스에 업로드 요청을 수행하기 위해서
    public CreateMusicResDto createMusic(CreateMusicReqDto createMusicReqDto, Long userId) {
        CustomLogger.debug(CustomLoggerType.EFFECT, "TODO: createMusic");
        return new CreateMusicResDto(new Music());
    }

    // 주어진 DataUrlCode에 해당하는 DataUrl을 반환하기 위해서
    public GetDataUrlResDto getDataUrl(GetDataUrlReqDto getDataUrlReqDto) {
        CustomLogger.debug(CustomLoggerType.EFFECT, "TODO: getDataUrl");
        return new GetDataUrlResDto("DATA URL");
    }


    // 음악 정보를 업데이트시키기 위해서
    public UpdateMusicInfoResDto updateMusicInfo(UpdateMusicInfoReqDto updateMusicInfoReqDto) {
        CustomLogger.debug(CustomLoggerType.EFFECT, "TODO: updateMusicInfo");
        return new UpdateMusicInfoResDto(new Music());
    }

    // 음악 파일을 업데이트시키기 위해서
    public UpdateMusicFileResDto updateMusicFile(UpdateMusicFileReqDto updateMusicFileReqDto) {
        CustomLogger.debug(CustomLoggerType.EFFECT, "TODO: updateMusicFile");
        return new UpdateMusicFileResDto(new Music());
    }


    // 음악 정보를 업데이트시키기 위해서
    public LikeMusicResDto likeMusic(LikeMusicReqDto createMusicReqDto) {
        CustomLogger.debug(CustomLoggerType.EFFECT, "TODO: likeMusic");
        return new LikeMusicResDto(new Music());
    }
}
