package musicStreaming.domain.file.sanityCheck;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import musicStreaming.domain.file.event.MusicFileDeleteFailed;
import musicStreaming.domain.file.event.MusicFileDeleted;
import musicStreaming.domain.file.event.MusicFileUpdateFailed;
import musicStreaming.domain.file.event.MusicFileUpdated;
import musicStreaming.domain.file.event.MusicFileUploadFailed;
import musicStreaming.domain.file.event.MusicFileUploaded;
import musicStreaming.domain.file.sanityCheck.reqDtos.MockMusicFileDeleteFailedReqDto;
import musicStreaming.domain.file.sanityCheck.reqDtos.MockMusicFileDeletedReqDto;
import musicStreaming.domain.file.sanityCheck.reqDtos.MockMusicFileUpdateFailedReqDto;
import musicStreaming.domain.file.sanityCheck.reqDtos.MockMusicFileUpdatedReqDto;
import musicStreaming.domain.file.sanityCheck.reqDtos.MockMusicFileUploadFailedReqDto;
import musicStreaming.domain.file.sanityCheck.reqDtos.MockMusicFileUploadedReqDto;

@Service
@RequiredArgsConstructor
public class FileSanityCheckService {

    // Policy 테스트용으로 MusicFileUploaded 이벤트를 강제로 발생시키기 위해서
    public void mockMusicFileUploaded(MockMusicFileUploadedReqDto mockData) {
        (new MusicFileUploaded(mockData)).publish();
    }

    // Policy 테스트용으로 MusicFileUploadFailed 이벤트를 강제로 발생시키기 위해서
    public void mockMusicFileUploadFailed(MockMusicFileUploadFailedReqDto mockData) {
        (new MusicFileUploadFailed(mockData)).publish();
    }
    

    // Policy 테스트용으로 MusicFileUpdated 이벤트를 강제로 발생시키기 위해서
    public void mockMusicFileUpdated(MockMusicFileUpdatedReqDto mockData) {
        (new MusicFileUpdated(mockData)).publish();
    }

    // Policy 테스트용으로 MusicFileUpdateFailed 이벤트를 강제로 발생시키기 위해서
    public void mockMusicFileUpdateFailed(MockMusicFileUpdateFailedReqDto mockData) {
        (new MusicFileUpdateFailed(mockData)).publish();
    }


    // Policy 테스트용으로 MusicFileDeleted 이벤트를 강제로 발생시키기 위해서
    public void mockMusicFileDeleted(MockMusicFileDeletedReqDto mockData) {
        (new MusicFileDeleted(mockData)).publish();
    }

    // Policy 테스트용으로 MusicFileDeleteFailed 이벤트를 강제로 발생시키기 위해서
    public void mockMusicFileDeleteFailed(MockMusicFileDeleteFailedReqDto mockData) {
        (new MusicFileDeleteFailed(mockData)).publish();
    }
}
