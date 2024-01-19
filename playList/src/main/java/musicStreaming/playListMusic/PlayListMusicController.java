package musicStreaming.playListMusic;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/playListMusics")
public class PlayListMusicController {
    // private final PlayListMusicService playListMusicService;
}
