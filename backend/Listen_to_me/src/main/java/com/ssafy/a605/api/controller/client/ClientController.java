package com.ssafy.a605.api.controller.client;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ssafy.a605.model.dto.ClientDto;
import com.ssafy.a605.model.response.client.ClientInfoRes;
import com.ssafy.a605.service.JwtServiceImpl;
import com.ssafy.a605.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@CrossOrigin
@RestController
@RequestMapping("/client-api")
@Api("내담자회원관리")
public class ClientController {

    public static final Logger logger = LoggerFactory.getLogger(ClientController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private JwtServiceImpl jwtService;

    @Autowired
    private ClientService userService;

    @ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) Map<String,String> map) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            Map<String,String> loginMap = userService.login(map);
            if (loginMap != null) {
                String token = jwtService.create("userid", loginMap.get("email"), "access-token");// key, data, subject
                logger.debug("로그인 토큰정보 : {}", token);
                resultMap.put("access-token", token);
                resultMap.put("message", SUCCESS);
                status = HttpStatus.ACCEPTED;
            } else {
                resultMap.put("message", FAIL);
                status = HttpStatus.ACCEPTED;
            }
        } catch (Exception e) {
            logger.error("로그인 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
    @GetMapping("/user")
    public ResponseEntity<Map<String, Object>> getInfo(HttpServletRequest request) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        if (jwtService.isUsable(request.getHeader("access-token"))) {
            logger.info("사용 가능한 토큰!!!");
            try {
//				로그인 사용자 정보.
                ClientInfoRes clientInfoRes = userService.clientInfo(jwtService.getUserId());
                resultMap.put("userInfo", clientInfoRes);
                resultMap.put("message", SUCCESS);
                status = HttpStatus.ACCEPTED;
            } catch (Exception e) {
                logger.error("정보조회 실패 : {}", e);
                resultMap.put("message", e.getMessage());
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } else {
            logger.error("사용 불가능 토큰!!!");
            resultMap.put("message", FAIL);
            status = HttpStatus.ACCEPTED;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    @PostMapping("/user")
    public ResponseEntity<String> joinUser(
            @RequestBody @ApiParam(value = "회원 가입 정보", required = true) ClientDto clientDto) throws Exception {
        logger.info("joinUser - 호출");
        if (userService.joinClient(clientDto)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{userEmail}")
    public ResponseEntity<String> checkId(
            @PathVariable("userEmail") @ApiParam(value = "중복 아이디 체크", required = true) String userEmail) throws Exception {
        logger.info("checkId - 호출");
        if (userService.checkId(userEmail)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/user")
    public ResponseEntity<String> modifyUser(
            @RequestBody @ApiParam(value = "수정할 회원정보.", required = true) ClientDto clientDto) throws Exception {
        logger.info("modify- 호출");

        if (userService.updateClient(clientDto)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.OK);
    }
//
    @DeleteMapping("/user/{userEmail}")
    public ResponseEntity<String> deleteUser(
            @PathVariable("userEmail") @ApiParam(value = "회원탈퇴", required = true) String userEmail) throws Exception {
        logger.info("delete - 호출");
        if (userService.deleteClient(userEmail)) {
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
    }

}
