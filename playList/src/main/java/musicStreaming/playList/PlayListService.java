package musicStreaming.playList;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

import musicStreaming.domain.playList.PlayList;

import musicStreaming.playList.reqDtos.CreatePlayListReqDto;
import musicStreaming.playList.reqDtos.UpdatePlayListReqDto;
import musicStreaming.playList.resDtos.CreatePlayListResDto;
import musicStreaming.playList.resDtos.UpdatePlayListResDto;

@Service
@RequiredArgsConstructor
public class PlayListService {
    // private final PlayListRepository playListRepository;

    // 주어진 DataURL을 저장하고, File 서비스에 업로드 요청을 수행하기 위해서
    public CreatePlayListResDto createPlayList(CreatePlayListReqDto createPlayListReqDto, Long userId) {
        CustomLogger.debug(CustomLoggerType.EFFECT, "TODO: createPlayList");
        return new CreatePlayListResDto(new PlayList());
    }

    // 플레이 리스트를 수정시키기 위해서
    public UpdatePlayListResDto updatePlayList(UpdatePlayListReqDto updatePlayListReqDto) {
        CustomLogger.debug(CustomLoggerType.EFFECT, "TODO: updatePlayList");
        return new UpdatePlayListResDto(new PlayList());
    }
}
