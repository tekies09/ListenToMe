package com.ssafy.a605.api.controller.counselor;

import com.ssafy.a605.model.entity.Category;
import com.ssafy.a605.model.entity.CounselorCategory;
import com.ssafy.a605.service.CategoryService;
import com.ssafy.a605.service.JwtServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/category-api")
public class CategoryController {
    public static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private JwtServiceImpl jwtService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<List<Category>> getCategory() throws Exception {
        return new ResponseEntity<List<Category>>(categoryService.getAllCategory(), HttpStatus.OK);
    }

    @GetMapping("/counselor")
    public ResponseEntity<List<CounselorCategory>> getCounselorCategory() throws Exception{
        return new ResponseEntity<List<CounselorCategory>>(categoryService.getCounselorCategory(jwtService.getUserId()), HttpStatus.OK);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<String> setCounselorCategory(@PathVariable("categoryId") int categoryId, HttpServletRequest request) throws Exception{
        if (jwtService.isUsable(request.getHeader("access-token"))) {
            if(categoryService.checkCounselorCategory(categoryId, jwtService.getUserId())){
                return new ResponseEntity<String>("already exists", HttpStatus.BAD_REQUEST);
            }
            if(categoryService.setCounselorCategory(categoryId, jwtService.getUserId())){
                return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
    }
}
