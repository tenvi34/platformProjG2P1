package musicStreaming.domain.playList.sanityCheck;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import musicStreaming.domain.playList.event.PlayListCreated;
import musicStreaming.domain.playList.event.PlayListDeleted;
import musicStreaming.domain.playList.event.PlayListMusicCountUpdated;
import musicStreaming.domain.playList.event.PlayListUpdated;
import musicStreaming.domain.playList.sanityCheck.reqDtos.MockPlayListCreatedReqDto;
import musicStreaming.domain.playList.sanityCheck.reqDtos.MockPlayListDeletedReqDto;
import musicStreaming.domain.playList.sanityCheck.reqDtos.MockPlayListMusicCountUpdatedReqDto;
import musicStreaming.domain.playList.sanityCheck.reqDtos.MockPlayListUpdatedReqDto;

@Service
@RequiredArgsConstructor
public class PlayListSanityCheckService {

    // Policy 테스트용으로 PlayListCreated 이벤트를 강제로 발생시키기 위해서
    public void mockPlayListCreated(MockPlayListCreatedReqDto mockData) {
        (new PlayListCreated(mockData)).publish();
    }

    // Policy 테스트용으로 PlayListUpdated 이벤트를 강제로 발생시키기 위해서
    public void mockPlayListUpdated(MockPlayListUpdatedReqDto mockData) {
        (new PlayListUpdated(mockData)).publish();
    }

    // Policy 테스트용으로 PlayListMusicCountUpdated 이벤트를 강제로 발생시키기 위해서
    public void mockPlayListMusicCountUpdated(MockPlayListMusicCountUpdatedReqDto mockData) {
        (new PlayListMusicCountUpdated(mockData)).publish();
    }

    // Policy 테스트용으로 PlayListDeleted 이벤트를 강제로 발생시키기 위해서
    public void mockPlayListDeleted(MockPlayListDeletedReqDto mockData) {
        (new PlayListDeleted(mockData)).publish();
    }

}
