package musicStreaming.domain.playList.PlayListTasks;

import musicStreaming._global.event.PlayListMusicDeleted;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;
import musicStreaming.domain.playList.PlayList;
import musicStreaming.domain.playList.PlayListRepository;

public class DecreaseMusicCountTask {
    // 플레이 리스트에 음악이 삭제되었을 경우, 음악 개수를 감소시키기 위해서
    public static void decreaseMusicCountTask(PlayListMusicDeleted playListMusicDeleted,
            PlayListRepository playListRepository) {
        CustomLogger.debug(CustomLoggerType.EFFECT, "TODO: decreaseMusicCount");

        // [1] playListId로 PlayList를 얻어서 musicCount를 1 감소시킵니다.
        String playListId = playListMusicDeleted.getPlayListId();

        // 해당 아이디의 플레이리스트를 찾습니다.
        PlayList playList = playListRepository.findById(playListId);

        // 음악 개수를 감소시키기 전에, 현재 음악 개수가 0보다 큰지 확인합니다.
        if (playList.getMusicCount() > 0) {
            playList.setMusicCount(playList.getMusicCount() - 1);
            playListRepository.save(playList);
        } else {
            CustomLogger.warn(CustomLoggerType.EFFECT, "플레이리스트에 음악이 존재하지않습니다.");
        }
    }
}
