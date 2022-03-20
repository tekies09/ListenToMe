package com.ssafy.a605.service;

import com.ssafy.a605.model.entity.Client;
import com.ssafy.a605.model.entity.Counselor;
import com.ssafy.a605.model.entity.Memo;
import com.ssafy.a605.model.entity.Schedule;
import com.ssafy.a605.model.response.memo.MemoRes;
import com.ssafy.a605.repository.MemoRepository;
import com.ssafy.a605.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MemoServiceImpl implements MemoService {
    final private MemoRepository memoRepository;
    final private ScheduleRepository scheduleRepository;

    @Override
    public boolean writeMemo(int id, String title, String content, String userEmail) throws Exception {
        Memo memo = new Memo();
        if(memoRepository.existsMemoByUserEmailEqualsAndSchedule_Id(userEmail, id)){
            memo = memoRepository.getByUserEmailEqualsAndSchedule_Id(userEmail, id);
        }
        else{
            Schedule schedule = scheduleRepository.findById(id);
            memo.setSchedule(schedule);
            memo.setUserEmail(userEmail);
        }
        memo.setTitle(title);
        memo.setContent(content);
        Memo m = memoRepository.save(memo);
        return m.equals(memo);
    }

    @Override
    public boolean checkUser(int id, String userEmail) throws Exception {
        Schedule schedule = scheduleRepository.findById(id);

        if(schedule.getClient().getEmail().equals(userEmail)) return true;
        else if(schedule.getCounselor().getEmail().equals(userEmail)) return true;
        else return false;
    }

    @Override
    public MemoRes getMemo(int id, String userEmail) throws Exception {
        Memo memo = memoRepository.findById(id);
        if(memo.getUserEmail().equals(userEmail)){
            System.out.println("일치");
            MemoRes memoRes = new MemoRes(memo.getId(), memo.getSchedule().getDateTime(), memo.getTitle(), memo.getContent());
            return memoRes;
        }
        MemoRes memoRes = new MemoRes();
        return memoRes;
    }

    @Override
    public MemoRes getMemoByCounseling(int id, String userEmail) throws Exception {
        Memo memo = memoRepository.getByUserEmailEqualsAndSchedule_Id(userEmail, id);
        MemoRes memoRes = new MemoRes(memo.getId(), memo.getSchedule().getDateTime(), memo.getTitle(), memo.getContent());
        return memoRes;
    }

    @Override
    public List<MemoRes> getMemoList(int id, String userEmail) throws Exception {
        Schedule schedule = scheduleRepository.findById(id);
        List<Schedule> listSchedule = scheduleRepository.findAllByCounselor_EmailAndClient_Email(schedule.getCounselor().getEmail(), schedule.getClient().getEmail());
        List<Memo> memoList = new ArrayList<>();
        for(Schedule s : listSchedule){
            List<Memo> tmp = memoRepository.findMemosBySchedule_Id(s.getId());
            memoList.addAll(tmp);
        }
        List<MemoRes> list = new ArrayList<>();
        for(Memo m : memoList){
            if(!m.getUserEmail().equals(userEmail)) continue;
            list.add(new MemoRes(m.getId(), m.getSchedule().getDateTime(), m.getTitle(), m.getContent()));
        }
        return list;
    }
}
