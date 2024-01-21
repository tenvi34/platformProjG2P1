package musicStreaming.domain.playListMusic.PlayListMusicTasks;

import musicStreaming._global.event.PlayListDeleted;

import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

import musicStreaming.domain.playListMusic.PlayListMusicRepository;

public class DeletePlayListMusicByPlayListDeletedTask {
    // 플레이 리스트가 삭제되었을 경우, 관련된 플레이 리스트 음악들을 삭제시키기 위해서
    public static void deletePlayListMusicByPlayListDeletedTask(PlayListDeleted playListDeleted,
            PlayListMusicRepository playListMusicRepository) {
        CustomLogger.debug(CustomLoggerType.EFFECT, "TODO: deletePlayListMusicByPlayListDeleted");

        // [1] playListId와 매칭되는 모든 PlayListMusic을 삭제시킵니다.
    }
}
