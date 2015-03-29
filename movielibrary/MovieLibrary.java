package movielibrary;

import java.io.Serializable;
import java.util.ArrayList;

public class MovieLibrary implements Serializable{

	private static final long serialVersionUID = -1503461739884644245L;
	private ArrayList<Movie> movies = new ArrayList<Movie>();
	private ArrayList<Customer> customers = new ArrayList<Customer>();
	private double profits = 0.00;
	
	/**
	 * Gets movie with specified title or ID number.
	 * @param str Either title of movie or ID number of a disc.
	 * @return Movie with matching title or a disc with matching number.
	 */
	public Movie getMovie(String str){
		for (Movie movie : movies){
			if (movie.getTitle().equals(str)){
				return movie;
			} else if (movie.getDisc(str) != null){
				return movie;
			}
		}
		return null;
	}
	
	/**
	 * Returns a disc with given ID number.
	 * @param number ID number of disc to get.
	 * @return Disc object with ID number.
	 */
	public Disc getDisc(String number){
		for (Movie movie : movies){
			for (Disc disc : movie.getDiscs())
				if (disc.getNumber().equals(number)){
					return disc;
				}
		}
		return null;
	}
	
	public ArrayList<Movie> getMovies() {
		return movies;
	}
	
	public String[] getMovieTitles() {
		String[] titles = new String[movies.size()];
		for (int i = 0; i < titles.length; i++){
			titles[i] = movies.get(i).getTitle();
		}
		return titles;
	}
	
	/**
	 * Returns an ArrayList of titles of each movie.
	 * @return ArrayList of titles.
	 */
	public String displayTitles() {
		StringBuilder titles = new StringBuilder();
		for (Movie movie : movies){
			titles.append(movie.getTitle() + "\n");
		}
		return titles.toString();
	}

	/**
	 * Adds a movie object to the library with given title.
	 * Also adds discs with provided numbers.
	 * @param title Title of movie.
	 */
	public void addMovie(String title){
		Movie newMovie = new Movie(title);
		movies.add(newMovie);
	}
	
	public void addDiscToMovie(String titleOfMovie, String discID, MovieVersion vers){
		getMovie(titleOfMovie).addDisc(discID, vers);
	}
	
	/**
	 * Removes a movie from the library.
	 * @param str Title of movie or ID number of one of the discs.
	 */
	public void removeMovie(String str){
		if (getMovie(str) != null){
			movies.remove(getMovie(str));
		}
	}
	
	/**
	 * Puts all discs in library into one list.
	 * @return ArrayList of all discs in library.
	 */
	public ArrayList<Disc> getAllDiscs(){
		ArrayList<Disc> ret = new ArrayList<Disc>();
		for (Movie movie : movies){
			for (Disc disc : movie.getDiscs()){
				ret.add(disc);
			}
		}
		return ret;
	}
	
	public String[] getDiscIDs(){
		String[] ret = new String[getAllDiscs().size()];
		for (int i = 0; i < ret.length; i++){
			ret[i] = getAllDiscs().get(i).getNumber();
		}
		return ret;
	}
	
	/**
	 * Gets customer account with specified last name or phone number.
	 * @param str Either last name or phone number.
	 * @return Movie with matching title or a disc with matching number.
	 */
	public Customer getCustomer(String str){
		for (Customer customer : customers){
			if (customer.getLastName().equals(str)){
				return customer;
			} else if (customer.getPhoneNumber().equals(str)){
				return customer;
			}
		}
		return null;
	}
	
	public String[] getCustomerNames(){
		String[] ret = new String[customers.size()];
		for (int i = 0; i < ret.length; i++){
			ret[i] = customers.get(i).getLastName();
		}
		return ret;
	}
	
	/**
	 * Removes a customer from the library.
	 * @param str Title of movie or ID number of one of the discs.
	 */
	public void removeCustomer(String str){
		if (getCustomer(str) != null){
			customers.remove(getCustomer(str));
		}
	}
	
	public void takeOutDisc(Disc disc, Customer customer, double price, int days){
		customer.takeOutDisc(disc, price, days);
		disc.setAvailability(false);
	}
	
	public void payCustBalance(double amount, Customer customer){
		customer.addToBalance(amount);
		profits += amount;
	}
	
	public void returnMovie(String discID, Customer customer){
		customer.returnDisc(discID);
	}
	
	public double getProfits() {
		return profits;
	}

	@Override
	public String toString(){
		String ret = "\n";
		MovieComparator mc = new MovieComparator();
		movies.sort(mc);
		for (Movie movie : movies){
			ret += movie + "\n\n";
		}
		return ret;
	}
}
