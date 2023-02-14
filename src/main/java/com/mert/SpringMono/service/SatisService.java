package com.mert.SpringMono.service;

import com.mert.SpringMono.repository.ISatisRepository;
import com.mert.SpringMono.repository.entity.Satis;
import com.mert.SpringMono.utiliy.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class SatisService extends ServiceManager<Satis,Long> {

    private final ISatisRepository repository;
    public SatisService(ISatisRepository repository){
        super(repository);
        this.repository=repository;
    }
}
