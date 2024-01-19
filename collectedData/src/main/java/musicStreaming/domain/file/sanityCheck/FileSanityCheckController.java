package musicStreaming.domain.file.sanityCheck;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/files/sanityCheck")
public class FileSanityCheckController {
    // private final FileSanityCheckService fileSanityCheckService;
}
