package com.bloomberg.deals.fxDeals.repository;

import com.bloomberg.deals.fxDeals.entity.FileCheck;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileCheckRepository extends CrudRepository<FileCheck,Integer> {

    FileCheck findByFileName(String username);
}
