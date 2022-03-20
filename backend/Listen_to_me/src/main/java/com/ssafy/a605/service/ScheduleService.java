package com.ssafy.a605.service;

import com.ssafy.a605.model.dto.ScheduleDto;
import com.ssafy.a605.model.entity.Schedule;
import com.ssafy.a605.model.response.schedule.ScheduleInfoRes;
import com.ssafy.a605.model.response.schedule.ScheduleStateRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleService {
    public Page<ScheduleInfoRes> getCounselorApprovedSchedule(String userEmail, Pageable pageRequest) throws Exception;
    public Page<ScheduleInfoRes> getCounselorEndedSchedule(String userEmail, Pageable pageRequest) throws Exception;
    public Page<ScheduleInfoRes> getClientApprovedSchedule(String userEmail, Pageable pageRequest) throws Exception;
    public Page<ScheduleInfoRes> getClientEndedSchedule(String userEmail, Pageable pageRequest) throws Exception;
    public boolean setScheduleTime(ScheduleDto scheduleDto, String userEmail) throws Exception;
    public boolean checkScheduleTime(LocalDateTime dateTime, String userEmail) throws Exception;
    public List<ScheduleStateRes> getCounselorSchedule(String userEmail) throws Exception;
    public boolean checkPoint(String userEmail) throws Exception;
    public boolean requestCounseling(String userEmail, int scheduleId, boolean isSticker) throws Exception;
    public boolean acceptCounseling(String userEmail, int scheduleId) throws Exception;
    public boolean endCounseling(String userEmail, int scheduleId) throws Exception;
    public ScheduleInfoRes getCounseling(int scheduleId) throws Exception;
}
