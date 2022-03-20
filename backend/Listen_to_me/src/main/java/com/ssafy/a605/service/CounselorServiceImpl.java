package com.ssafy.a605.service;


import com.ssafy.a605.model.dto.CertificateDto;
import com.ssafy.a605.model.dto.ClientDto;
import com.ssafy.a605.model.dto.CounselorDto;
import com.ssafy.a605.model.dto.UserDto;
import com.ssafy.a605.model.entity.Client;
import com.ssafy.a605.model.entity.Counselor;
import com.ssafy.a605.model.entity.User;
import com.ssafy.a605.model.request.counselor.CounselorInfoReq;
import com.ssafy.a605.model.response.counselor.CounselorInfoRes;
import com.ssafy.a605.repository.CareerRepository;
import com.ssafy.a605.repository.CertificateRepository;
import com.ssafy.a605.repository.CounselorRepository;
import com.ssafy.a605.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CounselorServiceImpl implements CounselorService {
    final private CounselorRepository counselorRepository;
    final private CareerRepository CareerRepository;
    final private CertificateRepository certificateRepository;
    final private UserRepository userRepository;

    @Value("${profileImg.path}")
    private String uploadFolder;

    @Override
    public Map<String,String> login(Map<String,String> map) throws Exception {
        User user = counselorRepository.findByEmail(map.get("email")).orElseThrow(
                ()->  new NullPointerException("회원정보가 존재 하지 않습니다")
        );;
        if (!map.get("password").equals(user.getPassword()))
            return null;
        return map;
    }

    @Override
    public CounselorInfoRes counselorInfo(String userEmail) throws Exception {
        Counselor counselor = counselorRepository.findByEmail(userEmail).orElseThrow(
                ()->  new NullPointerException("회원정보가 존재 하지 않습니다")
        );
        CounselorInfoRes counselorInfoRes = new CounselorInfoRes(counselor.getEmail(), counselor.getName(), counselor.getPhoneNumber(), counselor.getGender(), counselor.getBirth(), counselor.getPoint(), counselor.getPhoto(), counselor.getShortGreeting(), counselor.getGreeting(), counselor.getDegree());
        return counselorInfoRes;
    }

    @Override
    public Counselor getCounselorInfo(String userEmail) throws Exception {
        Counselor counselor = counselorRepository.findByEmail(userEmail).orElseThrow(
                ()->  new NullPointerException("회원정보가 존재 하지 않습니다")
        );
        return counselor;
    }

    @Override
    @Transactional
    public boolean joinCounselor(CounselorDto counselorDto) throws Exception {

        Counselor counselor = new Counselor();
        counselor.update(counselorDto);
        Counselor ret = userRepository.save(counselor);
        return ret.equals(counselor);
    }

    @Override
    public boolean joinUser(CounselorInfoReq counselorInfoReq) throws Exception {
        Counselor counselor = new Counselor();
        CounselorDto counselorDto = new CounselorDto(counselorInfoReq.getEmail(), counselorInfoReq.getPassword(), counselorInfoReq.getName(), counselorInfoReq.getGender(), counselorInfoReq.getBirth(), 0, counselorInfoReq.getPhoneNumber(), "", counselorInfoReq.getShortGreeting(),  counselorInfoReq.getGreeting(), counselorInfoReq.getDegree());
        counselor.update(counselorDto);
        Counselor ret = userRepository.save(counselor);
        updateImage(counselorInfoReq.getPhoto(), counselorInfoReq.getEmail());
        return ret.equals(counselor);
    }

    @Override
    @Transactional
    public boolean updateCounselor(CounselorDto counselorDto) throws Exception {
        String userEmail = counselorDto.getEmail();
        Counselor client = counselorRepository.findByEmail(userEmail).orElseThrow(
                ()->  new NullPointerException("회원정보가 존재 하지 않습니다")
        );
        client.update(counselorDto);
        return true;
    }

    @Override
    public boolean updateShortGreeting(String shortGreeting, String userEmail) throws Exception {
        Counselor counselor = counselorRepository.findByEmail(userEmail).orElseThrow(
                ()->  new NullPointerException("회원정보가 존재 하지 않습니다")
        );
        counselor.setShortGreeting(shortGreeting);
        Counselor c = counselorRepository.save(counselor);
        return c.equals(counselor);
    }

    @Override
    public boolean updateGreeting(String greeting, String userEmail) throws Exception {
        Counselor counselor = counselorRepository.findByEmail(userEmail).orElseThrow(
                ()->  new NullPointerException("회원정보가 존재 하지 않습니다")
        );
        counselor.setGreeting(greeting);
        Counselor c = counselorRepository.save(counselor);
        return c.equals(counselor);
    }

    @Override
    public boolean checkId(String userEmail) throws Exception {
        System.out.println(userEmail);
        Optional<User> user = userRepository.findByEmail(userEmail);

        if(user.isPresent()){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateImage(MultipartFile multipartFile, String userEmail) throws Exception {
        Counselor counselor = counselorRepository.findByEmail(userEmail).orElseThrow(
                ()->  new NullPointerException("회원정보가 존재 하지 않습니다")
        );
        String imageName = userEmail + "_" + multipartFile.getOriginalFilename();
        Path imagePath = Paths.get(uploadFolder + imageName);
        if(multipartFile.getSize()!=0){
            try {
                if(counselor.getPhoto() != null) {
                    File file = new File(uploadFolder + counselor.getPhoto());
                    file.delete();
                }
                Files.write(imagePath, multipartFile.getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            }
            counselor.setPhoto(imageName);
        }
        Counselor c = counselorRepository.save(counselor);
        return c.equals(counselor);
    }

    @Override
    @Transactional
    public boolean deleteCounselor(String userEmail) throws Exception {
        userRepository.deleteByEmail(
                userRepository.findByEmail(userEmail).orElseThrow(
                        () -> new NullPointerException("회원정보가 존재 하지 않습니다")
                ).getEmail()
        );
        return true;
    }

    @Override
    public List<CertificateDto> getCertificates(String userEmail) throws Exception {
        return null;
    }
}
