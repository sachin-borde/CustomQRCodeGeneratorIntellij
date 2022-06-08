package com.main.dao;

import org.springframework.data.repository.CrudRepository;

import com.main.model.Company;

public interface CompanyDao extends CrudRepository<Company, Integer> {

}
