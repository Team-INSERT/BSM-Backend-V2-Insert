package bssm.bsm.domain.board.emoticon.presentation;

import bssm.bsm.domain.board.emoticon.presentation.dto.req.EmoticonDeleteReq;
import bssm.bsm.domain.board.emoticon.presentation.dto.res.EmoticonRes;
import bssm.bsm.domain.board.emoticon.service.EmoticonAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("admin/emoticon")
@RequiredArgsConstructor
public class EmoticonAdminController {

    private final EmoticonAdminService emoticonAdminService;

    @GetMapping("inactive")
    public List<EmoticonRes> getInactiveEmoticonList() {
        return emoticonAdminService.getInactiveEmoticonList();
    }

    @DeleteMapping("delete")
    public void deleteEmoticon(@Valid @RequestBody EmoticonDeleteReq dto) {
        emoticonAdminService.deleteEmoticon(dto);
    }

    @PutMapping("{id}")
    public void activateEmoticon(@PathVariable long id) {
        emoticonAdminService.activateEmoticon(id);
    }

}
