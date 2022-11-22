package com.ssafit.model.service;

import com.ssafit.model.dto.Calendar;
import com.ssafit.model.dto.Review;

import java.util.HashMap;
import java.util.List;

public interface CalendarService {

    // 일정 작성
    void createCalendar(Calendar calendar);

    // id로 일정 조회
    List<Calendar> getCalendarList(String userId);

    // id + date로 일정 조회
    List<Calendar> getCalendar(HashMap<String, String> params);

    // calendarId로 조회
    public Calendar getCalendarById(int calendarId);

    // 일정 수정
    boolean modifyCalendar(Calendar calendar);

    // 일정 삭제
    boolean removeCalendar(int calendarId);


}
