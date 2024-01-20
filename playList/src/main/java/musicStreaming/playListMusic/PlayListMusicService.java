package musicStreaming.playListMusic;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

import musicStreaming.domain.playListMusic.PlayListMusic;

import musicStreaming.playListMusic.reqDtos.CreatePlayListMusicReqDto;
import musicStreaming.playListMusic.reqDtos.UpdatePlayListMusicReqDto;
import musicStreaming.playListMusic.resDtos.CreatePlayListMusicResDto;
import musicStreaming.playListMusic.resDtos.UpdatePlayListMusicResDto;

@Service
@RequiredArgsConstructor
public class PlayListMusicService {
    // private final PlayListMusicRepository playListMusicRepository;

    // 플레이 리스트 음악을 생성시키기 위해서
    public CreatePlayListMusicResDto createPlayListMusic(CreatePlayListMusicReqDto createPlayListMusicReqDto, Long userId) {
        CustomLogger.debug(CustomLoggerType.EFFECT, "TODO: createPlayListMusic");
        return new CreatePlayListMusicResDto(new PlayListMusic());
    }

    // 플레이 리스트 음악을 수정시키기 위해서
    public UpdatePlayListMusicResDto updatePlayListMusic(UpdatePlayListMusicReqDto updatePlayListMusicReqDto) {
        CustomLogger.debug(CustomLoggerType.EFFECT, "TODO: updatePlayListMusic");
        return new UpdatePlayListMusicResDto(new PlayListMusic());
    }
}
