package com.banking.service;

import java.util.Date;

import com.banking.bean.AccountHolder;
import com.banking.exceptions.AgeExceededException;
import com.banking.exceptions.AgeException;
import com.banking.exceptions.IncorrectPassword;
import com.banking.exceptions.ManagerRemoveException;
import com.banking.exceptions.NegativeInterestRateException;
import com.banking.exceptions.NegativeTransactionChargeException;
import com.banking.exceptions.UserExistException;
import com.banking.exceptions.UserNotActiveException;
import com.banking.exceptions.UserNotFoundException;

public interface ManagerService {

	public int createAccount(String name, Date dateOfBirth, String password, String ifscCode, float amount)
			throws UserExistException, AgeException, AgeExceededException;

	public AccountHolder login(int accountNumber, String password)
			throws UserNotFoundException, IncorrectPassword, UserNotActiveException;

	public String updateAccount(String name, AccountHolder user);

	public void activeAccount(AccountHolder user);

	public String deleteAccount(int accountNumber) throws ManagerRemoveException, UserNotFoundException;

	public String readAccount(AccountHolder user);

	public void showAllAccount();

	public String changeInterestRate() throws NegativeInterestRateException;

	public int getInterestRate();

	public String creditInterest();

	public String changeTransactionCharges() throws NegativeTransactionChargeException;

	public int getTransactionCharge();

	public void showAccountByBalance();

	public AccountHolder getUserByAccountNumber(int accountNumber);

	public void addManager(String name, Date dateOfBirth, String password, String ifsc, int amount);
}