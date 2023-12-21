package com.banking.utility;

import java.util.Comparator;

import com.banking.bean.AccountHolder;

public class BalanceComparator implements Comparator {

	@Override
	public int compare(Object o1, Object o2) {
		AccountHolder c1, c2;
		Float balance1, balance2;
		c1 = (AccountHolder) o1;
		c2 = (AccountHolder) o2;
		balance1 = c1.getBalance();
		balance2 = c2.getBalance();
		return balance2.compareTo(balance1);
	}

}
