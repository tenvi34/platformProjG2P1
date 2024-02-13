package musicStreaming.playList.playListTasks;

import java.util.Optional;

import musicStreaming._global.event.PlayListUpdated;

import musicStreaming.domain.playList.PlayList;
import musicStreaming.domain.playList.PlayListRepository;

import musicStreaming.playList.exceptions.PlayListNotFoundException;
import musicStreaming.playList.reqDtos.UpdatePlayListReqDto;
import musicStreaming.playList.resDtos.UpdatePlayListResDto;

public class UpdatePlayListTask {
    // 주어진 DataURL을 저장하고, File 서비스에 업로드 요청을 수행하기 위해서
    public static UpdatePlayListResDto updatePlayListTask(UpdatePlayListReqDto updatePlayListReqDto,
            PlayListRepository playListRepository) {

        // [1] playListRepository로 playListId와 매칭되는 PlayList 데이터를 가져옵니다.
        Optional<PlayList> optionalPlayList = playListRepository.findById(updatePlayListReqDto.getPlayListId());
        if(!optionalPlayList.isPresent()) throw new PlayListNotFoundException();
        PlayList playListToUpdate = optionalPlayList.get();

        // [2] PlayList를 수정하고 저장합니다.
        playListToUpdate.setTitle(updatePlayListReqDto.getTitle());
        playListRepository.save(playListToUpdate);

        playListRepository.save(updatePlayList);

        // [3] PlayListUpdated 이벤트를 발생시킵니다.
        (new PlayListUpdated(playListToUpdate)).publishAfterCommit();

        return new UpdatePlayListResDto(playListToUpdate);
    }
}
