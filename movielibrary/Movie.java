package movielibrary;

import java.io.Serializable;
import java.util.ArrayList;

public class Movie implements Serializable{
	
	private static final long serialVersionUID = -1374411410760646690L;
	private String title;
	private ArrayList<Disc> discs = new ArrayList<Disc>();

	public Movie(String title){
		this.title = title;
	}

	/**
	 * Adds disc to movie's list of discs.
	 * @param idNumber ID number of disc being added.
	 * @param version Version of movie on disc.
	 */
	public void addDisc(String idNumber, MovieVersion version) {
		discs.add(new Disc(idNumber, version));
	}
	
	/**
	 * Returns disc with given ID number.
	 * @param idNumber ID number of disc.
	 * @return Disc with given ID number.
	 */
	public Disc getDisc(String idNumber){
		for(Disc disc : discs){
			if (disc.getNumber().equals(idNumber)){
				return disc;
			}
		}
		return null;
	}

	public String getTitle() {
		return title;
	}
	
	public ArrayList<Disc> getDiscs() {
		return discs;
	}
	
	public String[] getDiscIDs(){
		String[] ret = new String[discs.size()];
		for (int i = 0; i < ret.length; i++){
			ret[i] = discs.get(i).getNumber();
		}
		return ret;
	}

	public void editTitle(String title) {
		this.title = title;
	}

	/**
	 * Removes disc from movie object.
	 * @param idNumber ID number of disc to remove.
	 */
	public void removeDisc(String idNumber){
		if (getDisc(idNumber) != null){
			discs.remove(getDisc(idNumber));
		}
	}
	
	public String displayDiscs(){
		String ret = "";
		for (Disc disc : discs){
			ret += disc + "\n";
		}
		return ret;
	}

	@Override
	public String toString() {
		String ret = title + ":";
		for (Disc disc : discs){
			ret += "\n\t" + disc;
		}
		return ret;
	}
}
