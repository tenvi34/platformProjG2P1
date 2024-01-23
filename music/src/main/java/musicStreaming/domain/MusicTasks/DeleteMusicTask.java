package musicStreaming.domain.MusicTasks;

import musicStreaming._global.event.MusicDeleted;
import musicStreaming._global.event.MusicFileDeleted;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;
import musicStreaming.domain.Music;
import musicStreaming.domain.MusicRepository;

import java.util.Optional;

public class DeleteMusicTask {
    // 음악 파일이 삭제되었을 경우, 최종적으로 관련 음악 정보를 삭제시키고, 관련 이벤트를 발생시키기 위해서
    public static void deleteMusicTask(MusicFileDeleted musicFileDeleted, MusicRepository musicRepository) {
        CustomLogger.debug(CustomLoggerType.EFFECT, "TODO: deleteMusic");

        // [1] musicRepository를 이용해서 주어진 musicId에 해당하는 Music 클래스를 얻습니다.
        musicRepository.findById(musicFileDeleted.getMusicId()).ifPresent(deleteMusic ->{
            musicRepository.delete(deleteMusic);
            // [2] 얻어진 Music 클래스을 musicRepository에서 삭제시킵니다.
            // [3] Music 데이터가 최종적으로 삭제되었으므로, MusicDeleted 이벤트를 발행합니다.
            MusicDeleted musicDeleted = new MusicDeleted(deleteMusic);
            musicDeleted.publishAfterCommit();
        });
        
        
        
    }
}
