package musicStreaming.playListMusic;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import musicStreaming.domain.playListMusic.PlayListMusicRepository;

import musicStreaming.playListMusic.playListMusicTasks.CreatePlayListMusicTask;
import musicStreaming.playListMusic.playListMusicTasks.UpdatePlayListMusicTask;
import musicStreaming.playListMusic.reqDtos.CreatePlayListMusicReqDto;
import musicStreaming.playListMusic.reqDtos.UpdatePlayListMusicReqDto;
import musicStreaming.playListMusic.resDtos.CreatePlayListMusicResDto;
import musicStreaming.playListMusic.resDtos.UpdatePlayListMusicResDto;

@Service
@RequiredArgsConstructor
public class PlayListMusicService {
    private final PlayListMusicRepository playListMusicRepository;

    // 플레이 리스트 음악을 생성시키기 위해서
    public CreatePlayListMusicResDto createPlayListMusic(CreatePlayListMusicReqDto createPlayListMusicReqDto, Long userId) {
        return CreatePlayListMusicTask.createPlayListMusicTask(createPlayListMusicReqDto, userId, playListMusicRepository);
    }

    // 플레이 리스트 음악을 수정시키기 위해서
    public UpdatePlayListMusicResDto updatePlayListMusic(UpdatePlayListMusicReqDto updatePlayListMusicReqDto) {
        return UpdatePlayListMusicTask.updatePlayListMusicTask(updatePlayListMusicReqDto, playListMusicRepository);
    }
}
