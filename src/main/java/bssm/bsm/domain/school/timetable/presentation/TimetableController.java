package bssm.bsm.domain.school.timetable.presentation;

import bssm.bsm.domain.school.timetable.presentation.dto.request.TimetableRequest;
import bssm.bsm.domain.school.timetable.presentation.dto.response.TimetableResponse;
import bssm.bsm.domain.school.timetable.service.TimetableService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("timetable")
@RequiredArgsConstructor
public class TimetableController {

    private final TimetableService timetableService;

    @GetMapping("{grade}/{classNo}")
    public List<List<TimetableResponse>> getTimetable(
            @PathVariable int grade,
            @PathVariable int classNo
    ) {
        return timetableService.getTimetableList(new TimetableRequest(grade, classNo));
    }

}