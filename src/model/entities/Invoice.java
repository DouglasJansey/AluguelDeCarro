package model.entities;

public class Invoice {
	private double basicPayment;
	private double tax;

	public Invoice() {
	}

	public Invoice(double basciPayment, double tax) {
		this.basicPayment = basciPayment;
		this.tax = tax;
	}

	public double getBasicPayment() {
		return basicPayment;
	}

	public void setBasciPayment(double basciPayment) {
		this.basicPayment = basciPayment;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}
	public double getTotalPayment() {
		return getBasicPayment() + getTax();
	}
}
