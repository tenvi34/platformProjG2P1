package musicStreaming.domain.MusicTasks;

import musicStreaming._global.event.MusicDeleted;
import musicStreaming._global.event.MusicFileDeleted;

import musicStreaming.domain.MusicRepository;

public class DeleteMusicTask {
    // 음악 파일이 삭제되었을 경우, 최종적으로 관련 음악 정보를 삭제시키고, 관련 이벤트를 발생시키기 위해서
    public static void deleteMusicTask(MusicFileDeleted musicFileDeleted, MusicRepository musicRepository) {

        // [1] musicRepository를 이용해서 주어진 musicId에 해당하는 Music 클래스를 얻습니다.
        musicRepository.findById(musicFileDeleted.getMusicId()).ifPresent(musicToDelete ->{

            // [2] 얻어진 Music 클래스을 musicRepository에서 삭제시킵니다.
            musicRepository.delete(musicToDelete);

            // [3] Music 데이터가 최종적으로 삭제되었으므로, MusicDeleted 이벤트를 발행합니다.
            (new MusicDeleted(musicToDelete)).publishAfterCommit();;
        
        });
        
    }
}
