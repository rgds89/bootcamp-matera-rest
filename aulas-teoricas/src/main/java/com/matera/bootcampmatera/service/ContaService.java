package com.matera.bootcampmatera.service;

import com.matera.bootcampmatera.model.Conta;

import java.util.ArrayList;
import java.util.List;

public class ContaService {
    private List<Conta> contas = new ArrayList<>();
    private Long idCorrente = 1L;

    public void informcoesConta(Conta conta) {
        System.out.println(conta);
    }

    public Conta criar(Conta conta) {
        conta.setId(idCorrente++);
        contas.add(conta);
        return conta;
    }

    public List<Conta> getContas() {
        return contas;
    }
}
