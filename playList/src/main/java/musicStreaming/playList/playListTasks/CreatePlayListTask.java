package musicStreaming.playList.playListTasks;

import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;
import musicStreaming.domain.playList.PlayList;
import musicStreaming.domain.playList.PlayListRepository;
import musicStreaming.playList.reqDtos.CreatePlayListReqDto;
import musicStreaming.playList.resDtos.CreatePlayListResDto;

public class CreatePlayListTask {
    // 주어진 DataURL을 저장하고, File 서비스에 업로드 요청을 수행하기 위해서
    public static CreatePlayListResDto createPlayListTask(CreatePlayListReqDto createPlayListReqDto, Long userId, 
            PlayListRepository playListRepository) {
        CustomLogger.debug(CustomLoggerType.EFFECT, "TODO: createPlayList");

        // [1] playListRepository를 이용해서 새로운 Music 데이터를 저장합니다.
        // [2] PlayListCreated 이벤트를 발생시킵니다.

        return new CreatePlayListResDto(new PlayList());
    }
}
