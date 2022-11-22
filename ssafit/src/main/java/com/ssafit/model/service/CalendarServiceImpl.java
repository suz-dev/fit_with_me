package com.ssafit.model.service;

import com.ssafit.model.dao.CalendarDao;
import com.ssafit.model.dto.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class CalendarServiceImpl implements CalendarService{

    @Autowired
    CalendarDao calendarDao;
    @Override
    public void createCalendar(Calendar calendar) {
        calendarDao.insertCalendar(calendar);
    }

    @Override
    public List<Calendar> getCalendarList(String userId) {
        return calendarDao.selectAllCalendar(userId);
    }

    @Override
    public List<Calendar> getCalendar(HashMap<String, String> params) {
        return calendarDao.selectCalendarByDate(params);
    }

    @Override
    public Calendar getCalendarById(int calendarId) {
        return calendarDao.selectCalendarById(calendarId);
    }

    @Override
    public boolean modifyCalendar(Calendar calendar) {
        Calendar originCalendar = calendarDao.selectCalendarById(calendar.getCalendarId());

        originCalendar.setPart(calendar.getPart());
        originCalendar.setVideoUrl(calendar.getVideoUrl());
        originCalendar.setStarTime(calendar.getStarTime());
        originCalendar.setEndTime(calendar.getEndTime());
        originCalendar.setMemo(calendar.getMemo());

        return calendarDao.updateCalendar(originCalendar)==1;
    }

    @Override
    public boolean removeCalendar(int calendarId) {
        return calendarDao.deleteCalendar(calendarId)==1;
    }
}
