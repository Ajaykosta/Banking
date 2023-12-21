package com.banking.utility;

public class Constants {
	public static final String INVALID_CHOICE = "Invalid Choice";
	public static final String USER_ADDED = "Congratulations account created Successfully !, Your account number is ";
	public static final String INVALID_USER = "User Not Found for this Account Number";
	public static final String INCORRECT_PASSWORD = "Incorrect Password";
	public static final String USER_NOT_ACTIVE = "User Not Active";
	public static final String VERIFIED_USER = "User Verified";
	public static final String CHANGED_TRANSACTION = "Transaction Charges Changed Successfully";
	public static final String CHANGED_INTEREST = "Interest Rate Changed Successfully";
	public static final String INSUFFICIENT_BALANCE = "Insufficient Balance";
	public static final String INTEREST_CREDITED = "Interest has been Credited Successfully";
	public static final String ACCOUNT_REMOVED = "Account has been Removed Successfully";
	public static final String WITHDRAW_SUCCESSFULLY = "Amount Withdraw Successfully";
	public static final String DEPOSITED_SUCCESSFULLY = "Amount Deposited Sucessfully";
	public static final String ACCOUNT_UPDATED = "Account has been Updated";
	public static final String ACCOUNT_DISABLE = "Account has been Disable";
	public static final String NEGATIVE_AMOUNT = "Amount must be Positive ";
	public static final String NEGATIVE_TRANSACTION_CHARGE = "Transaction Charges must be Positive";
	public static final String NEGATIVE_INTEREST_RATE = "Interest Rate must be Positive";
	public static final String REMOVE_MANAGER = "Cannot Remove Manger";
	public static final String SAME_ACCOUNT_TRANSFER = "Cannot Transfer Money to our Own Account";
	public static final String MONEY_TRSNSFER = "Money Transfer Successfully";
	public static final String USER_EXIST = "User Already Exist with Same Name";
	public static final String AGE_RESTRICTION = "You must have 18+ to Open Account";
	public static final String AGE_EXCEEDED = "Age is over 100";
	public static final int ACCOUNT_COUNT_START = 100;
	public static final int TRANSACTION_CHARGE = 10;
	public static final int INTEREST_RATE = 6;
	public static final int INTEREST_TIME = 2;
	public static final boolean IS_NOT_MANAGER = false;

	public static enum transactionType {
		Credit, Debit
	};
}