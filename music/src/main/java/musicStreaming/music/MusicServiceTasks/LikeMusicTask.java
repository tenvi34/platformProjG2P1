package musicStreaming.music.MusicServiceTasks;

import musicStreaming._global.event.MusicLiked;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

import musicStreaming.domain.Music;
import musicStreaming.domain.MusicRepository;

import musicStreaming.music.reqDtos.LikeMusicReqDto;
import musicStreaming.music.resDtos.LikeMusicResDto;

public class LikeMusicTask {
    // 좋아요를 눌렀을 경우, 해당하는 음악의 좋아요 개수를 업데이트하기 위해서
    public static LikeMusicResDto likeMusicTask(LikeMusicReqDto createMusicReqDto,
            MusicRepository musicRepository) {
        CustomLogger.debug(CustomLoggerType.EFFECT, "TODO: likeMusic");

        // [1] likeMusicReqDto에서 musicId를 얻어서 해당하는 Music 데이터 얻기
        Long musicId = createMusicReqDto.getMusicId();
        Music music = musicRepository.findById(musicId)
                .orElseThrow(() -> new IllegalArgumentException("No music found with id: " + musicId));
        // [2] likes의 개수를 1 증가시키고, 저장하기
        music.setLikes(music.getLikes() + 1);
        musicRepository.save(music);

        // [3] MusicLiked 이벤트 발생시키기 
        MusicLiked musicLiked = new MusicLiked(music);
        musicLiked.setId(musicId);
        musicLiked.setLikes(music.getLikes());
        musicLiked.publish();

        return new LikeMusicResDto(music);
    }
}
