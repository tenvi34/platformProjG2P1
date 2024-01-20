package musicStreaming.domain.playListMusic.sanityCheck;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import musicStreaming.domain.playListMusic.event.PlayListMusicCreated;
import musicStreaming.domain.playListMusic.event.PlayListMusicDeleted;
import musicStreaming.domain.playListMusic.event.PlayListMusicUpdated;
import musicStreaming.domain.playListMusic.sanityCheck.reqDtos.MockPlayListMusicCreatedReqDto;
import musicStreaming.domain.playListMusic.sanityCheck.reqDtos.MockPlayListMusicDeletedReqDto;
import musicStreaming.domain.playListMusic.sanityCheck.reqDtos.MockPlayListMusicUpdatedReqDto;

@Service
@RequiredArgsConstructor
public class PlayListMusicSanityCheckService {

    // Policy 테스트용으로 PlayListMusicCreated 이벤트를 강제로 발생시키기 위해서
    public void mockPlayListMusicCreated(MockPlayListMusicCreatedReqDto mockData) {
        (new PlayListMusicCreated(mockData)).publish();
    }

    // Policy 테스트용으로 PlayListMusicUpdated 이벤트를 강제로 발생시키기 위해서
    public void mockPlayListMusicUpdated(MockPlayListMusicUpdatedReqDto mockData) {
        (new PlayListMusicUpdated(mockData)).publish();
    }

    // Policy 테스트용으로 PlayListMusicDeleted 이벤트를 강제로 발생시키기 위해서
    public void mockPlayListMusicDeleted(MockPlayListMusicDeletedReqDto mockData) {
        (new PlayListMusicDeleted(mockData)).publish();
    }

}
