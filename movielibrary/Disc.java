package movielibrary;

import java.io.Serializable;

public class Disc implements Serializable{

	private static final long serialVersionUID = 5859376006788602737L;
	String number;
	Boolean availability;
	MovieVersion version;
	
	public Disc(String num, MovieVersion vers){
		 number = num;
	     version = vers;
	     availability = true;
	}

	public String getNumber(){
	    return number;
	}

	public void editNumber(String idNumber) {
		this.number = idNumber;
	}

	public boolean getAvailability(){
		return availability;
	}
	 
	public String getAvailStr(){
		if (availability){
			return "Available";
		}
		return "Unavailable";
	}

	public MovieVersion getVersion() {
		return version;
	}

	public void setAvailability(Boolean availability) {
		this.availability = availability;
	}

	@Override
	public String toString() {
		return version + " #" + number + ": " + getAvailStr();
	}
}
