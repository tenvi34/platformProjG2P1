package musicStreaming.comment;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import musicStreaming.comment.commentTasks.CreateCommentTask;
import musicStreaming.comment.commentTasks.UpdateCommentTask;
import musicStreaming.comment.reqDtos.CreateCommentReqDto;
import musicStreaming.comment.reqDtos.UpdateCommentReqDto;
import musicStreaming.comment.resDtos.CreateCommentResDto;
import musicStreaming.comment.resDtos.UpdateCommentResDto;

import musicStreaming.domain.CommentRepository;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    // 코멘트를 생성하기 위해서
    public CreateCommentResDto createComment(CreateCommentReqDto createCommentReqDto, Long userId) {
        return CreateCommentTask.createCommentTask(createCommentReqDto, userId, commentRepository);
    }

    // 코멘트를 업데이트하기 위해서
    public UpdateCommentResDto updateComment(UpdateCommentReqDto updateCommentReqDto) {
        return UpdateCommentTask.updateCommentTask(updateCommentReqDto, commentRepository);
    }
}
