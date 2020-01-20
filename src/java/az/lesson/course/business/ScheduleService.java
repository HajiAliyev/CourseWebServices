package az.lesson.course.business;

import az.lesson.course.model.AdvancedSearch;
import az.lesson.course.model.Schedule;

import java.util.List;

public interface ScheduleService {

    boolean addSchedule(Schedule schedule) throws Exception;

    List<Schedule> getScheduleList() throws Exception;

    List<Schedule> searchSchedule(String keyword) throws  Exception;

    List<Schedule> advancedSearchScheduleList(AdvancedSearch advancedSearch) throws Exception;
}
