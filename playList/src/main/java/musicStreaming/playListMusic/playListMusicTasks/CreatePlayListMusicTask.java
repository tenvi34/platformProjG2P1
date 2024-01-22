package musicStreaming.playListMusic.playListMusicTasks;

import java.util.Date;

import musicStreaming._global.event.PlayListMusicCreated;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

import musicStreaming.domain.playListMusic.PlayListMusic;
import musicStreaming.domain.playListMusic.PlayListMusicRepository;

import musicStreaming.playListMusic.reqDtos.CreatePlayListMusicReqDto;
import musicStreaming.playListMusic.resDtos.CreatePlayListMusicResDto;

public class CreatePlayListMusicTask {
    // 주어진 DataURL을 저장하고, File 서비스에 업로드 요청을 수행하기 위해서
    public static CreatePlayListMusicResDto createPlayListMusicTask(CreatePlayListMusicReqDto createPlayListMusicReqDto, Long userId, 
            PlayListMusicRepository playListMusicRepository) {
        CustomLogger.debug(CustomLoggerType.EFFECT, "TODO: createPlayListMusic");

        // [1] playListMusicRepository를 이용해서 새로운 PlayListMusic 데이터를 저장합니다.
        PlayListMusic playListMusic = new PlayListMusic();
        PlayListMusicCreated playListMusicCreated = new PlayListMusicCreated(playListMusic);

        playListMusic.setId(playListMusicCreated.getId());
        playListMusic.setPlayListId(playListMusicCreated.getPlayListId());
        playListMusic.setMusicId(playListMusicCreated.getMusicId());
        playListMusic.setCreaterId(playListMusicCreated.getCreaterId());
        playListMusic.setTitle(playListMusicCreated.getTitle());
        playListMusic.setCreatedDate(playListMusic.getCreatedDate());
        playListMusic.setUpdatedDate(playListMusicCreated.getUpdatedDate());

        playListMusicRepository.save(playListMusic);
        // [2] PlayListMusicCreated 이벤트를 발생시킵니다.
        playListMusicCreated.publishAfterCommit();
        
        return new CreatePlayListMusicResDto(new PlayListMusic());
    }
}
