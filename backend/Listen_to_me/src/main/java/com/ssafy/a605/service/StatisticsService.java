package com.ssafy.a605.service;

import java.util.List;

public interface StatisticsService {
    List<Long> getGender(String userEmail) throws Exception;
    long[] getAgeValue(String userEmail) throws Exception;
    long[] getMonthCount(String userEmail) throws Exception;
}