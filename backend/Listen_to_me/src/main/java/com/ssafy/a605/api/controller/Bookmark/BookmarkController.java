package com.ssafy.a605.api.controller.Bookmark;

//import com.ssafy.a605.api.controller.Counselor.CounselorController;
import com.ssafy.a605.model.dto.BookmarkDto;
import com.ssafy.a605.model.dto.ScheduleDto;
import com.ssafy.a605.model.entity.Bookmark;
import com.ssafy.a605.service.BookmarkService;
import com.ssafy.a605.service.JwtServiceImpl;
import com.ssafy.a605.service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/bookmark")
public class BookmarkController {
    public static final Logger logger= LoggerFactory.getLogger(BookmarkController.class);
    private static final String SUCCESS= "success";
    private static final String FAIL= "fail";

    @Autowired
    private JwtServiceImpl jwtService;

    @Autowired
    private BookmarkService bookmarkService;

    // 북마크 리스트
    @GetMapping("/likecounselor")
    public ResponseEntity<List<Bookmark>> getLikedCounselor(HttpServletRequest request) throws Exception{
        List<Bookmark> list = null;
        if (jwtService.isUsable(request.getHeader("access-token"))) {
            list = bookmarkService.getLikedCounselor(jwtService.getUserId());
            return new ResponseEntity<List<Bookmark>>(list, HttpStatus.OK);
        }
        return new ResponseEntity<List<Bookmark>>(list, HttpStatus.BAD_REQUEST);
    }

    // 북마크 추가
    @PostMapping("/setlike/{counselor}")
    public ResponseEntity<String> setLikeCounselor(@PathVariable("counselor") String counselor, HttpServletRequest request) throws Exception {
        if (jwtService.isUsable(request.getHeader("access-token"))) {
            boolean result = bookmarkService.setLikeCounselor(jwtService.getUserId(), counselor);
            if(result)
                return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
            else
                return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
    }
}
