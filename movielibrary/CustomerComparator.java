package movielibrary;

import java.util.Comparator;

public class CustomerComparator implements Comparator<Customer>{

	@Override
	public int compare(Customer cust1, Customer cust2) {
		return cust1.getLastName().compareTo(cust2.getLastName());
	}
}
