package com.matera.bootcamp.bank.service;

import com.matera.bootcamp.bank.model.Holder;
import com.matera.bootcamp.bank.repository.HolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HolderService {
    @Autowired
    private HolderRepository holderRepository;

    public Holder createOrUpdateHolder(Holder holder){
        return holderRepository.save(holder);
    }
}
