package musicStreaming.playListMusic.playListMusicTasks;

import musicStreaming._global.event.PlayListMusicCreated;

import musicStreaming.domain.playListMusic.PlayListMusic;
import musicStreaming.domain.playListMusic.PlayListMusicRepository;

import musicStreaming.playListMusic.reqDtos.CreatePlayListMusicReqDto;
import musicStreaming.playListMusic.resDtos.CreatePlayListMusicResDto;

public class CreatePlayListMusicTask {
    // 주어진 DataURL을 저장하고, File 서비스에 업로드 요청을 수행하기 위해서
    public static CreatePlayListMusicResDto createPlayListMusicTask(CreatePlayListMusicReqDto createPlayListMusicReqDto, Long userId, 
            PlayListMusicRepository playListMusicRepository) {
        // [1] playListMusicRepository를 이용해서 새로운 PlayListMusic 데이터를 저장합니다.
        PlayListMusic savedPlayListMusic = playListMusicRepository.save(
            PlayListMusic.builder()
                .playListId(createPlayListMusicReqDto.getPlayListId())
                .musicId(createPlayListMusicReqDto.getMusicId())
                .createrId(userId)
                .title(createPlayListMusicReqDto.getTitle())
                .build()
        );

        // [2] PlayListMusicCreated 이벤트를 발생시킵니다.
        (new PlayListMusicCreated(savedPlayListMusic)).publishAfterCommit();

        return new CreatePlayListMusicResDto(savedPlayListMusic);
    }
}
