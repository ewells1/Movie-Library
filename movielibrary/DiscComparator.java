package movielibrary;

import java.util.Comparator;

public class DiscComparator implements Comparator<Disc>{

	@Override
	public int compare(Disc disc1, Disc disc2) {
		return disc1.getAvailStr().compareTo(disc2.getAvailStr());
	}
}
