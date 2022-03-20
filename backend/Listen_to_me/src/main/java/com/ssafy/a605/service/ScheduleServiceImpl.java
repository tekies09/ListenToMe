package com.ssafy.a605.service;

import com.ssafy.a605.model.dto.ScheduleDto;
import com.ssafy.a605.model.entity.Client;
import com.ssafy.a605.model.entity.Counselor;
import com.ssafy.a605.model.entity.Schedule;
import com.ssafy.a605.model.response.schedule.ScheduleInfoRes;
import com.ssafy.a605.model.response.schedule.ScheduleStateRes;
import com.ssafy.a605.repository.ClientRepository;
import com.ssafy.a605.repository.CounselorRepository;
import com.ssafy.a605.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleServiceImpl implements ScheduleService {
    final private CounselorRepository counselorRepository;
    final private ScheduleRepository scheduleRepository;
    final private ClientRepository clientRepository;

    @Override
    @Transactional
    public boolean setScheduleTime(ScheduleDto scheduleDto, String userEmail) throws Exception {
        Counselor counselor = counselorRepository.findByEmail(userEmail).orElseThrow(
                ()->  new NullPointerException("회원정보가 존재 하지 않습니다")
        );
        Schedule schedule = new Schedule();
        schedule.setTime(scheduleDto);
        schedule.setCounselor(counselor);
        Schedule ret = scheduleRepository.save(schedule);
        return ret.equals(schedule);
    }

    @Override
    public boolean checkScheduleTime(LocalDateTime dateTime, String userEmail) throws Exception {
        return scheduleRepository.existsScheduleByDateTimeEqualsAndCounselor_Email(dateTime, userEmail);
    }

    @Override
    public List<ScheduleStateRes> getCounselorSchedule(String userEmail) throws Exception {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime start = now.withHour(0);
        LocalDateTime end = now.plusDays(7).withHour(23);
        //LocalDateTime start = now.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY)).withHour(0); 이번주
        //LocalDateTime end = now.with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY)).withHour(23);
        List<ScheduleDto> schedule = scheduleRepository.findAllByCounselor_EmailAndDateTimeBetween(userEmail, start, end);

        List<ScheduleStateRes> list = new ArrayList<>();
        for(ScheduleDto s: schedule){
            list.add(new ScheduleStateRes(s.getId(), s.getDateTime(), s.getState()));
        }
        return list;
    }

    @Override
    public boolean checkPoint(String userEmail) throws Exception {
        Client client = clientRepository.findByEmail(userEmail).orElseThrow(
                ()->  new NullPointerException("회원정보가 존재 하지 않습니다")
        );
        int point = client.getPoint()-1000;
        if(point<0) return false;
        return true;
    }

    @Override
    public boolean requestCounseling(String userEmail, int scheduleId, boolean isSticker) throws Exception {
        Client client = clientRepository.findByEmail(userEmail).orElseThrow(
                ()->  new NullPointerException("회원정보가 존재 하지 않습니다")
        );
        Schedule schedule = scheduleRepository.findById(scheduleId);
        if(schedule.getState()!=0) return false;
        schedule.setState(1);
        schedule.setSticker(isSticker);
        int point = client.getPoint()-1000;
        client.setPoint(point);
        schedule.setClient(client);
        Client client1 = clientRepository.save(client);
        Schedule ret = scheduleRepository.save(schedule);
        return (ret.equals(schedule) && client1.equals(client));
    }

    @Override
    public boolean acceptCounseling(String userEmail, int scheduleId) throws Exception {
        Counselor counselor = counselorRepository.findByEmail(userEmail).orElseThrow(
                ()->  new NullPointerException("회원정보가 존재 하지 않습니다")
        );
        Schedule schedule = scheduleRepository.findById(scheduleId);
        if(schedule.getState()!=1 || schedule.getCounselor() != counselor) return false;
        schedule.setState(2);
        Schedule ret = scheduleRepository.save(schedule);
        return ret.equals(schedule);
    }

    @Override
    public boolean endCounseling(String userEmail, int scheduleId) throws Exception {
        Counselor counselor = counselorRepository.findByEmail(userEmail).orElseThrow(
                ()->  new NullPointerException("회원정보가 존재 하지 않습니다")
        );

        Schedule schedule = scheduleRepository.findById(scheduleId);
        if(schedule.getState()!=2 || schedule.getCounselor()!=counselor) return false;
        schedule.setState(3);
        int point = counselor.getPoint()+1000;
        counselor.setPoint(point);
        Counselor counselor1 = counselorRepository.save(counselor);
        Schedule ret = scheduleRepository.save(schedule);
        return (ret.equals(schedule) && counselor1.equals(counselor));
    }

    @Override
    public ScheduleInfoRes getCounseling(int scheduleId) throws Exception {
        Schedule schedule = scheduleRepository.findById(scheduleId);
        ScheduleInfoRes scheduleInfoRes = new ScheduleInfoRes(schedule.getId(), schedule.getClient().getEmail(), schedule.getClient().getName(), schedule.getCounselor().getEmail(), schedule.getCounselor().getName(), schedule.getDateTime(), schedule.getPoint(), schedule.getState(), schedule.isSticker());
        return scheduleInfoRes;
    }

    @Override
    public Page<ScheduleInfoRes> getCounselorApprovedSchedule(String userEmail, Pageable pageRequest) throws Exception {
        Counselor counselor = counselorRepository.findByEmail(userEmail).orElseThrow(
                ()->  new NullPointerException("회원정보가 존재 하지 않습니다")
        );
        Page<ScheduleDto> schedule = scheduleRepository.findAllByCounselor_EmailAndStateEquals(userEmail, 2, pageRequest);
        Page<ScheduleInfoRes> pagingList = schedule.map(
                post -> new ScheduleInfoRes(post.getId(), post.getClient().getEmail(), post.getClient().getName(), post.getCounselor().getEmail(), post.getCounselor().getName(), post.getDateTime(), post.getPoint(), post.getState(), post.isSticker()));
        return pagingList;
    }
    @Override
    public Page<ScheduleInfoRes> getCounselorEndedSchedule(String userEmail, Pageable pageRequest) throws Exception {
        Counselor counselor = counselorRepository.findByEmail(userEmail).orElseThrow(
                ()->  new NullPointerException("회원정보가 존재 하지 않습니다")
        );
        Page<ScheduleDto> schedule = scheduleRepository.findAllByCounselor_EmailAndStateEquals(userEmail, 3, pageRequest);
        Page<ScheduleInfoRes> pagingList = schedule.map(
                post -> new ScheduleInfoRes(post.getId(), post.getClient().getEmail(), post.getClient().getName(), post.getCounselor().getEmail(), post.getCounselor().getName(), post.getDateTime(), post.getPoint(), post.getState(), post.isSticker()));
        return pagingList;
    }

    @Override
    public Page<ScheduleInfoRes> getClientApprovedSchedule(String userEmail, Pageable pageRequest) throws Exception {
        Page<ScheduleDto> schedule = scheduleRepository.findAllByClient_EmailAndStateEquals(userEmail, 2, pageRequest);
        Page<ScheduleInfoRes> pagingList = schedule.map(
                post -> new ScheduleInfoRes(post.getId(), post.getClient().getEmail(), post.getClient().getName(), post.getCounselor().getEmail(), post.getCounselor().getName(), post.getDateTime(), post.getPoint(), post.getState(), post.isSticker()));
        return pagingList;
    }

    @Override
    public Page<ScheduleInfoRes> getClientEndedSchedule(String userEmail, Pageable pageRequest) throws Exception {
        Page<ScheduleDto> schedule = scheduleRepository.findAllByClient_EmailAndStateEquals(userEmail, 3, pageRequest);
        Page<ScheduleInfoRes> pagingList = schedule.map(
                post -> new ScheduleInfoRes(post.getId(), post.getClient().getEmail(), post.getClient().getName(), post.getCounselor().getEmail(), post.getCounselor().getName(), post.getDateTime(), post.getPoint(), post.getState(), post.isSticker()));
        return pagingList;
    }
}
