package musicStreaming.domain.playList.PlayListTasks;

import musicStreaming._global.event.PlayListMusicCountUpdated;
import musicStreaming._global.event.PlayListMusicDeleted;

import musicStreaming.domain.playList.PlayList;
import musicStreaming.domain.playList.PlayListRepository;

import musicStreaming.playList.exceptions.PlayListNotFoundException;

public class DecreaseMusicCountTask {
    // 플레이 리스트에 음악이 삭제되었을 경우, 음악 개수를 감소시키기 위해서
    public static void decreaseMusicCountTask(PlayListMusicDeleted playListMusicDeleted,
            PlayListRepository playListRepository) {

        // [1] playListId로 PlayList를 얻어서 musicCount를 1 감소시킵니다.
        PlayList playListToUpdate = playListRepository.findById(playListMusicDeleted.getPlayListId())
            .orElseThrow(() -> new PlayListNotFoundException());

        playListToUpdate.setMusicCount(playListToUpdate.getMusicCount() - 1);
        playListRepository.save(playListToUpdate);


        // [2] PlayListMusicCountUpdated 이벤트를 발생시킵니다.
        (new PlayListMusicCountUpdated(playListToUpdate)).publishAfterCommit();

    }
}
