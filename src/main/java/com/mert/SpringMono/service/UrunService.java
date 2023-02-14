package com.mert.SpringMono.service;

import com.mert.SpringMono.repository.IUrunRepository;
import com.mert.SpringMono.repository.entity.Urun;
import com.mert.SpringMono.utiliy.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class UrunService extends ServiceManager<Urun,Long> {

    public UrunService(IUrunRepository repository){
        super(repository);
    }
}
