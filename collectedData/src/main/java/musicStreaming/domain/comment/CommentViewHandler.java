package musicStreaming.domain.comment;

import java.util.Optional;

import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import musicStreaming._global.config.kafka.KafkaProcessor;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

import musicStreaming.domain.comment.event.CommentCreated;
import musicStreaming.domain.comment.event.CommentDeleted;
import musicStreaming.domain.comment.event.CommentUpdated;
import musicStreaming.domain.comment.exceptions.CommentNotFoundException;

@Service
@RequiredArgsConstructor
public class CommentViewHandler {
    private final CommentRepository commentRepository;

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CommentCreated'"
    )
    public void whenCommentCreated_then_createComment(
        @Payload CommentCreated commentCreated
    ) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", commentCreated.getClass().getSimpleName(), commentCreated.toString()));
            if (!commentCreated.validate()) return;

            Comment savedComment = this.commentRepository.save(
                Comment.builder()
                    .commentId(commentCreated.getId())
                    .createrId(commentCreated.getCreaterId())
                    .musicId(commentCreated.getMusicId())
                    .content(commentCreated.getContent())
                    .createdDate(commentCreated.getCreatedDate())
                    .updatedDate(commentCreated.getUpdatedDate())
                    .status("CommentCreated")
                    .build()
            );

            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", savedComment.getClass().getSimpleName(), savedComment.toString()));

        } catch (Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", commentCreated.getClass().getSimpleName(), commentCreated.toString()));
        }
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CommentUpdated'"
    )
    public void whenCommentUpdated_then_updateCommentContent(
        @Payload CommentUpdated commentUpdated
    ) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", commentUpdated.getClass().getSimpleName(), commentUpdated.toString()));
            if (!commentUpdated.validate()) return;

            Optional<Comment> optionalComment = this.commentRepository.findByCommentId(commentUpdated.getId());
            if(!optionalComment.isPresent()) throw new CommentNotFoundException();


            Comment commentToUpdate = optionalComment.get();
            commentToUpdate.setContent(commentUpdated.getContent());
            commentToUpdate.setUpdatedDate(commentUpdated.getUpdatedDate());
            commentToUpdate.setStatus("CommentUpdated");

            Comment savedComment = this.commentRepository.save(commentToUpdate);


            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", savedComment.getClass().getSimpleName(), savedComment.toString()));

        } catch (Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", commentUpdated.getClass().getSimpleName(), commentUpdated.toString()));
        }
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='CommentDeleted'"
    )
    public void whenCommentDeleted_then_updateCommentStatus(
        @Payload CommentDeleted commentDeleted
    ) {
        try {

            CustomLogger.debug(CustomLoggerType.ENTER, "", String.format("{%s: %s}", commentDeleted.getClass().getSimpleName(), commentDeleted.toString()));
            if (!commentDeleted.validate()) return;

            Optional<Comment> optionalComment = this.commentRepository.findByCommentId(commentDeleted.getId());
            if(!optionalComment.isPresent()) throw new CommentNotFoundException();


            Comment commentToUpdate = optionalComment.get();
            commentToUpdate.setStatus("CommentDeleted");

            Comment savedComment = this.commentRepository.save(commentToUpdate);


            CustomLogger.debug(CustomLoggerType.EXIT, "", String.format("{%s: %s}", savedComment.getClass().getSimpleName(), savedComment.toString()));

        } catch (Exception e) {
            CustomLogger.error(e, "", String.format("{%s: %s}", commentDeleted.getClass().getSimpleName(), commentDeleted.toString()));
        }
    }
}
