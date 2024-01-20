package musicStreaming.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PostRemove;
import javax.persistence.PreUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import musicStreaming.CommentApplication;

import musicStreaming._global.event.CommentDeleted;
import musicStreaming._global.event.MusicDeleted;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "App_Comment")
public class Comment {    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long createrId;

    private Long musicId;

    private String content;

    private Date createdDate;

    private Date updatedDate;

    public static CommentRepository repository() {
        CommentRepository commentRepository = CommentApplication.applicationContext.getBean(
            CommentRepository.class
        );
        return commentRepository;
    }


    @PrePersist
    public void onPrePersist() {
        this.createdDate = new Date();
        this.updatedDate = new Date();

        CustomLogger.debug(
            CustomLoggerType.EFFECT,
            String.format("Try to create %s by using JPA", this.getClass().getSimpleName()),
            String.format("{%s: %s}", this.getClass().getSimpleName(), this.toString())
        );
    }

    @PostPersist
    public void onPostPersist() {
        CustomLogger.debug(
            CustomLoggerType.EFFECT,
            String.format("%s is created by using JPA", this.getClass().getSimpleName()),
            String.format("{%s: %s}", this.getClass().getSimpleName(), this.toString())
        );
    }


    @PreUpdate
    public void onPreUpdate() {
        this.updatedDate = new Date();
    
        CustomLogger.debug(
            CustomLoggerType.EFFECT,
            String.format("Try to update %s by using JPA", this.getClass().getSimpleName()),
            String.format("{%s: %s}", this.getClass().getSimpleName(), this.toString())
        );
    }

    @PostUpdate
    public void onPostUpdate() {
        CustomLogger.debug(
            CustomLoggerType.EFFECT,
            String.format("%s is updated by using JPA", this.getClass().getSimpleName()),
            String.format("{%s: %s}", this.getClass().getSimpleName(), this.toString())
        );
    }


    @PreRemove
    public void onPreRemove() {
        CustomLogger.debug(
            CustomLoggerType.EFFECT, 
            String.format("Try to delete %s by using JPA", this.getClass().getSimpleName()),
            String.format("{%s: %s}", this.getClass().getSimpleName(), this.toString())
        );
    }

    @PostRemove
    public void onPostRemove() {
        CustomLogger.debug(
            CustomLoggerType.EFFECT,
            String.format("%s is deleted by using JPA", this.getClass().getSimpleName()),
            String.format("{%s: %s}", this.getClass().getSimpleName(), this.toString())
        );

        (new CommentDeleted(this)).publishAfterCommit();
    }


    // 음악 정보가 삭제되었을 경우, 연관된 코멘트 정보들을 삭제시키기 위해서
    public static void deleteComment(MusicDeleted musicDeleted) {
        CustomLogger.debug(CustomLoggerType.EFFECT, "TODO: deleteComment(MusicDeleted)");
    }  
}