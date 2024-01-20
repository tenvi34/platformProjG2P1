package musicStreaming.comment;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;
import musicStreaming.comment.reqDtos.CreateCommentReqDto;
import musicStreaming.comment.reqDtos.UpdateCommentReqDto;
import musicStreaming.comment.resDtos.CreateCommentResDto;
import musicStreaming.comment.resDtos.UpdateCommentResDto;

import javax.transaction.Transactional;

@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {
    private final CommentService commentService;

    // 코멘트를 생성하기 위해서
    @PutMapping("/createComment")
    public ResponseEntity<CreateCommentResDto> createComment(@RequestBody CreateCommentReqDto createCommentReqDto, @RequestHeader("User-Id") Long userId) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", createCommentReqDto.getClass().getSimpleName(), createCommentReqDto.toString()));
        
            CreateCommentResDto createCommentResDto = this.commentService.createComment(createCommentReqDto, userId);
        
            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", createCommentResDto.getClass().getSimpleName(), createCommentResDto.toString()));
            return ResponseEntity.ok(createCommentResDto);

        } catch(Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", createCommentReqDto.getClass().getSimpleName(), createCommentReqDto.toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // 코멘트를 업데이트하기 위해서
    @PutMapping("/updateComment")
    public ResponseEntity<UpdateCommentResDto> updateComment(@RequestBody UpdateCommentReqDto updateCommentReqDto) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", updateCommentReqDto.getClass().getSimpleName(), updateCommentReqDto.toString()));
        
            UpdateCommentResDto updateCommentResDto = this.commentService.updateComment(updateCommentReqDto);
        
            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", updateCommentResDto.getClass().getSimpleName(), updateCommentResDto.toString()));
            return ResponseEntity.ok(updateCommentResDto);

        } catch(Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", updateCommentReqDto.getClass().getSimpleName(), updateCommentReqDto.toString()));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
