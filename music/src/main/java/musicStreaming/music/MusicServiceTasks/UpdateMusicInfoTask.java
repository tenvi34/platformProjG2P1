package musicStreaming.music.MusicServiceTasks;

import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

import musicStreaming.domain.Music;
import musicStreaming.domain.MusicRepository;

import musicStreaming.music.reqDtos.UpdateMusicInfoReqDto;
import musicStreaming.music.resDtos.UpdateMusicInfoResDto;

public class UpdateMusicInfoTask {
    // 음악 정보를 업데이트시키기 위해서
    public static UpdateMusicInfoResDto updateMusicInfoTask(UpdateMusicInfoReqDto updateMusicInfoReqDto,
            MusicRepository musicRepository) {
        CustomLogger.debug(CustomLoggerType.EFFECT, "TODO: updateMusicInfo");

        // [1] updateMusicInfoReqDto에서 musicId를 얻어서 해당하는 Music 데이터 얻기
        // [2] title을 업데이트하고, 저장하기
        // [3] MusicInfoUpdated 이벤트 발생시키기 

        return new UpdateMusicInfoResDto(new Music());
    }
}
