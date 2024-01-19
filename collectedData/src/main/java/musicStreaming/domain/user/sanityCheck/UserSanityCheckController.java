package musicStreaming.domain.user.sanityCheck;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users/sanityCheck")
public class UserSanityCheckController {
    // private final UserSanityCheckService userSanityCheckService;
}
