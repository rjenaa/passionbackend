package com.batch.businessbatch.services;

import com.batch.businessbatch.models.BusinessAccount;
import com.batch.businessbatch.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusinessService {
    
    @Autowired
    private BusinessRepository businessRepository;
    
    public BusinessAccount addBusiness(BusinessAccount businessAccount){
         return businessRepository.save(businessAccount);
    }

    public BusinessAccount updateBusiness(BusinessAccount businessAccount){
        return businessRepository.save(businessAccount);
    }

    public void deleteBusiness(Long id){
         businessRepository.deleteById(id);
    }

    public Optional<BusinessAccount> getBusinessById(Long Id){
        return businessRepository.findById(Id);
    }

    public List<BusinessAccount> getAllBusiness(){
        return businessRepository.findAll();
    }

    public BusinessAccount signIn(Long id, String location){
        if(businessRepository.findById(id).get().getLocation().equalsIgnoreCase(location)){
            return businessRepository.findById(id).get();
        }
        return null;
    }




}
