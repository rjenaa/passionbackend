package com.batch.businessbatch.controllers;

import com.batch.businessbatch.models.BusinessAccount;
import com.batch.businessbatch.services.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class BusinessController {



    @Autowired
    private BusinessService businessService;

    @RequestMapping(value = "/business", method = RequestMethod.GET)
    public List<BusinessAccount> getAllBusinessAccounts(){
        return businessService.getAllBusiness();
    }

    @RequestMapping(value = "/business", method = RequestMethod.POST)
    public BusinessAccount createBusiness(@RequestBody BusinessAccount businessAccount){
        return businessService.addBusiness(businessAccount);
    }

    @RequestMapping(value = "/business/signin",method = RequestMethod.GET)
    public BusinessAccount signIn(@RequestParam Long id, @RequestParam String location){
        return businessService.signIn(id, location);
    }

    @RequestMapping(value = "/business/{id}", method = RequestMethod.GET)
    public BusinessAccount getBusinessById(@PathVariable Long id){
        return businessService.getBusinessById(id).get();
    }




}
