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

import musicStreaming.FileApplication;

import musicStreaming._global.event.MusicFileDeleteRequested;
import musicStreaming._global.event.MusicFileUpdateRequested;
import musicStreaming._global.event.MusicFileUploadRequested;
import musicStreaming._global.externalSystemProxy.ExternalSystemProxyService;
import musicStreaming._global.logger.CustomLogger;
import musicStreaming._global.logger.CustomLoggerType;
import musicStreaming._global.resources.ResourcesService;

import musicStreaming.domain.FileTasks.DeleteMusicFileTask;
import musicStreaming.domain.FileTasks.UpdateMusicFileTask;
import musicStreaming.domain.FileTasks.UploadMusicFileTask;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "App_File")
public class File {    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long createrId;

    private String path;

    private Date createdDate;

    private Date updatedDate;

    public static FileRepository repository() {
        FileRepository fileRepository = FileApplication.applicationContext.getBean(
            FileRepository.class
        );
        return fileRepository;
    }

    public static ResourcesService resourcesService() {
        ResourcesService resourcesService = FileApplication.applicationContext.getBean(
            ResourcesService.class
        );
        return resourcesService;
    }

    public static ExternalSystemProxyService externalSystemProxyService() {
        ExternalSystemProxyService externalSystemProxyService = FileApplication.applicationContext.getBean(
            ExternalSystemProxyService.class
        );
        return externalSystemProxyService;
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
    }


    // 관련 음악 파일 DATA URL을 요청해서 얻고, 디코딩 후, 파일을 저장시키기 위해서
    public static void uploadMusicFile(MusicFileUploadRequested musicFileUploadRequested) {
        UploadMusicFileTask.uploadMusicFileTask(musicFileUploadRequested, File.repository(), File.resourcesService(), File.externalSystemProxyService());
    }

    // 이미 존재하는 파일 정보를 갱신하면서 관련 음악 파일 DATA URL을 요청해서 얻고, 디코딩 후, 파일을 저장시키기 위해서
    public static void updateMusicFile(MusicFileUpdateRequested musicFileUpdateRequested) throws Exception {
        UpdateMusicFileTask.updateMusicFileTask(musicFileUpdateRequested, File.repository(), File.resourcesService(), File.externalSystemProxyService());
    }

    // 요청된 음악 파일을 삭제시키기 위해서
    public static void deleteMusicFile(MusicFileDeleteRequested musicFileDeleteRequested) {
        DeleteMusicFileTask.deleteMusicFileTask(musicFileDeleteRequested, File.repository(), File.resourcesService());
    }
}