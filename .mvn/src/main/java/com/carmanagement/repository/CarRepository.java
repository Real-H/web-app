package com.carmanagement.repository;

import org.springframework.data.repository.CrudRepository;

import com.carmanagement.model.Car;

public interface CarRepository extends CrudRepository<Car, Long> {

	//
}
