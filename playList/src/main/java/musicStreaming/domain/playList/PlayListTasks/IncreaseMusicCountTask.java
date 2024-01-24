package musicStreaming.domain.playList.PlayListTasks;

import musicStreaming._global.event.PlayListMusicCountUpdated;
import musicStreaming._global.event.PlayListMusicCreated;

import musicStreaming.domain.playList.PlayListRepository;

public class IncreaseMusicCountTask {
    // 플레이 리스트에 음악이 추가되었을 경우, 음악 개수를 증가시키기 위해서
    public static void increaseMusicCountTask(PlayListMusicCreated playListMusicCreated,
            PlayListRepository playListRepository) {
        // [1] playListId로 PlayList를 얻어서 musicCount를 1 증가시킵니다.
        playListRepository.findById(playListMusicCreated.getPlayListId()).ifPresent(playListToUpdate->{
            playListToUpdate.setMusicCount(playListToUpdate.getMusicCount()+1);
            playListRepository.save(playListToUpdate);

            // [2] PlayListMusicCountUpdated 이벤트를 발생시킵니다.
            (new PlayListMusicCountUpdated(playListToUpdate)).publishAfterCommit();
        });
    }
}
