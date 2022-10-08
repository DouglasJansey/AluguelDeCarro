package model.services;

import java.time.Duration;

import model.entities.CarRental;
import model.entities.Invoice;

public class RentalServices {
	private double priceDay;
	private double priceHour;
	
	private ITaxService taxService;

	public RentalServices(double priceDay, double priceHour, ITaxService taxService) {
		this.priceDay = priceDay;
		this.priceHour = priceHour;
		this.taxService = taxService;
	}
	public void processInvoice(CarRental carRental) {
		double minutes = Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes();
		double hours = minutes / 60.0;
		double basicPayment;
		
		if(hours <= 12.0) {
			basicPayment = priceHour * Math.ceil(hours);
		}else{
			basicPayment = priceDay * Math.ceil(hours / 24.0);			
		};
		double tax = taxService.tax(basicPayment);
		
		
		carRental.setInvoice(new Invoice(basicPayment, tax));
	}
}
