package com.ssafy.a605.api.controller.statistics;

import com.ssafy.a605.service.CounselorService;
import com.ssafy.a605.service.JwtServiceImpl;
import com.ssafy.a605.service.StatisticsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/statistics-api")
public class StatisticsController {

    public static final Logger logger= LoggerFactory.getLogger(StatisticsController.class);
    private static final String SUCCESS= "success";
    private static final String FAIL= "fail";

    @Autowired
    private JwtServiceImpl jwtService;

    @Autowired
    private CounselorService counselorService;

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/gender")
    public ResponseEntity<List<Long>> getGender(HttpServletRequest request) throws Exception {
        List<Integer> response = new ArrayList<>();
        if(jwtService.isUsable(request.getHeader("access-token"))){
            if(counselorService.checkId(jwtService.getUserId())){
                return new ResponseEntity<List<Long>>(statisticsService.getGender(jwtService.getUserId()), HttpStatus.OK);
            }
        }
        return new ResponseEntity<List<Long>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/age")
    public ResponseEntity<Map<String, Object>> getAge(HttpServletRequest request) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        if(jwtService.isUsable(request.getHeader("access-token"))){
            if(counselorService.checkId(jwtService.getUserId())){
                String[] list = new String []{"10~19세", "20~29세", "30~39세", "40~49세", "50~59세", "60세 이상"};
                long[] age_value = statisticsService.getAgeValue(jwtService.getUserId());
                resultMap.put("age_list", list);
                resultMap.put("age_value", age_value);
                return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
            }
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/month")
    public ResponseEntity<Map<String, Object>> getMonth(HttpServletRequest request) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        if(jwtService.isUsable(request.getHeader("access-token"))){
            List<String> month_list = new ArrayList<>();
            for(int i=1;i<13;i++) {
                month_list.add(i+"월");
            }
            long[] month_count = statisticsService.getMonthCount(jwtService.getUserId());
            resultMap.put("month_list", month_list);
            resultMap.put("month_count", month_count);
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
    }
}
