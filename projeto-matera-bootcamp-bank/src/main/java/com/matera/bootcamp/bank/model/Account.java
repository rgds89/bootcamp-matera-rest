package com.matera.bootcamp.bank.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
public class Account implements Serializable {
    private static final Long serrialVersionUID = -2338626292552177485L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String account_number;
    private String branch;
    private BigDecimal balance = BigDecimal.ZERO;
    @OneToOne
    @JoinColumn(name = "holder")
    private Holder holder;

    public Account(Long id, String account_number, String branch, BigDecimal balance, Holder holder) {
        this.id = id;
        this.account_number = account_number;
        this.branch = branch;
        this.balance = balance;
        this.holder = holder;
    }

    public void debit(BigDecimal value){
        balance = balance.subtract(value);
    }

    public void credit (BigDecimal value){
        balance = balance.add(value);
    }
}
