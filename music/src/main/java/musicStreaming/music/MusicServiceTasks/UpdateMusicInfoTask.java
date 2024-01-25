package musicStreaming.music.MusicServiceTasks;

import java.util.Optional;

import musicStreaming._global.event.MusicInfoUpdated;
import musicStreaming._global.exceptions.MusicNotFoundException;

import musicStreaming.domain.Music;
import musicStreaming.domain.MusicRepository;

import musicStreaming.music.reqDtos.UpdateMusicInfoReqDto;
import musicStreaming.music.resDtos.UpdateMusicInfoResDto;

public class UpdateMusicInfoTask {
    // 음악 정보를 업데이트시키기 위해서
    public static UpdateMusicInfoResDto updateMusicInfoTask(UpdateMusicInfoReqDto updateMusicInfoReqDto,
            MusicRepository musicRepository) {

        // [1] updateMusicInfoReqDto에서 musicId를 얻어서 해당하는 Music 데이터 얻기
        Optional<Music> optaionalMusic = musicRepository.findById(updateMusicInfoReqDto.getMusicId());
        if(!optaionalMusic.isPresent()) throw new MusicNotFoundException();
        Music musicToUpate = optaionalMusic.get();

        // [2] title을 업데이트하고, 저장하기
        musicToUpate.setTitle(updateMusicInfoReqDto.getTitle());
        Music savedMusic = musicRepository.save(musicToUpate);

        // [3] MusicInfoUpdated 이벤트 발생시키기
        (new MusicInfoUpdated(savedMusic)).publishAfterCommit();

        return new UpdateMusicInfoResDto(savedMusic);
    }
}
