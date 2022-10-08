package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.RentalServices;
import model.services.ServiceTax;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		
		DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		System.out.println("Entre com os dados do alugel");
		System.out.print("Modelo do carro");
		String carModel = sc.nextLine();
		
		System.out.println("Retirada(dd/mm/yyyy) hh:mm");
		LocalDateTime start = LocalDateTime.parse(sc.nextLine(), formatDate);

		System.out.println("Retorno(dd/mm/yyyy) hh:mm");
		LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), formatDate);
		
		CarRental cr = new CarRental(start, finish, new Vehicle(carModel));
		
		System.out.println("Entre com o preço por hora: ");
		double pricePerHour = sc.nextDouble();
		
		System.out.println("Entre com o preço por dia: ");
		double pricePerDay = sc.nextDouble();
		
		RentalServices rentalService = new RentalServices(pricePerDay, pricePerHour, new ServiceTax());
		rentalService.processInvoice(cr);
		System.out.println("=============FATURA==============");
		System.out.println("Pagamento basico: " + String.format("%.2f", cr.getInvoice().getBasicPayment()) );
		System.out.println("Imposto: " +  String.format("%.2f", cr.getInvoice().getTax()));
		System.out.println("Pagamento total: " + String.format("%.2f", cr.getInvoice().getTotalPayment()));
		sc.close();
	}

}
