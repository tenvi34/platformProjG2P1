package musicStreaming.music.MusicServiceTasks;

import musicStreaming._global.event.MusicLiked;
import musicStreaming._global.exceptions.MusicNotFoundException;

import musicStreaming.domain.Music;
import musicStreaming.domain.MusicRepository;

import musicStreaming.music.reqDtos.LikeMusicReqDto;
import musicStreaming.music.resDtos.LikeMusicResDto;

public class LikeMusicTask {
    // 좋아요를 눌렀을 경우, 해당하는 음악의 좋아요 개수를 업데이트하기 위해서
    public static LikeMusicResDto likeMusicTask(LikeMusicReqDto createMusicReqDto,
            MusicRepository musicRepository) {

        // [1] likeMusicReqDto에서 musicId를 얻어서 해당하는 Music 데이터 얻기
        Music musicToUpdate = musicRepository.findById(createMusicReqDto.getMusicId())
            .orElseThrow(() -> new MusicNotFoundException());

        // [2] likes의 개수를 1 증가시키고, 저장하기
        musicToUpdate.setLikes(musicToUpdate.getLikes() + 1);
        musicRepository.save(musicToUpdate);

        // [3] MusicLiked 이벤트 발생시키기 
        (new MusicLiked(musicToUpdate)).publishAfterCommit();

        return new LikeMusicResDto(musicToUpdate);
    }
}
