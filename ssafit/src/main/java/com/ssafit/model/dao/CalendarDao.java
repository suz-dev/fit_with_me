package com.ssafit.model.dao;

import com.ssafit.model.dto.Calendar;

import java.util.HashMap;
import java.util.List;

public interface CalendarDao {

    // 일정 작성
    void insertCalendar(Calendar calendar);
    
    // id로 일정 조회
    List<Calendar> selectAllCalendar(String userId);


    // id + date로 일정 조회
    List<Calendar> selectCalendarByDate(HashMap<String, String> params);

    // calendarId로 조회
    Calendar selectCalendarById(int calendarId);

    // 일정 수정
    int updateCalendar(Calendar calendar);

    // 일정 삭제
    int deleteCalendar(int calendarId);

}
