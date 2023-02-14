package com.mert.SpringMono.service;

import com.mert.SpringMono.repository.IMusteriRepository;
import com.mert.SpringMono.repository.entity.Musteri;
import com.mert.SpringMono.utiliy.ServiceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MusteriService extends ServiceManager<Musteri,Long> {

    /**
     * Repositorynin enjekte edilme yöntemleri
     * 1-Field Injection -> @Autowired
     */
//    @Autowired   //bize nesne yaratır
//    private IMusteriRepository repository;

    /**
     * 2-Constructor Injection
     */
    private final IMusteriRepository repository;

    public MusteriService(IMusteriRepository repository){
        super(repository);
        this.repository = repository;
    }



    /*
     *Update için özellikle bir update komutu yoktur.Eğer entity içinde ID bilgisi bulunuyorsa
     * ilgili id kaydı için entity içindeki yeni değerler değiştirilir.
     */


    public boolean isExist(Long id){
        return repository.existsById(id);
    }

    public List<Musteri> adinaGoreGetir(String musteriadi){
        return repository.findByAd(musteriadi);
    }

    public List<Musteri> adVeAdreseGoreGetir(String ad,String adres){
        return repository.findAllByAdAndAdres(ad,adres);
    }

    public List<Musteri> findAllByAdLike(String ad){
        return repository.findAllByAdLike(ad);
    }
}
