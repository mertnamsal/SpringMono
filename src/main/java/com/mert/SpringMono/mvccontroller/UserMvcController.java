package com.mert.SpringMono.mvccontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static com.mert.SpringMono.constant.EndPoints.*;
@Controller
@RequestMapping(MVCUSER)
public class UserMvcController {


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

}
