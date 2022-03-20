package com.ssafy.a605.repository;

import com.ssafy.a605.model.dto.ScheduleDto;
import com.ssafy.a605.model.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    Page<ScheduleDto> findAllByCounselor_EmailAndStateEquals(String userEmail, int state, Pageable pageRequest);
    Page<ScheduleDto> findAllByClient_EmailAndStateEquals(String userEmail, int state, Pageable pageRequest);
    boolean existsScheduleByDateTimeEqualsAndCounselor_Email(LocalDateTime dateTime, String userEmail);
    List<ScheduleDto> findAllByCounselor_EmailAndDateTimeBetween(String userEmail, LocalDateTime start, LocalDateTime end);
    List<Schedule> findAllByCounselor_EmailAndClient_Email(String counselor, String client);
    Schedule findById(int id);
}
