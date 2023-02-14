package com.mert.SpringMono.controller;

import com.mert.SpringMono.repository.IMusteriRepository;
import com.mert.SpringMono.repository.entity.Musteri;
import com.mert.SpringMono.service.MusteriService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
/*
Uygulama ayağa kalktığı andan itibaren bir ip ve port üzerinde yayın yapar.
localhost:80 -> localhost
Uygulamanızın alt kırılımlarına yön vermek ve belli Class lara yönlerdirme yapmak için
Mapping yaparız.Bunun için @RequestMapping anotasyonu kullanılır.
 */
@RequestMapping("/musteri")
public class MusteriController {

    @Autowired
    MusteriService musteriService;

    /*
    Get -> bir sayfaya erişme ve ondan bilgi alma isteğidir.Özel bir gereksinimi yoktur.
    Browser ların tümü get isteğidir.

     */
    @GetMapping("/save")
    public void save(String ad,String adres,String telefon){
        Musteri musteri = Musteri.builder()
                .ad(ad)
                .adres(adres)
                .telefon(telefon)
                .build();
        musteriService.save(musteri);
    }
    /*
     * ResponseEntity -> Pojo JsonObject olarak return type kullan
     * localhost/musteri/findall
     * @return
     */
    @GetMapping("/findall")
    public ResponseEntity<List<Musteri>> findAll(){
        return ResponseEntity.ok(musteriService.findAll());
    }

    @GetMapping("/findbyad")
    public ResponseEntity<List<Musteri>> findByAd(String ad,String adres){
        if(adres==null)
            return ResponseEntity.ok(musteriService.adinaGoreGetir(ad));
        return ResponseEntity.ok(musteriService.adVeAdreseGoreGetir(ad,adres));
    }

    /**
     * select * from tblmusteri where ad like '%ad' - 'a%'
     * @param ad
     * @return
     */
    @GetMapping("/findallbyadlike")
    public ResponseEntity<List<Musteri>> findAllByStartwithAd(String ad){
        return ResponseEntity.ok(musteriService.findAllByAdLike(ad+"%"));
    }
}
