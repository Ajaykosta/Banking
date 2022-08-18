package com.banking.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.banking.utility.Constants;

public class AccountHolder {

	private static int accountCount = Constants.ACCOUNT_COUNT_START;
	private int accountNumber;
	private String accountHolderName;
	private String password;
	private Date dateOfBirth;
	private float balance;
	private String ifscCode;
	private Date accountCreationDateTime;
	private Date interestPaidDateTime;
	private boolean isManager = Constants.IS_NOT_MANAGER;
	private boolean isActive;
	private List<Transaction> transactions;

	public AccountHolder() {
		transactions = new ArrayList<>();
		accountNumber = ++accountCount;
		this.isActive = true;
	}

	public AccountHolder(String name, Date dateOfBirth, String password, String ifscCode, float balance) {
		transactions = new ArrayList<>();
		accountNumber = ++accountCount;
		this.accountCreationDateTime = new Date();
		this.accountHolderName = name;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.ifscCode = ifscCode;
		this.balance = balance;
		this.isActive = true;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public Date getAccountCreationDateTime() {
		return accountCreationDateTime;
	}

	public void setAccountCreationDateTime(Date accountCreationDateTime) {
		this.accountCreationDateTime = accountCreationDateTime;
	}

	public Date getInterestPaidDateTime() {
		return interestPaidDateTime;
	}

	public void setInterestPaidDateTime(Date interestPaidDateTime) {
		this.interestPaidDateTime = interestPaidDateTime;
	}

	public boolean isManager() {
		return isManager;
	}

	public void setIsManager(boolean isManager) {
		this.isManager = isManager;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String toString() {
		return "AccountHolder [accountNumber=" + accountNumber + ", accountHolderName=" + accountHolderName
				+ ", balance=" + balance + ", ifscCode=" + ifscCode + ", isActive=" + isActive + "]";
	}
}