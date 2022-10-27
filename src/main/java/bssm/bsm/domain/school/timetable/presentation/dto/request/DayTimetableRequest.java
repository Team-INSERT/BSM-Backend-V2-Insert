package bssm.bsm.domain.school.timetable.presentation.dto.request;

import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
public class DayTimetableRequest extends TimetableRequest {

    @Max(6) @Min(0)
    private int day;

    public DayTimetableRequest(int grade, int classNo, int day) {
        super(grade, classNo);
        this.day = day;
    }

}
