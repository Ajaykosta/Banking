package com.banking.service;

import com.banking.bean.AccountHolder;
import com.banking.exceptions.InsufficientBalanceException;
import com.banking.exceptions.NegativeAmountException;
import com.banking.exceptions.SameAccountTransferException;

public interface UserService {
	public float withdrawMoney(float amount, AccountHolder user)
			throws InsufficientBalanceException, NegativeAmountException;

	public float depositMoney(float amount, AccountHolder user) throws NegativeAmountException;

	public String transferMoney(AccountHolder fromUser, AccountHolder toUser, int amount, String description)
			throws InsufficientBalanceException, NegativeAmountException, SameAccountTransferException;

	public void transactionHistory(AccountHolder user);

	public void Statement(AccountHolder user);

	public float accruedInterest(AccountHolder user);
}
