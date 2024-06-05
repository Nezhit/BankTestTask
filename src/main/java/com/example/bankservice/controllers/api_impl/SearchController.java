package com.example.bankservice.controllers.api_impl;

import com.example.bankservice.controllers.SearchApi;
import com.example.bankservice.dto.FIOSearchRequest;
import com.example.bankservice.entity.Client;
import com.example.bankservice.service.SearchService;

import java.util.List;

public class SearchController implements SearchApi {
    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }


    @Override
    public List<Client> searchByBirthdate(String birthdate) {
        return searchService.searchByBirthdate(birthdate);
    }

    @Override
    public List<Client> searchByPhone(String phone) {
        return searchService.searchByPhone(phone);
    }

    @Override
    public List<Client> searchByEmail(String email) {
        return searchService.searchByEmail(email);
    }

    @Override
    public List<Client> searchByFIO(FIOSearchRequest fioSearchRequst) {
        return searchService.searchByFIO(fioSearchRequst);
    }
}
