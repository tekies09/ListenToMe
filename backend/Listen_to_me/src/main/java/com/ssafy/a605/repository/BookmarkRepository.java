package com.ssafy.a605.repository;

import com.ssafy.a605.model.entity.Bookmark;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark, Integer> {
    List<Bookmark> findAllByClient_Email(String userEmail);
}
