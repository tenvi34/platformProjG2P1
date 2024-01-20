package musicStreaming.comment;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

import musicStreaming.comment.reqDtos.CreateCommentReqDto;
import musicStreaming.comment.reqDtos.UpdateCommentReqDto;
import musicStreaming.comment.resDtos.CreateCommentResDto;
import musicStreaming.comment.resDtos.UpdateCommentResDto;
import musicStreaming.domain.Comment;

@Service
@RequiredArgsConstructor
public class CommentService {
    // private final CommentRepository commentRepository;

    // 코멘트를 생성하기 위해서
    public CreateCommentResDto createComment(CreateCommentReqDto createCommentReqDto, Long userId) {
        CustomLogger.debug(CustomLoggerType.EFFECT, "TODO: createComment");
        return new CreateCommentResDto(new Comment());
    }

    // 코멘트를 업데이트하기 위해서
    public UpdateCommentResDto updateComment(UpdateCommentReqDto updateCommentReqDto) {
        CustomLogger.debug(CustomLoggerType.EFFECT, "TODO: updateComment");
        return new UpdateCommentResDto(new Comment());
    }
}
