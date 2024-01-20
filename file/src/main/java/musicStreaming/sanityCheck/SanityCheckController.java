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
import musicStreaming._global.externalSystemProxy.ExternalSystemProxyService;
import musicStreaming._global.externalSystemProxy.reqDtos.EchoWithJsonReqDto;
import musicStreaming._global.externalSystemProxy.resDtos.EchoWithJsonResDto;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

import musicStreaming.sanityCheck.exceptions.DivByZeroException;
import musicStreaming.sanityCheck.reqDtos.EchoWithJsonByRequestReqDto;
import musicStreaming.sanityCheck.reqDtos.LogsReqDto;
import musicStreaming.sanityCheck.reqDtos.MockMusicFileDeleteRequestedReqDto;
import musicStreaming.sanityCheck.reqDtos.MockMusicFileUpdateRequestedReqDto;
import musicStreaming.sanityCheck.reqDtos.MockMusicFileUploadRequestedReqDto;
import musicStreaming.sanityCheck.resDtos.AuthenticationCheckResDto;
import musicStreaming.sanityCheck.resDtos.EchoWithJsonByRequestResDto;
import musicStreaming.sanityCheck.resDtos.LogsResDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sanityCheck")
public class SanityCheckController {
    private final SanityCheckService sanityCheckService;
    private final ExternalSystemProxyService externalSystemProxyService;
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

    // Music 서비스와의 상호 통신이 정상적으로 이뤄지는지 확인해보기 위해서
    @PutMapping("/echoWithJsonByRequest")
    public ResponseEntity<EchoWithJsonByRequestResDto> echoWithJsonByRequest(@RequestBody EchoWithJsonByRequestReqDto echoWithJsonByRequestReqDto) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{echoWithJsonByRequestReqDto: %s}", echoWithJsonByRequestReqDto.toString()));
            
            EchoWithJsonReqDto echoWithJsonReqDto = new EchoWithJsonReqDto(echoWithJsonByRequestReqDto.getMessage());
            EchoWithJsonResDto echoWithJsonResDto = externalSystemProxyService.echoWithJson(echoWithJsonReqDto);
            EchoWithJsonByRequestResDto echoWithJsonByRequestResDto = new EchoWithJsonByRequestResDto(echoWithJsonResDto.getMessage());

            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{echoWithJsonByRequestResDto: %s}", echoWithJsonByRequestResDto.toString()));

            return ResponseEntity.ok(echoWithJsonByRequestResDto);

        } catch(Exception e) {
            CustomLogger.error(e, "", String.format("{echoWithJsonByRequestReqDto: %s}", echoWithJsonByRequestReqDto.toString()));
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


    // Policy 테스트용으로 MusicFileUploadRequested 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/MusicFileUploadRequested")
    public void mockMusicFileUploadRequested(@RequestBody MockMusicFileUploadRequestedReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.sanityCheckService.mockMusicFileUploadRequested(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }

    // Policy 테스트용으로 MusicFileUpdateRequested 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/MusicFileUpdateRequested")
    public void mockMusicFileUpdateRequested(@RequestBody MockMusicFileUpdateRequestedReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.sanityCheckService.mockMusicFileUpdateRequested(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }

    // Policy 테스트용으로 MusicFileDeleteRequested 이벤트를 강제로 발생시키기 위해서
    @PostMapping("/mock/MusicFileDeleteRequested")
    public void mockMusicFileDeleteRequested(@RequestBody MockMusicFileDeleteRequestedReqDto mockData) {
        CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{mockData: %s}", mockData.toString()));
        this.sanityCheckService.mockMusicFileDeleteRequested(mockData);
        CustomLogger.debug(CustomLoggerType.EXIT);
    }
}