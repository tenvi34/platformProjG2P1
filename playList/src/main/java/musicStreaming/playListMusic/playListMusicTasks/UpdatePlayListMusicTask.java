package musicStreaming.playListMusic.playListMusicTasks;

import java.util.Optional;

import musicStreaming._global.event.PlayListMusicUpdated;

import musicStreaming.domain.playListMusic.PlayListMusic;
import musicStreaming.domain.playListMusic.PlayListMusicRepository;

import musicStreaming.playListMusic.exceptions.PlayListMusicNotFoundException;
import musicStreaming.playListMusic.reqDtos.UpdatePlayListMusicReqDto;
import musicStreaming.playListMusic.resDtos.UpdatePlayListMusicResDto;

public class UpdatePlayListMusicTask {
    // 주어진 DataURL을 저장하고, File 서비스에 업로드 요청을 수행하기 위해서
    public static UpdatePlayListMusicResDto updatePlayListMusicTask(UpdatePlayListMusicReqDto updatePlayListMusicReqDto,
            PlayListMusicRepository playListMusicRepository) {

        // [1] playListMusicRepository로 playListMusicId와 매칭되는 PlayListMusic 데이터를 가져옵니다.
        Optional<PlayListMusic> optionalPlayListMusic = playListMusicRepository.findById(updatePlayListMusicReqDto.getPlayListMusicId());
        if(!optionalPlayListMusic.isPresent()) throw new PlayListMusicNotFoundException();
        PlayListMusic playListMusicToUpdate = optionalPlayListMusic.get();

        // [2] PlayListMusic를 수정하고 저장합니다.
        playListMusicToUpdate.setTitle(updatePlayListMusicReqDto.getTitle());
        playListMusicRepository.save(playListMusicToUpdate);

        playListMusicRepository.save(updatePlayListMusic);

        // [3] PlayListMusicUpdated 이벤트를 발생시킵니다.
        (new PlayListMusicUpdated(playListMusicToUpdate)).publishAfterCommit();

        return new UpdatePlayListMusicResDto(playListMusicToUpdate);
    }
}
