package com.matera.bootcamp.bank.repository;

import com.matera.bootcamp.bank.model.Holder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolderRepository extends JpaRepository<Holder, Long> {
}
