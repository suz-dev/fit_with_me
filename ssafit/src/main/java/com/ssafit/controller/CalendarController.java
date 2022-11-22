package com.ssafit.controller;


import com.ssafit.model.dto.Calendar;
import com.ssafit.model.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        calendarService.createCalendar(calendar);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @GetMapping("/calendar/{userId}")
    public ResponseEntity<?> getCalendarList(@PathVariable String userId) {
        List<Calendar>
    }

}
