package com.mert.SpringMono.service;

import com.mert.SpringMono.dto.request.RegisterRequestDto;
import com.mert.SpringMono.dto.response.UserControllerFindAllResponseDto;
import com.mert.SpringMono.mapper.IUserMapper;
import com.mert.SpringMono.repository.IUserRepository;
import com.mert.SpringMono.repository.entity.User;
import com.mert.SpringMono.utiliy.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserService extends ServiceManager<User,Long> {
    private final IUserRepository repository;

    public UserService(IUserRepository repository){
        super(repository);
        this.repository=repository;
    }
    public Boolean register(RegisterRequestDto dto){
        User user=User.builder()
                .email(dto.getEmail())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
        save(user); //ServiceManagerdaki save i kullanmış oluyoruz.
        return true;
    }
    public Boolean registerMapper(RegisterRequestDto dto){
        save(IUserMapper.INSTANCE.toUser(dto));
        return true;
    }

    public List<UserControllerFindAllResponseDto> findAllResponseDtos(){
        /**
         * boş list oluşturdk
         */
        List<UserControllerFindAllResponseDto> result = new ArrayList<>();
        /**
         * tüm kullanıcı listesini çektik
         */
        findAll().forEach(x->{
            /**
             * Dto nesnesini oluşturmak için her kullanıcının bilgilerini alarak builder ile
             * dto nesnesi yarattık ve bu nesneyi listemize ekledik
             */

//            result.add(UserControllerFindAllResponseDto.builder()
//                    .avatar(x.getAvatar())
//                    .username(x.getUsername())
//                    .id(x.getId())
//                    .build());
            result.add(IUserMapper.INSTANCE.userControllerFindAllResponseDtoFromUser(x));
        });

        return result;
    }
    public Boolean existsUserByUsername(String username){
        return repository.existsUserByUsername(username);
    }
}
