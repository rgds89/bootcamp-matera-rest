package com.matera.bootcampmatera.controller;

import com.matera.bootcampmatera.model.Conta;
import com.matera.bootcampmatera.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contas")
@RequiredArgsConstructor
public class ContaController {
    //  1 maneira
//    @Autowired
//    private ContaService contaService;

    // 2 maneira e adicionando no onstrutor ou usando @RequiredArgsConstructor do lombok
//    private final ContaService contaService;
//    public ContaController(ContaService contaService) {
//        this.contaService = contaService;
//    }

    private final ContaService contaService;

    @GetMapping
    public List<Conta> teste() {
        return contaService.getContas();
    }

    @PostMapping
    public ResponseEntity<Conta> novaConta(@RequestBody Conta conta) {
        return ResponseEntity.ok(contaService.criar(conta));
    }

    // http://localhost:8080/contas/1
    @GetMapping("/{id}")
    public ResponseEntity<Conta> buscarPorId(@PathVariable Long id) {
        List<Conta> contas = contaService.getContas();
        Optional<Conta> contaOptional = contas.stream()
                .filter(conta -> conta.getId().equals(id))
                .findFirst();
        if (contaOptional.isPresent()) {
            Conta conta = contaOptional.get();
            return ResponseEntity.ok(conta);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 3 maneira "setinjection"
//    private ContaService contaService;
//
//    @Autowired
//    public void setContaService(ContaService contaService) {
//        this.contaService = contaService;
//    }
}
