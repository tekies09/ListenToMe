package com.ssafy.a605.api.controller.Review;

import com.ssafy.a605.api.controller.Bookmark.BookmarkController;
import com.ssafy.a605.model.dto.ReviewDto;
import com.ssafy.a605.model.entity.Review;
import com.ssafy.a605.model.response.review.ReviewRes;
import com.ssafy.a605.service.JwtServiceImpl;
import com.ssafy.a605.service.ReviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/review-api")
public class ReviewController {
    public static final Logger logger= LoggerFactory.getLogger(ReviewController.class);
    private static final String SUCCESS= "success";
    private static final String FAIL= "fail";

    @Autowired
    private JwtServiceImpl jwtService;

    @Autowired
    private ReviewService reviewService;

    // 리뷰 리스트 - 비로그인시 가능
    @GetMapping("/list/{counselor}")
    public ResponseEntity<Page<ReviewRes>> getListReview(@PathVariable("counselor") String counselor, @PageableDefault(size=5, sort = "id") Pageable pageRequest) throws Exception{
        return new ResponseEntity<Page<ReviewRes>>(reviewService.getListReview(counselor, pageRequest), HttpStatus.OK);
    }

    // 리뷰 작성
    @PostMapping("/save/{scheduleId}")
    public ResponseEntity<String> setReview(@PathVariable("scheduleId") int scheduleId, @RequestBody ReviewDto reviewDto, HttpServletRequest request) throws Exception{
        if(jwtService.isUsable(request.getHeader("access-token"))){
            if(!reviewService.checkClient(jwtService.getUserId(), scheduleId))
                return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
            if(reviewService.writeReview(scheduleId, reviewDto))
                return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
    }

    // 리뷰 수정을 위해 리뷰 불러오기
    @GetMapping("/detail/{id}")
    public ResponseEntity<Review> getReview(@PathVariable("id") int reviewId) throws Exception{
        return new ResponseEntity<Review>(reviewService.getReview(reviewId), HttpStatus.OK);
    }

    // 리뷰 수정
    @PatchMapping("/update")
    public ResponseEntity<String> updateReview(@RequestBody ReviewDto reviewDto, HttpServletRequest request) throws Exception {
        if(reviewService.updateReview(reviewDto)){
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
    }

    // 리뷰 삭제
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteReview(@PathVariable("id") int reviewId) throws Exception{
        if(reviewService.deleteReview(reviewId)){
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
    }

}
