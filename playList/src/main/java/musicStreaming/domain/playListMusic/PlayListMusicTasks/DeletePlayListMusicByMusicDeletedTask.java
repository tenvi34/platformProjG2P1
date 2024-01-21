package musicStreaming.domain.playListMusic.PlayListMusicTasks;

import musicStreaming._global.event.MusicDeleted;

import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

import musicStreaming.domain.playListMusic.PlayListMusicRepository;

public class DeletePlayListMusicByMusicDeletedTask {
    // 플레이 리스트가 삭제되었을 경우, 관련된 플레이 리스트 음악들을 삭제시키기 위해서
    public static void deletePlayListMusicByMusicDeletedTask(MusicDeleted musicDeleted,
            PlayListMusicRepository playListMusicRepository) {
        CustomLogger.debug(CustomLoggerType.EFFECT, "TODO: deletePlayListMusicByMusicDeleted");

        // [1] musicId와 매칭되는 모든 PlayListMusic을 삭제시킵니다.
        // [2] 각각의 삭제되는 PlayListMusic 마다 PlayListMusicDeleted 이벤트를 발생시킵니다.
    }
}
