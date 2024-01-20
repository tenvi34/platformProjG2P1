package musicStreaming.domain.music.sanityCheck;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import musicStreaming.domain.music.event.MusicCreated;
import musicStreaming.domain.music.event.MusicDeleted;
import musicStreaming.domain.music.event.MusicDeletedByFail;
import musicStreaming.domain.music.event.MusicFileDeleteRequested;
import musicStreaming.domain.music.event.MusicFileUpdateRequested;
import musicStreaming.domain.music.event.MusicFileUploadRequested;
import musicStreaming.domain.music.event.MusicInfoUpdated;
import musicStreaming.domain.music.event.MusicLiked;
import musicStreaming.domain.music.sanityCheck.reqDtos.MockMusicCreatedReqDto;
import musicStreaming.domain.music.sanityCheck.reqDtos.MockMusicDeletedByFailReqDto;
import musicStreaming.domain.music.sanityCheck.reqDtos.MockMusicDeletedReqDto;
import musicStreaming.domain.music.sanityCheck.reqDtos.MockMusicFileDeleteRequestedReqDto;
import musicStreaming.domain.music.sanityCheck.reqDtos.MockMusicFileUpdateRequestedReqDto;
import musicStreaming.domain.music.sanityCheck.reqDtos.MockMusicFileUploadRequestedReqDto;
import musicStreaming.domain.music.sanityCheck.reqDtos.MockMusicInfoUpdatedReqDto;
import musicStreaming.domain.music.sanityCheck.reqDtos.MockMusicLikedReqDto;

@Service
@RequiredArgsConstructor
public class MusicSanityCheckService {

    // Policy 테스트용으로 MusicFileUploadRequested 이벤트를 강제로 발생시키기 위해서
    public void mockMusicFileUploadRequested(MockMusicFileUploadRequestedReqDto mockData) {
        (new MusicFileUploadRequested(mockData)).publish();
    }

    // Policy 테스트용으로 MusicCreated 이벤트를 강제로 발생시키기 위해서
    public void mockMusicCreated(MockMusicCreatedReqDto mockData) {
        (new MusicCreated(mockData)).publish();
    }

    // Policy 테스트용으로 MusicDeletedByFail 이벤트를 강제로 발생시키기 위해서
    public void mockMusicDeletedByFail(MockMusicDeletedByFailReqDto mockData) {
        (new MusicDeletedByFail(mockData)).publish();
    }

    
    // Policy 테스트용으로 MusicInfoUpdated 이벤트를 강제로 발생시키기 위해서
    public void mockMusicInfoUpdated(MockMusicInfoUpdatedReqDto mockData) {
        (new MusicInfoUpdated(mockData)).publish();
    }

    // Policy 테스트용으로 MusicFileUpdateRequested 이벤트를 강제로 발생시키기 위해서
    public void mockMusicFileUpdateRequested(MockMusicFileUpdateRequestedReqDto mockData) {
        (new MusicFileUpdateRequested(mockData)).publish();
    }


    // Policy 테스트용으로 MusicFileDeleteRequested 이벤트를 강제로 발생시키기 위해서
    public void mockMusicFileDeleteRequested(MockMusicFileDeleteRequestedReqDto mockData) {
        (new MusicFileDeleteRequested(mockData)).publish();
    }

    // Policy 테스트용으로 MusicDeleted 이벤트를 강제로 발생시키기 위해서
    public void mockMusicDeleted(MockMusicDeletedReqDto mockData) {
        (new MusicDeleted(mockData)).publish();
    }


    // Policy 테스트용으로 MusicLiked 이벤트를 강제로 발생시키기 위해서
    public void mockMusicLiked(MockMusicLikedReqDto mockData) {
        (new MusicLiked(mockData)).publish();
    }
}
