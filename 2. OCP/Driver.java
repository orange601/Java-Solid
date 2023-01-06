package com.sample.solid;

public class Driver {
	private Car car;
	public Driver(Car car) {
		this.car = car;
	}
	public String drive() {
		return car.drive();
	}
}
