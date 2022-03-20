package com.ssafy.a605.api.controller.counselor;

import com.ssafy.a605.service.CertificateService;
import com.ssafy.a605.service.JwtServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/certificate-api")
public class CertificateController {
    public static final Logger logger = LoggerFactory.getLogger(CertificateController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private JwtServiceImpl jwtService;

    @Autowired
    private CertificateService certificateService;

    @GetMapping("/{certificate}")
    public ResponseEntity<String> setCertificate(@PathVariable("certificate") String certificate, HttpServletRequest request) throws Exception{
        if (jwtService.isUsable(request.getHeader("access-token"))) {
            if(certificateService.setCertificate(jwtService.getUserId(), certificate)){
                return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
            }
        }
        return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
    }
}
