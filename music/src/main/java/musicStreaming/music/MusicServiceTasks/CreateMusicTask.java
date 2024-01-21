package musicStreaming.music.MusicServiceTasks;

import musicStreaming._global.dataUrlStorage.DataUrlStorageService;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

import musicStreaming.domain.Music;
import musicStreaming.domain.MusicRepository;

import musicStreaming.music.reqDtos.CreateMusicReqDto;
import musicStreaming.music.resDtos.CreateMusicResDto;

public class CreateMusicTask {
    // 주어진 DataURL을 저장하고, File 서비스에 업로드 요청을 수행하기 위해서
    public static CreateMusicResDto createMusicTask(CreateMusicReqDto createMusicReqDto, Long userId, 
            MusicRepository musicRepository, DataUrlStorageService dataUrlStorageService) {
        CustomLogger.debug(CustomLoggerType.EFFECT, "TODO: createMusic");

        // [1] musicRepository를 이용해서 새로운 Music 데이터를 저장합니다.
        // [2] 전달된 dataUrl을 dataUrlStorageService를 통해서 저장하고, DataUrlCode를 얻습니다.
        // [3] DataUrlCode와 저장한 Music 데이터를 기반으로 MusicFileUploadRequested 이벤트를 발생시킵니다.

        return new CreateMusicResDto(new Music());
    }
}
