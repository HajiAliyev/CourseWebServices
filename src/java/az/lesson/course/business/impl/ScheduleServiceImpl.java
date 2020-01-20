package az.lesson.course.business.impl;

import az.lesson.course.dao.ScheduleDao;
import az.lesson.course.business.ScheduleService;
import az.lesson.course.model.AdvancedSearch;
import az.lesson.course.model.Schedule;

import java.util.List;

public class ScheduleServiceImpl implements ScheduleService {
    private ScheduleDao scheduleDao;

    public ScheduleServiceImpl(ScheduleDao scheduleDao) {
        this.scheduleDao = scheduleDao;
    }

    @Override
    public boolean addSchedule(Schedule schedule) throws Exception {


        return scheduleDao.addSchedule(schedule);
    }

    @Override
    public List<Schedule> getScheduleList() throws Exception {
        return scheduleDao.getScheduleList();
    }

    @Override
    public List<Schedule> searchSchedule(String keyword) throws Exception {
        return scheduleDao.searchSchedule(keyword);
    }

    @Override
    public List<Schedule> advancedSearchScheduleList(AdvancedSearch advancedSearch) throws Exception {
        return scheduleDao.advancedSearchScheduleList(advancedSearch);
    }
}
