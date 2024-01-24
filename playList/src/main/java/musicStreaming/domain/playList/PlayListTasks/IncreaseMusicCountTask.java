package musicStreaming.domain.playList.PlayListTasks;

import musicStreaming._global.event.PlayListMusicCreated;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;
import musicStreaming.domain.playList.PlayList;
import musicStreaming.domain.playList.PlayListRepository;

public class IncreaseMusicCountTask {
    // 플레이 리스트에 음악이 추가되었을 경우, 음악 개수를 증가시키기 위해서
    public static void increaseMusicCountTask(PlayListMusicCreated playListMusicCreated,
            PlayListRepository playListRepository) {
        CustomLogger.debug(CustomLoggerType.EFFECT, "TODO: increaseMusicCount");

        // [1] playListId로 PlayList를 얻어서 musicCount를 1 증가시킵니다.
        playListRepository.findById(playListMusicCreated.getPlayListId()).ifPresent(musicCount->{
            musicCount.setMusicCount(musicCount.getMusicCount()+1);
            playListRepository.save(musicCount);
        });
    }
}
