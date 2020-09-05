package com.carmanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carmanagement.exception.RecordNotFoundException;
import com.carmanagement.model.Car;
import com.carmanagement.repository.CarRepository;



@Service
public class CarService {

	@Autowired
	CarRepository repository;
	
	public List<Car> getAllCars()
	{
		List<Car> result = (List<Car>) repository.findAll();
		
		if(result.size() > 0) {
			return result;
		} else {
			return new ArrayList<Car>();
		}
	}
	
	public Car getCarById(Long id) throws RecordNotFoundException 
	{
		Optional<Car> car = repository.findById(id);
		
		if(car.isPresent()) {
			return car.get();
		} else {
			throw new RecordNotFoundException("No car record exist for given id");
		}
	}
	
	public Car createOrUpdateCar(Car entity) 
	{
		if(entity.getId()  == null) 
		{
			repository.save(entity);
			
			return entity;
		} 
		else 
		{
			Optional<Car> car = repository.findById(entity.getId());
			
			if(car.isPresent()) 
			{
				Car newEntity = car.get();
				newEntity.setCarName(entity.getCarName());
				newEntity.setBrand(entity.getBrand());
				newEntity.setColor(entity.getColor());
				newEntity.setPrice(entity.getPrice());

				newEntity = repository.save(newEntity);
				
				return newEntity;
			} else {
				repository.save(entity);
				
				return entity;
			}
		}
	} 
	
	public void deleteCarById(Long id) throws RecordNotFoundException 
	{
		Optional<Car> car = repository.findById(id);
		
		if(car.isPresent()) 
		{
			repository.deleteById(id);
		} else {
			throw new RecordNotFoundException("No car record exist for given id");
		}
	}
}
