package com.ssafy.a605.service;

import com.ssafy.a605.model.dto.BookmarkDto;
import com.ssafy.a605.model.entity.Bookmark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookmarkService {
    public List<Bookmark> getLikedCounselor(String userEmail) throws Exception;
    public boolean setLikeCounselor(String userEmail, String counselorEmail) throws Exception;
}
