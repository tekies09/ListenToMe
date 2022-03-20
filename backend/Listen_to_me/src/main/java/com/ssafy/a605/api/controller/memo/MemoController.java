package com.ssafy.a605.api.controller.memo;

import com.ssafy.a605.model.response.memo.MemoRes;
import com.ssafy.a605.service.JwtServiceImpl;
import com.ssafy.a605.service.MemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/memo-api")
public class MemoController {
    public static final Logger logger= LoggerFactory.getLogger(MemoController.class);
    private static final String SUCCESS= "success";
    private static final String FAIL= "fail";

    @Autowired
    private JwtServiceImpl jwtService;

    @Autowired
    private MemoService memoService;

    @PostMapping("/write")
    public ResponseEntity<String> writeMemo(@RequestBody Map<String,String> param, HttpServletRequest request) throws Exception {
        if (jwtService.isUsable(request.getHeader("access-token"))) {
            int id = Integer.parseInt(param.get("id"));
            String title = param.get("title");
            String content = param.get("content");
            System.out.println(title);
            System.out.println(content);
            if(memoService.writeMemo(id, title, content, jwtService.getUserId())){
                return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{memoId}")
    public ResponseEntity<MemoRes> getMemoById(@PathVariable("memoId") int memoId, HttpServletRequest request) throws Exception {
        if (jwtService.isUsable(request.getHeader("access-token"))) {
            return new ResponseEntity<MemoRes>(memoService.getMemo(memoId, jwtService.getUserId()), HttpStatus.OK);
        }
        return new ResponseEntity<MemoRes>(new MemoRes(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/counseling/{scheduleId}")
    public ResponseEntity<MemoRes> getMemoByCounseling(@PathVariable("scheduleId") int scheduleId, HttpServletRequest request) throws Exception {
        if (jwtService.isUsable(request.getHeader("access-token"))) {
            return new ResponseEntity<MemoRes>(memoService.getMemoByCounseling(scheduleId, jwtService.getUserId()), HttpStatus.OK);
        }
        return new ResponseEntity<MemoRes>(new MemoRes(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/list/{scheduleId}")
    public ResponseEntity<List<MemoRes>> getMemoList(@PathVariable("scheduleId") int scheduleId, HttpServletRequest request) throws Exception{
        if (jwtService.isUsable(request.getHeader("access-token"))) {
            if(!memoService.checkUser(scheduleId, jwtService.getUserId())) return new ResponseEntity<List<MemoRes>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
            List<MemoRes> list = memoService.getMemoList(scheduleId, jwtService.getUserId());
            return new ResponseEntity<List<MemoRes>>(list, HttpStatus.OK);
        }
        return new ResponseEntity<List<MemoRes>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
}
