package com.ssafy.a605.model.entity;


import com.ssafy.a605.model.dto.ClientDto;
import com.ssafy.a605.model.dto.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor //기본생성자 생성
@Entity
@DiscriminatorValue("Client")
@Getter
@Setter
public class Client extends User   {

    private String nickname;
    public Client( String email, String password, String name,String nickname, char gender, String birth, int point, String phoneNumber) {
        super(email, password, name, phoneNumber, gender, birth, point);
        this.nickname = nickname;
    }

    public void update(ClientDto clientDto){
        this.setEmail(clientDto.getEmail());
        this.setPassword(clientDto.getPassword());
        this.setName(clientDto.getName());
        this.setPhoneNumber(clientDto.getPhoneNumber());
        this.setGender(clientDto.getGender());
        this.setBirth(clientDto.getBirth());
        this.setPoint(clientDto.getPoint());
        this.nickname = clientDto.getNickname();
    }


}
