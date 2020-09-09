package com.carmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.carmanagement.exception.RecordNotFoundException;
import com.carmanagement.model.Car;
import com.carmanagement.service.CarService;




@Controller
public class CarController {

	@Autowired
	CarService service;
	
	
	@GetMapping(value="")
	public String getAllCars(Model model) 
	{
		List<Car> list = service.getAllCars();

		model.addAttribute("cars", list);
		return "list-cars";
	}
	
	@RequestMapping(path = {"/edit", "/edit/{id}"})
	public String editCarById(Model model, @PathVariable("id") Optional<Long> id) 
							throws RecordNotFoundException 
	{
		if (id.isPresent()) {
			Car entity = service.getCarById(id.get());
			model.addAttribute("car", entity);
		} else {
			model.addAttribute("car", new Car());
		}
		return "add-edit-car";
	}
	
	@RequestMapping(path = "/delete/{id}")
	public String deleteCarById(Model model, @PathVariable("id") Long id) 
							throws RecordNotFoundException 
	{
		service.deleteCarById(id);
		return "redirect:/";
	}

	@RequestMapping(path = "/createCar", method = RequestMethod.POST)
	public String createOrUpdateCar(Car car) 
	{
		service.createOrUpdateCar(car);
		return "redirect:/";
	}
	@GetMapping("/403")
	public String error() {
		return"403";
	}
	
	@GetMapping("/login")
	public String logInPage() {
		return"login";
	}
	

	

}
