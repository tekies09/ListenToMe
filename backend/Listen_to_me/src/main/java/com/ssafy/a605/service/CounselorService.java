package com.ssafy.a605.service;

import com.ssafy.a605.model.dto.CertificateDto;
import com.ssafy.a605.model.dto.ClientDto;
import com.ssafy.a605.model.dto.CounselorDto;
import com.ssafy.a605.model.dto.UserDto;
import com.ssafy.a605.model.entity.Counselor;
import com.ssafy.a605.model.request.counselor.CounselorInfoReq;
import com.ssafy.a605.model.response.counselor.CounselorInfoRes;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface CounselorService {
    public Map<String,String> login(Map<String,String> map) throws Exception;
    public CounselorInfoRes counselorInfo(String userEmail) throws Exception;
    public Counselor getCounselorInfo(String userEmail) throws Exception;
    public boolean joinCounselor(CounselorDto counselorDto) throws Exception;
    public boolean joinUser(CounselorInfoReq counselorInfoReq) throws Exception;
    public boolean updateCounselor(CounselorDto counselorDto) throws Exception;
    public boolean updateShortGreeting(String shortGreeting, String userEmail) throws Exception;
    public boolean updateGreeting(String greeting, String userEmail) throws Exception;
    public boolean checkId(String userid) throws Exception;
    boolean updateImage(MultipartFile multipartFile, String userEmail) throws Exception;
    boolean deleteCounselor(String userEmail) throws Exception;
    List<CertificateDto> getCertificates (String userEmail) throws Exception;
}
