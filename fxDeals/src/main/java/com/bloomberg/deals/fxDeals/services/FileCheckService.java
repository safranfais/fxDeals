package com.bloomberg.deals.fxDeals.services;

import com.bloomberg.deals.fxDeals.entity.FileCheck;
import com.bloomberg.deals.fxDeals.repository.FileCheckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FileCheckService {

    @Autowired
    FileCheckRepository fileCheckRepository;

    public List<FileCheck> getAllFileNames() {
        return (ArrayList<FileCheck>) fileCheckRepository.findAll();
    }

    public FileCheck addFileName(String fileName){
        FileCheck addFileName = new FileCheck();
        addFileName.setFileName(fileName);
        addFileName.setIsActive(1);
        return fileCheckRepository.save(addFileName);
    }

    public FileCheck findByFileName(String fileName){
        return fileCheckRepository.findByFileName(fileName);
    }



}
