package com.ssafy.a605.service;

import com.ssafy.a605.model.dto.ClientDto;
import com.ssafy.a605.model.dto.CounselorDto;
import com.ssafy.a605.model.dto.UserDto;
import com.ssafy.a605.model.entity.Client;
import com.ssafy.a605.model.entity.Counselor;
import com.ssafy.a605.model.entity.User;
import com.ssafy.a605.model.response.client.ClientInfoRes;
import com.ssafy.a605.model.response.counselor.CounselorInfoRes;
import com.ssafy.a605.repository.ClientRepository;
import com.ssafy.a605.repository.CounselorRepository;
import com.ssafy.a605.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClientServiceImpl implements ClientService {
    final private CounselorRepository counselorRepository;
    final private ClientRepository clientRepository;
    final private UserRepository userRepository;

    @Override
    public Map<String,String> login(Map<String,String> map) throws Exception {
        User user = clientRepository.findByEmail(map.get("email")).orElseThrow(
                ()->  new NullPointerException("회원정보가 존재 하지 않습니다")
        );;
        if (!map.get("password").equals(user.getPassword()))
            return null;
        return map;
    }

    @Override
    public UserDto userInfo(String userEmail) throws Exception {
        User user = userRepository.findByEmail(userEmail).orElseThrow(
                ()->  new NullPointerException("회원정보가 존재 하지 않습니다")
        );
        UserDto userDto = new UserDto(
                user.getEmail(),user.getPassword(),user.getName(),user.getPhoneNumber(),user.getGender(),user.getBirth(),user.getPoint()
        );
        return userDto;
    }

    @Override
    public ClientInfoRes clientInfo(String userEmail) throws Exception {
        Client client = clientRepository.findByEmail(userEmail).orElseThrow(
                ()->  new NullPointerException("회원정보가 존재 하지 않습니다")
        );
        ClientInfoRes clientInfoRes = new ClientInfoRes( client.getEmail(), client.getName(), client.getPhoneNumber(), client.getGender(), client.getBirth(), client.getPoint(), client.getNickname());
        return clientInfoRes;
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
    @Transactional
    public boolean joinClient(ClientDto clientDto) throws Exception {
        Client client = new Client();
        client.update(clientDto);
        Client ret = userRepository.save(client);
        return ret.equals(client);
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
    @Transactional
    public boolean updateClient(ClientDto clientDto) throws Exception {
        String userEmail = clientDto.getEmail();
        Client client = clientRepository.findByEmail(userEmail).orElseThrow(
                ()->  new NullPointerException("회원정보가 존재 하지 않습니다")
        );
        client.update(clientDto);
        return true;
    }

    @Override
    @Transactional
    public boolean deleteClient(String userEmail) throws Exception {
        userRepository.deleteByEmail(
                userRepository.findByEmail(userEmail).orElseThrow(
                        () -> new NullPointerException("회원정보가 존재 하지 않습니다")
                ).getEmail()
        );
        return true;
    }
}
