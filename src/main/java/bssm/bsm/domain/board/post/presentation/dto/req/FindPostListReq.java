package bssm.bsm.domain.board.post.presentation.dto.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class FindPostListReq {

    @NotBlank
    private String boardId;

    @NotBlank
    private String category;

    private Pageable pageable;

}
