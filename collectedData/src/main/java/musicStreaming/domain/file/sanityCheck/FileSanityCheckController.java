package musicStreaming.domain.file.sanityCheck;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

import musicStreaming.domain.file.sanityCheck.reqDtos.MockMusicFileDeleteFailedReqDto;
import musicStreaming.domain.file.sanityCheck.reqDtos.MockMusicFileDeletedReqDto;
import musicStreaming.domain.file.sanityCheck.reqDtos.MockMusicFileUpdateFailedReqDto;
import musicStreaming.domain.file.sanityCheck.reqDtos.MockMusicFileUpdatedReqDto;
import musicStreaming.domain.file.sanityCheck.reqDtos.MockMusicFileUploadFailedReqDto;
import musicStreaming.domain.file.sanityCheck.reqDtos.MockMusicFileUploadedReqDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/files/sanityCheck")
public class FileSanityCheckController {
    private final FileSanityCheckService fileSanityCheckService;

    // Policy 테스트용으로 MusicFileUploaded 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/MusicFileUploaded")
    public void mockMusicFileUploaded(@RequestBody MockMusicFileUploadedReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.fileSanityCheckService.mockMusicFileUploaded(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }

    // Policy 테스트용으로 MusicFileUploadFailed 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/MusicFileUploadFailed")
    public void mockMusicFileUploadFailed(@RequestBody MockMusicFileUploadFailedReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.fileSanityCheckService.mockMusicFileUploadFailed(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }


    // Policy 테스트용으로 MusicFileUpdated 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/MusicFileUpdated")
    public void mockMusicFileUpdated(@RequestBody MockMusicFileUpdatedReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.fileSanityCheckService.mockMusicFileUpdated(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }

    // Policy 테스트용으로 MusicFileUpdateFailed 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/MusicFileUpdateFailed")
    public void mockMusicFileUpdateFailed(@RequestBody MockMusicFileUpdateFailedReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.fileSanityCheckService.mockMusicFileUpdateFailed(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }


    // Policy 테스트용으로 MusicFileDeleted 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/MusicFileDeleted")
    public void mockMusicFileDeleted(@RequestBody MockMusicFileDeletedReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.fileSanityCheckService.mockMusicFileDeleted(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }

    // Policy 테스트용으로 MusicFileDeleteFailed 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/MusicFileDeleteFailed")
    public void mockMusicFileDeleteFailed(@RequestBody MockMusicFileDeleteFailedReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.fileSanityCheckService.mockMusicFileDeleteFailed(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }
}
