package com.ssafy.a605.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StatisticsServiceImpl implements StatisticsService {
    final private EntityManager em;

    @Override
    public List<Long> getGender(String userEmail) throws Exception {
        Query query = em.createQuery("select count(S.client.gender) from Schedule S WHERE S.counselor.email = :userEmail GROUP BY S.client.gender").setParameter("userEmail", userEmail);
        List queryList = query.getResultList();
        return queryList;
    }

    @Override
    public long[] getAgeValue(String userEmail) throws Exception {
        Query query = em.createQuery("select substring(S.client.birth, 1,4), count(S.client) as b from Schedule S where S.counselor.email = :userEmail group by substring(S.client.birth, 1,4)").setParameter("userEmail", userEmail);
        List queryList = query.getResultList();
        long[] answer = new long[6];

        for(Object o : queryList){
            Object[] objects = (Object[]) o;
            int birth = Integer.parseInt((String)objects[0]);
            int year = LocalDate.now().getYear();
            if(year-birth <20) answer[0]+= (Long) objects[1];
            else if (year-birth <30) answer[1]+= (Long) objects[1];
            else if (year-birth <40) answer[2]+= (Long) objects[1];
            else if (year-birth <50) answer[3]+= (Long) objects[1];
            else if (year-birth <60) answer[4]+= (Long) objects[1];
            else answer[5]+= (Long) objects[1];
        }
        return answer;
    }

    @Override
    public long[] getMonthCount(String userEmail) throws Exception {
        Query query = em.createQuery("select substring(dateTime, 4,5), count(dateTime) from Schedule where counselor.email = :userEmail group by substring(dateTime, 4,5)").setParameter("userEmail", userEmail);
        List queryList = query.getResultList();
        String tmp = ""; int tmpNum = 0;
        long[] answer = new long[12];

        for(Object o : queryList){
            Object[] objects = (Object[]) o;
            tmp = (String)objects[0];
            tmp = tmp.substring(2,4);
            System.out.println(tmp);
            if(tmp.charAt(0)=='0') tmpNum = tmp.charAt(1)-48;
            else tmpNum = Integer.parseInt(tmp);
            System.out.println(tmpNum + " tmp");
            System.out.println(objects[1]);
            System.out.println((long)objects[1]);
            answer[tmpNum-1] += (long)objects[1];
        }
        return answer;
    }
}