package com.mert.SpringMono.mapper;

import com.mert.SpringMono.dto.request.RegisterRequestDto;
import com.mert.SpringMono.dto.response.UserControllerFindAllResponseDto;
import com.mert.SpringMono.repository.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * Class->Dto ya da Dto-> class tür dönüşümleri için ilk olarak ilgili interface üzerinden
 * @Mapper anotasyonumuzu ekliyoruz.Burada dikkat edilmesi gerek iki nokta var bunlardan
 * 1. mapper spring framework un uygulama çatısına uygun olarak entegre olsun diye component
 * model olarak spring i ekliyor.
 * 2. olarak ister istemez tür dönüşümlerinde olan adları bire bir uyuşmamaktadır.Bu nedenle
 * eşleşmeyen olanların nasıl davaranacağının belilenmesi gereklidir. Bunu sağlamak için hedef
 * politikalarının seçilmesine ihtiyaç vardır.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IUserMapper {

    IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);


    User toUser(final RegisterRequestDto dto);  //dto dan kullanıcıya
    //    @Mapping(source = "username",target = "kullaniciadi")
    //    @Mappings(@Mapping(),@Mapping())
    UserControllerFindAllResponseDto userControllerFindAllResponseDtoFromUser(final User user); //kullanıcıdan dto ya
}
