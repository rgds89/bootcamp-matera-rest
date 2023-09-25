package com.matera.bootcamp.bank.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class Holder implements Serializable {
    private static final Long serrialVersionUID = -2338626292552177486L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String inscription;

    public Holder(Long id, String name, String inscription) {
        this.id = id;
        this.name = name;
        this.inscription = inscription;
    }
}
