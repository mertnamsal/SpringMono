package com.mert.SpringMono.mvccontroller;

import com.mert.SpringMono.dto.request.RegisterRequestDto;
import com.mert.SpringMono.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.mert.SpringMono.constant.EndPoints.*;
@Controller
@RequestMapping(MVCUSER)
@RequiredArgsConstructor
public class UserMvcController {

    private final UserService userService;
    /**
     * http://www.localhost:8080/mvc/user/login
     * MVC sayfalarına gelen isteklerde kullanıcıya bir HTML sayfası dönmeniz gerekmektedir.
     * Bu nedenle MVC yapısında tema ile birleştirilen model kullanıcıya ModelAndView üzerinden
     * dönülür.
     * ModelAndView -> bir html sayfası ve model talep eder(model zorunlu değildir)
     * bu bilgiler ile bir html sayfası oluşturarak kullanıcıya dönüş yapar.
     */

    @GetMapping(LOGIN)
    public ModelAndView login(){
        ModelAndView model = new ModelAndView();
        /**
         * templates içinde bulunan html sayfasının adıdır.
         */
        model.setViewName("login");
        model.addObject("title","Giriş Sayfası");
        return model;
    }
    @GetMapping(REGISTER)
    public ModelAndView register(){
        ModelAndView model = new ModelAndView();
        model.setViewName("register");
        model.addObject("error","");
        return model;
    }
    @PostMapping(REGISTER)
    public ModelAndView register(RegisterRequestDto dto){
        ModelAndView model = new ModelAndView();
        /**
         * Eğer kullanıcı mevcut ise uyarı bildirimi yap
         */
        if(userService.existsUserByUsername(dto.getUsername())){
            model.setViewName("register");
            model.addObject("error",dto.getUsername()+" kullanıcı adı daha önce başkası tarafından alınmıştır.");
        }else{
            userService.register(dto);
            return new ModelAndView("redirect:login");
        }
        return model;
    }

}
