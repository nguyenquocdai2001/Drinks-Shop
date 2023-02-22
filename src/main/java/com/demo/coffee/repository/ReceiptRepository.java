package com.demo.coffee.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo.coffee.entity.Receipt;

public interface ReceiptRepository extends CrudRepository<Receipt, Long> {

}
