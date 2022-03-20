package com.ssafy.a605.service;

import com.ssafy.a605.model.entity.Bookmark;
import com.ssafy.a605.model.entity.Client;
import com.ssafy.a605.model.entity.Counselor;
import com.ssafy.a605.repository.BookmarkRepository;
import com.ssafy.a605.repository.ClientRepository;
import com.ssafy.a605.repository.CounselorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookmarkServiceImpl implements BookmarkService{
    final private CounselorRepository counselorRepository;
    final private BookmarkRepository bookmarkRepository;
    final private ClientRepository clientRepository;

    @Override
    public List<Bookmark> getLikedCounselor(String userEmail) throws Exception {
        List<Bookmark> counselor = bookmarkRepository.findAllByClient_Email(userEmail);
        return counselor;
    }

    @Override
    public boolean setLikeCounselor(String userEmail, String counselorEmail) throws Exception {
        Counselor counselor = counselorRepository.findByEmail(counselorEmail).orElseThrow(
                ()->  new NullPointerException("상담사 회원정보가 존재하지 않습니다")
        );
        Client client = clientRepository.findByEmail(userEmail).orElseThrow(
                ()-> new NullPointerException("회원정보가 존재하지 않습니다.")
        );
        List<Bookmark> storedCounselor = getLikedCounselor(userEmail);
        for(Bookmark b : storedCounselor){
            if(b.getCounselor().getEmail().equals(counselorEmail))
                return false;
        }
        Bookmark bookmark = new Bookmark();
        bookmark.setCounselor(counselor);
        bookmark.setClient(client);
        Bookmark lastBookmark = bookmarkRepository.save(bookmark);
        return lastBookmark.equals(bookmark);
    }
}
