package com.ssafy.a605.service;

import com.ssafy.a605.model.dto.ReviewDto;
import com.ssafy.a605.model.entity.Counselor;
import com.ssafy.a605.model.entity.CounselorCategory;
import com.ssafy.a605.model.entity.Review;
import com.ssafy.a605.model.entity.Schedule;
import com.ssafy.a605.model.response.counselor.CounselorListRes;
import com.ssafy.a605.model.response.review.ReviewRes;
import com.ssafy.a605.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService{
    final private ScheduleRepository scheduleRepository;
    final private ReviewRepository reviewRepository;
    final private CounselorRepository counselorRepository;
    final private CounselorCategoryRepository categoryRepository;
    final private EntityManager em;

    @Override
    public boolean writeReview(int scheduleId, ReviewDto reviewDto)throws Exception {
        Schedule schedule = scheduleRepository.findById(scheduleId);
        Review review = new Review();
        review.setClient(schedule.getClient());
        review.setCounselor(schedule.getCounselor());
        review.setStartScore(reviewDto.getStartScore());
        review.setContent(reviewDto.getContent());
        Review ret = reviewRepository.save(review);
        return ret.equals(review);
    }
    @Override
    public Page<ReviewRes> getListReview(String counselor, Pageable pageRequest) throws Exception{
        Page<Review> reviews = reviewRepository.findReviewsByCounselor_Email(counselor, pageRequest);
        Page<ReviewRes> reviewList = reviews.map(
                post -> new ReviewRes(post.getId(), post.getClient().getNickname(), post.getStartScore(), post.getContent())
        );
        return reviewList;
    }

    @Override
    public List<CounselorListRes> getCounselorList() throws Exception {
        Query query = em.createQuery("select R.counselor, avg(R.startScore) from Review R group by R.counselor order by avg(R.startScore) desc");
        List queryList = query.getResultList();
        List<CounselorListRes> list = new ArrayList<>();
        List<String> counselor = new ArrayList<>();

        for(Object o : queryList){
            Object[] objects = (Object[]) o;

            Counselor c = (Counselor) objects[0];
            CounselorListRes counselorListRes = new CounselorListRes(c.getEmail(), c.getName(), c.getPhoto(), (double)objects[1], getCategory(c.getEmail()));
            list.add(counselorListRes);
            counselor.add(c.getEmail());
        }

        List<Counselor> counselors = counselorRepository.findAll();
        for(Counselor c: counselors){
            if(!counselor.contains(c.getEmail())){
                list.add(new CounselorListRes(c.getEmail(), c.getName(), c.getPhoto(), 0, getCategory(c.getEmail())));
            }
        }
        return list;
    }

    @Override
    public List<CounselorListRes> getPartialCounselorList(List<CounselorListRes> list, int page) throws Exception {
        List<CounselorListRes> result = new ArrayList<>();
        if(page == list.size()/10) result = list.subList(page*10, list.size());
        else result = list.subList(page*10, (page*10)+10);

        return result;
    }

    @Override
    public List<CounselorListRes> getTodayCounselorList() throws Exception {
        Query query = em.createQuery("select R.counselor, avg(R.startScore) from Review R group by R.counselor order by avg(R.startScore) desc").setMaxResults(5);
        List queryList = query.getResultList();
        List<CounselorListRes> list = new ArrayList<>();
        List<String> counselor = new ArrayList<>();

        for(Object o : queryList){
            Object[] objects = (Object[]) o;

            Counselor c = (Counselor) objects[0];
            CounselorListRes counselorListRes = new CounselorListRes(c.getEmail(), c.getName(), c.getPhoto(), (double)objects[1], getCategory(c.getEmail()));
            list.add(counselorListRes);
            counselor.add(c.getEmail());
        }

        List<Counselor> counselors = counselorRepository.findAll();
        for(Counselor c: counselors){
            if(list.size()==5) break;
            if(!counselor.contains(c.getEmail())){
                list.add(new CounselorListRes(c.getEmail(), c.getName(), c.getPhoto(), 0, getCategory(c.getEmail())));
            }
        }
        return list;
    }

    @Override
    public boolean updateReview(ReviewDto reviewDto) {
        return false;
    }

    @Override
    public boolean deleteReview(int reviewId) {
        return false;
    }

    @Override
    public Review getReview(int reviewId) {
        return null;
    }

    @Override
    public boolean checkClient(String clientEmail, int scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId);
        if(schedule.getClient().getEmail().equals(clientEmail)) return true;
        return false;
    }

    private List<String> getCategory(String userEmail) {
        List<CounselorCategory> list = categoryRepository.findAllByCounselor_Email(userEmail);
        List<String> result = new ArrayList<>();
        for(CounselorCategory c: list){
            result.add(c.getCategory().getCategory());
        }
        return result;
    }
}
