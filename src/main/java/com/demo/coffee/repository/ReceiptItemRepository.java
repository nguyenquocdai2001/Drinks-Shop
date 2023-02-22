package com.demo.coffee.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo.coffee.entity.ReceiptItem;

public interface ReceiptItemRepository extends CrudRepository<ReceiptItem, Long>{

}
