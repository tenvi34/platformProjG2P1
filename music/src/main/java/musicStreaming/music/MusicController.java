package musicStreaming.music;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@RestController
@Transactional
@RequiredArgsConstructor
@RequestMapping("/musics")
public class MusicController {
    // private final MusicService musicService;
}
