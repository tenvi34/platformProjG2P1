package musicStreaming.domain.music.sanityCheck;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/musics/sanityCheck")
public class MusicSanityCheckController {
    // private final MusicSanityCheckService musicSanityCheckService;
}
