package com.sample.solid;

public class Application {
	public static void main(String[] args) {
		Driver car = new Driver(new Truck());
		String drive = car.drive();
		System.out.println(drive);
	}
}
