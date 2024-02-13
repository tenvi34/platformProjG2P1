package musicStreaming.sanityCheck;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import lombok.RequiredArgsConstructor;

import musicStreaming._global.dataUrlStorage.DataUrlStorageService;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

import musicStreaming.sanityCheck.exceptions.DivByZeroException;
import musicStreaming.sanityCheck.reqDtos.DeleteDataUrlFileReqDto;
import musicStreaming.sanityCheck.reqDtos.EchoWithJsonReqDto;
import musicStreaming.sanityCheck.reqDtos.LogsReqDto;
import musicStreaming.sanityCheck.reqDtos.MockMusicFileDeletedReqDto;
import musicStreaming.sanityCheck.reqDtos.MockMusicFileUpdateFailedReqDto;
import musicStreaming.sanityCheck.reqDtos.MockMusicFileUpdatedReqDto;
import musicStreaming.sanityCheck.reqDtos.MockMusicFileUploadFailedReqDto;
import musicStreaming.sanityCheck.reqDtos.MockMusicFileUploadedReqDto;
import musicStreaming.sanityCheck.reqDtos.ReadDataUrlFromDataCodeReqDto;
import musicStreaming.sanityCheck.reqDtos.WriteDataUrlToFileReqDto;
import musicStreaming.sanityCheck.resDtos.AuthenticationCheckResDto;
import musicStreaming.sanityCheck.resDtos.EchoWithJsonResDto;
import musicStreaming.sanityCheck.resDtos.LogsResDto;
import musicStreaming.sanityCheck.resDtos.ReadDataUrlFromDataCodeResDto;
import musicStreaming.sanityCheck.resDtos.WriteDataUrlToFileResDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sanityCheck")
public class SanityCheckController {
    private final SanityCheckService sanityCheckService;
    private final DataUrlStorageService dataUrlStorageService;
    private boolean isNormalSanityCheck = true;

    // 정상적인 통신 여부를 단순하게 확인해보기 위해서
    @GetMapping
    public ResponseEntity<Void> sanityCheck() {
        CustomLogger.debug(CustomLoggerType.ENTER_EXIT, "",
            String.format("{isNormalSanityCheck: %b}", this.isNormalSanityCheck));
        
        return ResponseEntity.status((this.isNormalSanityCheck ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR))
            .build();
    }

    // 에러 복구 여부 테스트를 위해서 SanityCheck시에 무조건 정상 처리가 반환되도록 만듬
    @PutMapping("/setNormal")
    public void sanityCheckSetNormal() {
        this.isNormalSanityCheck = true;
        CustomLogger.debug(CustomLoggerType.ENTER_EXIT);
        return;
    }

    // 에러 복구 여부 테스트를 위해서 SanityCheck시에 무조건 내부 서버 에러가 반환되도록 만듬
    @PutMapping("/setError")
    public void sanityCheckSetError() {
        this.isNormalSanityCheck = false;
        CustomLogger.debug(CustomLoggerType.ENTER_EXIT);
        return;
    }


