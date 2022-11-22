package com.ssafit.controller;


import com.ssafit.model.dto.Calendar;
import com.ssafit.model.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/calendarapi")
public class CalendarController {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    CalendarService calendarService;

    @PostMapping("/calendar")
    public ResponseEntity<?> createCalendar(Calendar calendar) {
        System.out.println(calendar);
        calendarService.createCalendar(calendar);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @GetMapping("/calendar/{userId}")
    public ResponseEntity<List<Calendar>> getCalendarList(@PathVariable String userId) {

        List<Calendar> calendarList = calendarService.getCalendarList(userId);
        return new ResponseEntity<>(calendarList, HttpStatus.OK);
    }

    @GetMapping("/calendar")
    public ResponseEntity<List<Calendar>> getCalendar(@RequestParam String userId, @RequestParam String date) {
        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId);
        params.put("date", date);
        List<Calendar> calendarList = calendarService.getCalendar(params);
        return new ResponseEntity<>(calendarList, HttpStatus.OK);
    }

    @GetMapping("/calendar/")
    public ResponseEntity<Calendar> getCalendarById(@RequestParam int calendarId) {

        Calendar calendar = calendarService.getCalendarById(calendarId);
        return new ResponseEntity<>(calendar, HttpStatus.OK);
    }

    @PutMapping("/calendar")
    public ResponseEntity<?> modifyCalendar(Calendar calendar) {
        boolean result = calendarService.modifyCalendar(calendar);

        if (result) return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        else return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("/calendar/{calendarId}")
    public ResponseEntity<String> removeCalendar(@PathVariable int calendarId) {
        boolean result = calendarService.removeCalendar(calendarId);
        if (result) return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
        else return new ResponseEntity<>(FAIL, HttpStatus.NO_CONTENT);

    }

}
