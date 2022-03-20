package com.ssafy.a605.api.controller.counselor;

import com.ssafy.a605.api.controller.counselor.CounselorController;
import com.ssafy.a605.model.dto.ScheduleDto;
import com.ssafy.a605.model.entity.Schedule;
import com.ssafy.a605.model.response.schedule.ScheduleInfoRes;
import com.ssafy.a605.model.response.schedule.ScheduleStateRes;
import com.ssafy.a605.service.CounselorService;
import com.ssafy.a605.service.JwtServiceImpl;
import com.ssafy.a605.service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/schedule-api")
public class ScheduleController {

    public static final Logger logger= LoggerFactory.getLogger(CounselorController.class);
    private static final String SUCCESS= "success";
    private static final String FAIL= "fail";

    @Autowired
    private JwtServiceImpl jwtService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private CounselorService userService;

    //1. 상담자 -> 되는 상담 시간 지정
    @PostMapping("/time")
    public ResponseEntity<String> setScheduleTime(@RequestBody ScheduleDto scheduleDto, HttpServletRequest request) throws Exception {
        if(scheduleService.checkScheduleTime(scheduleDto.getDateTime(), jwtService.getUserId())) return new ResponseEntity<String>("already exists", HttpStatus.OK);
        //scheduleDto.setCounselor(userService.getCounselorInfo(jwtService.getUserId()));
        if(scheduleService.setScheduleTime(scheduleDto, jwtService.getUserId())){
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
    }

    //2. 상담사 상세페이지에서 상담사의 일정 확인
    @GetMapping("/schedule/{counselor}")
    public ResponseEntity<List<ScheduleStateRes>> getCounselorSchedule(@PathVariable("counselor") String counselor) throws Exception {
        return new ResponseEntity<List<ScheduleStateRes>>(scheduleService.getCounselorSchedule(counselor), HttpStatus.OK);
    }

    //3. 내담자 -> 상담 신청
    @PostMapping("/request")
    public ResponseEntity<String> requestCounseling(@RequestBody Map<String,String> param, HttpServletRequest request) throws Exception {
        HttpStatus status = HttpStatus.ACCEPTED;
        if (jwtService.isUsable(request.getHeader("access-token"))) {
            int scheduleId = Integer.parseInt(param.get("scheduleId"));
            String sticker = param.get("isSticker");
            boolean isSticker = false;
            if(sticker.equals("true")) isSticker = true;
            else isSticker = false;

            if(!scheduleService.checkPoint(jwtService.getUserId())){
                return new ResponseEntity<String>("no enough points", HttpStatus.OK);
            }
            if(scheduleService.requestCounseling(jwtService.getUserId(), scheduleId, isSticker)){
                return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
    }

    //4. 상담자 -> 상담 수락
    @GetMapping("/accept/{scheduleId}")
    public ResponseEntity<String> acceptCounseling(@PathVariable("scheduleId") int scheduleId) throws Exception {
        if(scheduleService.acceptCounseling(jwtService.getUserId(), scheduleId)){
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
    }

    // 5. 상담 종료
    @GetMapping("/end/{scheduleId}")
    public ResponseEntity<String> endCounseling(@PathVariable("scheduleId") int scheduleId) throws Exception {
        if(scheduleService.endCounseling(jwtService.getUserId(), scheduleId)){
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
    }

    //id -> 상담 내역 하나 불러오기 (상담 자세히보기)
    @GetMapping("/{scheduleId}")
    public ResponseEntity<ScheduleInfoRes> getCounseling(@PathVariable("scheduleId") int scheduleId, HttpServletRequest request) throws Exception {
        return new ResponseEntity<ScheduleInfoRes>(scheduleService.getCounseling(scheduleId),HttpStatus.OK);
    }

    //상담자 -> 자신의 승인된 상담 내역 불러오기
    @GetMapping("/counselor/approved")
    public ResponseEntity<Page<ScheduleInfoRes>> getCounselorApprovedSchedule(@PageableDefault(size = 5, sort = "dateTime") Pageable pageRequest, HttpServletRequest request) throws Exception {
        HttpStatus status = HttpStatus.ACCEPTED;
        if (jwtService.isUsable(request.getHeader("access-token"))) {
            return new ResponseEntity<Page<ScheduleInfoRes>>(scheduleService.getCounselorApprovedSchedule(jwtService.getUserId(), pageRequest), HttpStatus.OK);
        } else {
            logger.error("사용 불가능 토큰!!!");
            status = HttpStatus.ACCEPTED;
        }
        return new ResponseEntity<Page<ScheduleInfoRes>>(HttpStatus.BAD_REQUEST);
    }

    //상담자 -> 자신의 종료된 상담 내역 불러오기
    @GetMapping("/counselor/ended")
    public ResponseEntity<Page<ScheduleInfoRes>> getCounselorEndedSchedule(@PageableDefault(size = 5, sort = "dateTime") Pageable pageRequest, HttpServletRequest request) throws Exception {
        if (jwtService.isUsable(request.getHeader("access-token"))) {
            return new ResponseEntity<Page<ScheduleInfoRes>>(scheduleService.getCounselorEndedSchedule(jwtService.getUserId(), pageRequest), HttpStatus.OK);
        } else {
            logger.error("사용 불가능 토큰!!!");
        }
        return new ResponseEntity<Page<ScheduleInfoRes>>(HttpStatus.BAD_REQUEST);
    }

    //내담자 -> 자신의 승인된 상담 내역 불러오기
    @GetMapping("/client/approved")
    public ResponseEntity<Page<ScheduleInfoRes>> getClientApprovedSchedule(@PageableDefault(size = 5, sort = "dateTime") Pageable pageRequest, HttpServletRequest request) throws Exception {
        if (jwtService.isUsable(request.getHeader("access-token"))) {
            return new ResponseEntity<Page<ScheduleInfoRes>>(scheduleService.getClientApprovedSchedule(jwtService.getUserId(), pageRequest), HttpStatus.OK);
        } else {
            logger.error("사용 불가능 토큰!!!");
        }
        return new ResponseEntity<Page<ScheduleInfoRes>>(HttpStatus.BAD_REQUEST);
    }

    //내담자 -> 자신의 종료된 상담 내역 불러오기
    @GetMapping("/client/ended")
    public ResponseEntity<Page<ScheduleInfoRes>> getClientEndedSchedule(@PageableDefault(size = 5, sort = "dateTime") Pageable pageRequest, HttpServletRequest request) throws Exception {
        if (jwtService.isUsable(request.getHeader("access-token"))) {
            return new ResponseEntity<Page<ScheduleInfoRes>>(scheduleService.getClientEndedSchedule(jwtService.getUserId(), pageRequest), HttpStatus.OK);
        } else {
            logger.error("사용 불가능 토큰!!!");
        }
        return new ResponseEntity<Page<ScheduleInfoRes>>(HttpStatus.BAD_REQUEST);
    }
}