package movielibrary;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Customer implements Serializable{

	private static final long serialVersionUID = 2748311059926234193L;
	
	private String phoneNumber;
	private String lastName;
	private ArrayList<String> renters = new ArrayList<String>();
	private ArrayList<Rental> rentals = new ArrayList<Rental>();
	private double accountBalance = 0.00;
	
	public Customer(String phoneNumber, String lastName,
			ArrayList<String> renters) {
		super();
		this.phoneNumber = phoneNumber;
		this.lastName = lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getLastName() {
		return lastName;
	}

	public ArrayList<String> getRenters() {
		return renters;
	}
	
	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double balance) {
		this.accountBalance = balance;
	}
	
	public void addToBalance(double amount) {
		this.accountBalance += amount;
	}

	public ArrayList<Rental> getRentals() {
		return rentals;
	}

	public void addRenter(String name){
		renters.add(name);
	}
	
	public void removeRenter(String renter){
		renters.remove(renter);
	}
	
	public void takeOutDisc(Disc disc, double price, int days){
		rentals.add(new Rental(disc, price, days));
		addToBalance(-(price));
	}
	
	public void returnDisc(String discID){
		Rental rental = null;
		for (Rental r : rentals){
			if (discID.equals(r.getDisc().getNumber())){
				rental = r;
			}
		}
		if (rental != null){
			rentals.remove(rental);
		} else {
			JOptionPane.showMessageDialog(null,
					"Disc " + discID + " not rented by this customer",
							"Error",
							JOptionPane.WARNING_MESSAGE);
		}
	}
	
	@Override
	public String toString() {
		String ret = lastName + ":" + "/nPhone Number: " + phoneNumber + "\nMovies Out:";
		for (Rental rental : rentals){
			ret += "\n\t" + rental;
		}
		ret += "/nApproved Renters:";
		for (String renter : renters){
			ret += "\n\t" + renter;
		}
		ret += "/nAccount Balnce: " + accountBalance;
		return ret;
	}
}