    // 현재 저장된 로그들 중에서 일부분을 간편하게 가져오기 위해서
    @GetMapping("/logs")
    public ResponseEntity<LogsResDto> logs(@ModelAttribute LogsReqDto logsReqDto) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER);

            LogsResDto logsResDto = this.sanityCheckService.logs(logsReqDto);

            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{logsSize: %d}", logsResDto.getLogs().size()));
            return ResponseEntity.ok(logsResDto);

        } catch(Exception e) {
            CustomLogger.error(e, "", String.format("{logsReqDto: %s}", logsReqDto.toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 정상적인 에러 로그 출력 여부를 테스트해보기 위해서
    @GetMapping("/divByZeroCheck")
    public ResponseEntity<Integer> divByZeroCheck() {
        try {
            Integer returnNum = 1/0;
            return ResponseEntity.ok(returnNum);
        } catch(Exception e) {
            CustomLogger.error(e, "Div By Zero Check Message", String.format("{returnNum: %s}", "Undefined"));
            throw new DivByZeroException();
        }    
    }

    // File 서비스와의 상호 통신이 정상적으로 이뤄지는지 확인해보기 위해서
    @PutMapping("/echoWithJson")
    public ResponseEntity<EchoWithJsonResDto> echoWithJson(@RequestBody EchoWithJsonReqDto echoWithJsonReqDto) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{echoWithJsonReqDto: %s}", echoWithJsonReqDto.toString()));
            EchoWithJsonResDto echoWithJsonResDto = new EchoWithJsonResDto(echoWithJsonReqDto.getMessage());
            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{echoWithJsonResDto: %s}", echoWithJsonResDto.toString()));

            return ResponseEntity.ok(echoWithJsonResDto);

        } catch(Exception e) {
            CustomLogger.error(e, "", String.format("{echoWithJsonReqDto: %s}", echoWithJsonReqDto.toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }    
    }


    
    // 게이트웨이 JWT 인증시에 관련 정보를 얻을 수 있는지 테스트해보기 위해서
    @GetMapping("/authenticationCheck")
    public ResponseEntity<AuthenticationCheckResDto> authenticationCheck(@RequestHeader("User-Id") Long userId) {
        AuthenticationCheckResDto authenticationCheckResDto = new AuthenticationCheckResDto(userId);
        CustomLogger.debug(CustomLoggerType.ENTER_EXIT, "", String.format("{authenticationCheckResDto: %s}", authenticationCheckResDto.toString()));
        return ResponseEntity.ok(authenticationCheckResDto);
    }


    // Policy 테스트용으로 MusicFileUploaded 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/MusicFileUploaded")
    public void mockMusicFileUploaded(@RequestBody MockMusicFileUploadedReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.sanityCheckService.mockMusicFileUploaded(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }

    // Policy 테스트용으로 MusicFileUploadFailed 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/MusicFileUploadFailed")
    public void mockMusicFileUploadFailed(@RequestBody MockMusicFileUploadFailedReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.sanityCheckService.mockMusicFileUploadFailed(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }

    // Policy 테스트용으로 MusicFileUpdated 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/MusicFileUpdated")
    public void mockMusicFileUpdated(@RequestBody MockMusicFileUpdatedReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.sanityCheckService.mockMusicFileUpdated(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }

    // Policy 테스트용으로 MusicFileDeleted 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/MusicFileDeleted")
    public void mockMusicFileDeleted(@RequestBody MockMusicFileDeletedReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.sanityCheckService.mockMusicFileDeleted(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }

    // Policy 테스트용으로 MusicFileUpdateFailed 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/MusicFileUpdateFailed")
    public void mockMusicFileUpdateFailed(@RequestBody MockMusicFileUpdateFailedReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.sanityCheckService.mockMusicFileUpdateFailed(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }


    // DataUrlStorageService.writeDataUrlToFile SanityCheck 용도로 사용됨
    @PutMapping("/writeDataUrlToFile")
    public WriteDataUrlToFileResDto writeDataUrlToFile(@RequestBody WriteDataUrlToFileReqDto writeDataUrlToFileReqDto) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{writeDataUrlToFileReqDto: %s}", writeDataUrlToFileReqDto.toString()));
        WriteDataUrlToFileResDto writeDataUrlToFileResDto  = new WriteDataUrlToFileResDto(dataUrlStorageService.writeDataUrlToFile(writeDataUrlToFileReqDto.getDataUrl()));
        CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{writeDataUrlToFileResDto: %s}", writeDataUrlToFileResDto.toString()));
        return writeDataUrlToFileResDto;
    }

    // DataUrlStorageService.readDataUrlFromDataCode SanityCheck 용도로 사용됨
    @PutMapping("/readDataUrlFromDataCode")
    public ReadDataUrlFromDataCodeResDto readDataUrlFromDataCode(@RequestBody ReadDataUrlFromDataCodeReqDto readDataUrlFromDataCodeReqDto) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{readDataUrlFromDataCodeReqDto: %s}", readDataUrlFromDataCodeReqDto.toString()));
        ReadDataUrlFromDataCodeResDto readDataUrlFromDataCodeResDto  = new ReadDataUrlFromDataCodeResDto(dataUrlStorageService.readDataUrlFromDataCode(readDataUrlFromDataCodeReqDto.getDataUrlCode()));
        CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{readDataUrlFromDataCodeResDto: %s}", readDataUrlFromDataCodeResDto.toString()));
        return readDataUrlFromDataCodeResDto;
    }

    // DataUrlStorageService.deleteDataUrlFile SanityCheck 용도로 사용됨
    @PutMapping("/deleteDataUrlFile")
    public void deleteDataUrlFile(@RequestBody DeleteDataUrlFileReqDto deleteDataUrlFileReqDto) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{deleteDataUrlFileReqDto: %s}", deleteDataUrlFileReqDto.toString()));
        dataUrlStorageService.deleteDataUrlFile(deleteDataUrlFileReqDto.getDataUrlCode());
    }
}