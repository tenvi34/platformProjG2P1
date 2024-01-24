package musicStreaming.domain.playListMusic.PlayListMusicTasks;

import java.util.Optional;

import musicStreaming._global.event.PlayListDeleted;
import musicStreaming._global.event.PlayListMusicDeleted;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;
import musicStreaming.domain.playListMusic.PlayListMusic;
import musicStreaming.domain.playListMusic.PlayListMusicRepository;

public class DeletePlayListMusicByPlayListDeletedTask {
    // 플레이 리스트가 삭제되었을 경우, 관련된 플레이 리스트 음악들을 삭제시키기 위해서
    public static void deletePlayListMusicByPlayListDeletedTask(PlayListDeleted playListDeleted,
            PlayListMusicRepository playListMusicRepository) {
        CustomLogger.debug(CustomLoggerType.EFFECT, "TODO: deletePlayListMusicByPlayListDeleted");

        Optional<PlayListMusic> optionalPlayListMusic = playListMusicRepository.findById(playListDeleted.getId());

        // [1] playListId와 매칭되는 모든 PlayListMusic을 삭제시킵니다.
        playListMusicRepository.delete(optionalPlayListMusic.get());
        // [2] 각각의 삭제되는 PlayListMusic 마다 PlayListMusicDeleted 이벤트를 발생시킵니다.
        if (optionalPlayListMusic.isPresent()) {
            PlayListMusic playListMusic = optionalPlayListMusic.get();

            PlayListMusicDeleted playListMusicDeleted = new PlayListMusicDeleted(playListMusic);
            playListMusicDeleted.publishAfterCommit();
        } else {
            System.out.println("Comment not found");
        }
    }
}
