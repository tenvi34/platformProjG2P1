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

import musicStreaming.MusicApplication;

import musicStreaming._global.dataUrlStorage.DataUrlStorageService;
import musicStreaming._global.event.MusicDeleted;
import musicStreaming._global.event.MusicFileDeleted;
import musicStreaming._global.event.MusicFileUpdateFailed;
import musicStreaming._global.event.MusicFileUpdated;
import musicStreaming._global.event.MusicFileUploadFailed;
import musicStreaming._global.event.MusicFileUploaded;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;

import musicStreaming.domain.MusicTasks.DeleteDataUrlTask;
import musicStreaming.domain.MusicTasks.DeleteMusicByFailTask;
import musicStreaming.domain.MusicTasks.DeleteMusicTask;
import musicStreaming.domain.MusicTasks.UpdateFileIdTask;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "App_Music")
public class Music {    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long fileId;

    private Long createrId;

    private String title;

    private String creater;

    private Integer totalSeconds;

    private Integer likes;

    private Date createdDate;

    private Date updatedDate;

    public static MusicRepository repository() {
        MusicRepository musicRepository = MusicApplication.applicationContext.getBean(
            MusicRepository.class
        );
        return musicRepository;
    }

    public static DataUrlStorageService dataUrlStorageService() {
        DataUrlStorageService dataUrlStorageService = MusicApplication.applicationContext.getBean(
            DataUrlStorageService.class
        );
        return dataUrlStorageService;
    }


    @PrePersist
    public void onPrePersist() {
        this.likes = 0;
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

        (new MusicDeleted(this)).publishAfterCommit();
    }


    public static void updateFileId(MusicFileUploaded musicFileUploaded) {
        Music.updateFileId(musicFileUploaded.getMusicId(), musicFileUploaded.getId(), musicFileUploaded.getTotalSeconds(), musicFileUploaded.getDataUrlCode());
    }

    public static void updateFileId(MusicFileUpdated musicFileUpdated) {
        Music.updateFileId(musicFileUpdated.getMusicId(), musicFileUpdated.getId(), musicFileUpdated.getTotalSeconds(), musicFileUpdated.getDataUrlCode());
    }

    // 음악 파일이 업로드되었을 경우, 관련 파일 정보를 음악 정보에 반영시키기 위해서
    // 또한, Music 서비스에 임시로 저장되었던 DataUrlCode 파일을 삭제시키기 위해서
    public static void updateFileId(Long musicId, Long fileId, Integer totalSeconds, String dataUrlCode) {
        UpdateFileIdTask.updateFileIdTask(musicId, fileId, totalSeconds, dataUrlCode, Music.repository(), Music.dataUrlStorageService());
    }



    // 음악 파일이 업로드에 실패 했을 경우, 음악 정보를 지우고, 관련 이벤트를 발생시키기 위해서
    public static void deleteMusicByFail(MusicFileUploadFailed musicFileUploadFailed) {
        DeleteMusicByFailTask.deleteMusicByFailTask(musicFileUploadFailed, Music.repository(), Music.dataUrlStorageService());
    }

    // 음악 파일이 삭제되었을 경우, 최종적으로 관련 음악 정보를 삭제시키고, 관련 이벤트를 발생시키기 위해서
    public static void deleteMusic(MusicFileDeleted musicFileDeleted) {
        DeleteMusicTask.deleteMusicTask(musicFileDeleted, Music.repository());
    }

    // 음악 파일이 업데이트에 실패 했을 경우, 관련 DataUrl을 삭제시키기 위해서
    public static void deleteDataUrl(MusicFileUpdateFailed musicFileUpdateFailed) {
        DeleteDataUrlTask.deleteDataUrlTask(musicFileUpdateFailed, Music.dataUrlStorageService());
    }
}