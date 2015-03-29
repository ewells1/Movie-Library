package movielibrary;

import java.time.LocalDate;

public class Rental {

	Disc disc;
	double pricePerDay;
	int rentalDays;
	LocalDate dueDate;
	
	public Rental(Disc disc, Double pricePerDay, int rentalDays) {
		super();
		this.disc = disc;
		this.pricePerDay = pricePerDay;
		this.rentalDays = rentalDays;
		this.dueDate = LocalDate.now().plusDays(rentalDays);
	}
	
	public int getRentalDays() {
		return rentalDays;
	}

	public void setRentalDays(int rentalDays) {
		this.rentalDays = rentalDays;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public Disc getDisc() {
		return disc;
	}

	public double getPricePerDay() {
		return pricePerDay;
	}

	public boolean isOverdue(){
		return !dueDate.isAfter(LocalDate.now());
	}
	
	public double getCost(){
		if (!isOverdue()){
			return pricePerDay * rentalDays;
		}
		return pricePerDay * (rentalDays + dueDate.until(LocalDate.now()).getDays());
	}
	
	@Override
	public String toString(){
		String ret = disc.toString();
		ret += "/nCost: " + getCost();
		ret += "/nDueDate: " + dueDate;
		if (isOverdue()){
			ret += " (Overdue)";
		}
		return ret;
	}
}
