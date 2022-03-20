package com.ssafy.a605.service;

import com.ssafy.a605.model.dto.ClientDto;
import com.ssafy.a605.model.dto.CounselorDto;
import com.ssafy.a605.model.dto.UserDto;
import com.ssafy.a605.model.response.client.ClientInfoRes;
import com.ssafy.a605.model.response.counselor.CounselorInfoRes;

import java.util.Map;

public interface ClientService {
    public Map<String,String> login(Map<String,String> map) throws Exception;
    public UserDto userInfo(String userid) throws Exception;
    public ClientInfoRes clientInfo(String userEmail) throws Exception;
    public CounselorInfoRes counselorInfo(String userEmail) throws Exception;
    public boolean joinClient(ClientDto clientDto) throws Exception;
    public boolean updateClient(ClientDto clientDto) throws Exception;
    public boolean checkId(String userid) throws Exception;
    boolean deleteClient(String userEmail) throws Exception;
}
