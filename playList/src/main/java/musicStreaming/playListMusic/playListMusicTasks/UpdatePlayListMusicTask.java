package musicStreaming.playListMusic.playListMusicTasks;

import musicStreaming._global.event.PlayListMusicUpdated;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

import musicStreaming.domain.playListMusic.PlayListMusic;
import musicStreaming.domain.playListMusic.PlayListMusicRepository;

import musicStreaming.playListMusic.reqDtos.UpdatePlayListMusicReqDto;
import musicStreaming.playListMusic.resDtos.UpdatePlayListMusicResDto;

public class UpdatePlayListMusicTask {
    // 주어진 DataURL을 저장하고, File 서비스에 업로드 요청을 수행하기 위해서
    public static UpdatePlayListMusicResDto updatePlayListMusicTask(UpdatePlayListMusicReqDto updatePlayListMusicReqDto, 
            PlayListMusicRepository playListMusicRepository) {
        CustomLogger.debug(CustomLoggerType.EFFECT, "TODO: updatePlayListMusic");

        // [1] playListMusicRepository로 playListMusicId와 매칭되는 PlayListMusic 데이터를 가져옵니다.
        playListMusicRepository.findById(updatePlayListMusicReqDto.getPlayListMusicId()).ifPresent(updatePlayListMusic -> {
            PlayListMusic playListMusic = new PlayListMusic(
                updatePlayListMusic.getId(),
                updatePlayListMusic.getPlayListId(),
                updatePlayListMusic.getMusicId(),
                updatePlayListMusic.getCreaterId(),
                updatePlayListMusic.getTitle(),
                updatePlayListMusic.getCreatedDate(),
                updatePlayListMusic.getCreatedDate()
            );
            
            // [2] PlayListMusic를 수정하고 저장합니다.
            PlayListMusicUpdated playListMusicUpdated = new PlayListMusicUpdated(playListMusic);
            playListMusic.setTitle(playListMusicUpdated.getTitle());
            playListMusic.setUpdatedDate(playListMusicUpdated.getUpdatedDate());

            playListMusicRepository.save(playListMusic);   

            // [3] PlayListMusicUpdated 이벤트를 발생시킵니다.
            playListMusicUpdated.publishAfterCommit();            
        });        

        return new UpdatePlayListMusicResDto(new PlayListMusic());
    }
}
