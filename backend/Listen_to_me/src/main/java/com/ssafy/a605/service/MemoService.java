package com.ssafy.a605.service;

import com.ssafy.a605.model.dto.MemoDto;
import com.ssafy.a605.model.entity.Memo;
import com.ssafy.a605.model.response.memo.MemoRes;

import java.util.List;

public interface MemoService {
    public boolean writeMemo(int id, String title, String content, String userEmail) throws Exception;
    public boolean checkUser(int id, String userEmail) throws Exception;
    public MemoRes getMemo(int id, String userEmail) throws Exception;
    public MemoRes getMemoByCounseling(int id, String userEmail) throws Exception;
    public List<MemoRes> getMemoList(int id, String userEmail) throws Exception;
}
