package musicStreaming.domain.playList.PlayListTasks;

import musicStreaming._global.event.PlayListMusicDeleted;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

import musicStreaming.domain.playList.PlayListRepository;

public class DecreaseMusicCountTask {
    // 플레이 리스트에 음악이 삭제되었을 경우, 음악 개수를 감소시키기 위해서
    public static void decreaseMusicCountTask(PlayListMusicDeleted playListMusicDeleted,
            PlayListRepository playListRepository) {
        CustomLogger.debug(CustomLoggerType.EFFECT, "TODO: decreaseMusicCount");

        // [1] playListId로 PlayList를 얻어서 musicCount를 1 감소시킵니다.
    }
}
