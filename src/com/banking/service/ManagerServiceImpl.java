package com.banking.service;

import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import com.banking.bean.AccountHolder;
import com.banking.bean.Transaction;
import com.banking.exceptions.AgeExceededException;
import com.banking.exceptions.AgeException;
import com.banking.exceptions.IncorrectPassword;
import com.banking.exceptions.ManagerRemoveException;
import com.banking.exceptions.NegativeInterestRateException;
import com.banking.exceptions.NegativeTransactionChargeException;
import com.banking.exceptions.UserExistException;
import com.banking.exceptions.UserNotActiveException;
import com.banking.exceptions.UserNotFoundException;
import com.banking.utility.BalanceComparator;
import com.banking.utility.Constants;
import com.banking.utility.Constants.transactionType;

public class ManagerServiceImpl implements ManagerService {

	Scanner scanner = new Scanner(System.in);
	static Map<Integer, AccountHolder> userList = new HashMap<Integer, AccountHolder>();
	static private int transactionCharge = Constants.TRANSACTION_CHARGE;
	static private int interestRate = Constants.INTEREST_RATE;
	static private int interestTime = Constants.INTEREST_TIME;

	public int createAccount(String name, Date dateOfBirth, String password, String ifscCode, float amount)
			throws UserExistException, AgeException, AgeExceededException {
		isDuplicate(name);
		int age = calculateAge(dateOfBirth);
		if (age < 18) {
			throw new AgeException();
		} else if (age > 100) {
			throw new AgeExceededException();
		}
		String newPassword = passwordEncrypt(password);
		AccountHolder newUser = new AccountHolder(name, dateOfBirth, newPassword, ifscCode, amount);
		Transaction transaction = new Transaction("Credit", "Deposit", amount, 0, newUser.getBalance(),
				"Account Open Deposit");
		newUser.getTransactions().add(transaction);
		userList.put(newUser.getAccountNumber(), newUser);
		return newUser.getAccountNumber();
	}

	private void isDuplicate(String name) throws UserExistException {
		boolean containsName = false;
		for (int i = 101; i <= userList.size() + 100; i++) {
			if (userList.get(i).getAccountHolderName().equalsIgnoreCase(name)) {
				containsName = true;
			}
		}
		if (containsName) {
			throw new UserExistException();
		}
	}

	private int calculateAge(Date dateOfBirth) {
		Date currentDate = new Date();
		long ageInMillis = currentDate.getTime() - dateOfBirth.getTime();
		long age = TimeUnit.DAYS.convert(ageInMillis, TimeUnit.MILLISECONDS) / 365;
		return (int) age;
	}

	public AccountHolder login(int accountNumber, String password)
			throws UserNotFoundException, IncorrectPassword, UserNotActiveException {
		AccountHolder user = getUserByAccountNumber(accountNumber);
		if (user != null) {
			if (passwordEncrypt(password).equals(user.getPassword())) {
				if (user.isActive()) {
					System.out.println(Constants.VERIFIED_USER);
				} else {
					System.out.println(Constants.USER_NOT_ACTIVE);
					System.out.println("Want to activate account(Yes/No): ");
					String choice = scanner.nextLine();
					if (choice.equalsIgnoreCase("Yes")) {
						activeAccount(user);
					} else {
						throw new UserNotActiveException();
					}
				}
			} else {
				throw new IncorrectPassword();
			}
		} else {
			throw new UserNotFoundException();
		}
		return user;
	}

	public String updateAccount(String name, AccountHolder user) {
		user.setAccountHolderName(name);
		return Constants.ACCOUNT_UPDATED;
	}

	public void activeAccount(AccountHolder user) {
		user.setActive(true);
	}

	public String deleteAccount(int accountNumber) throws ManagerRemoveException, UserNotFoundException {
		AccountHolder user = getUserByAccountNumber(accountNumber);
		if (user == null) {
			throw new UserNotFoundException();
		} else if (user.isManager()) {
			throw new ManagerRemoveException();
		} else {
			userList.remove(user.getAccountNumber());
			return Constants.ACCOUNT_REMOVED;
		}
	}

	public String readAccount(AccountHolder user) {
		return "Name: " + user.getAccountHolderName() + "\nAccount Number: " + user.getAccountNumber() + "\nAge: "
				+ calculateAge(user.getDateOfBirth()) + "\nBalance: " + user.getBalance() + "\nIFSC: "
				+ user.getIfscCode() + "\nActive: " + user.isActive() + "\nAccount Created: "
				+ user.getAccountCreationDateTime();
	}

	public void showAllAccount() {
		for (int i = 101; i <= userList.size() + 100; i++) {
			System.out.println(userList.get(i));
			System.out.println();
		}
	}

	public String changeInterestRate() throws NegativeInterestRateException {
		System.out.println("Current Interest rate: " + interestRate);
		System.out.print("Enter New Interest Rate: ");
		int rate = scanner.nextInt();
		if (rate < 0) {
			throw new NegativeInterestRateException();
		}
		return Constants.CHANGED_INTEREST;
	}

	public int getInterestRate() {
		return interestRate;
	}

	public String creditInterest() {
		for (int i = 101; i <= userList.size() + 100; i++) {
			if (userList.get(i).isActive()) {
				float previousBalance = userList.get(i).getBalance();
				userList.get(i).setBalance(userList.get(i).getBalance()
						+ ((userList.get(i).getBalance() * interestRate * interestTime) / 100));
				Transaction transaction = new Transaction(transactionType.Credit.toString(), "Interest",
						userList.get(i).getBalance() - previousBalance, 0, userList.get(i).getBalance(), null);
				userList.get(i).getTransactions().add(transaction);
			}
		}
		return Constants.INTEREST_CREDITED;
	}

	public String changeTransactionCharges() throws NegativeTransactionChargeException {
		System.out.println("Current Transaction Charges: " + transactionCharge);
		System.out.print("Enter Transaction charge: ");
		int charge = scanner.nextInt();
		if (charge < 0) {
			throw new NegativeTransactionChargeException();
		}
		transactionCharge = charge;
		return Constants.CHANGED_TRANSACTION;
	}

	public int getTransactionCharge() {
		return transactionCharge;
	}

	public void showAccountByBalance() {
		TreeSet<AccountHolder> ts1 = new TreeSet<AccountHolder>(new BalanceComparator());
		for (AccountHolder accountHolder : userList.values()) {
			ts1.add(accountHolder);
		}
		for (AccountHolder accountHolder : ts1) {
			System.out.println(accountHolder.getAccountHolderName() + "\t" + accountHolder.getBalance());
		}
	}

	public AccountHolder getUserByAccountNumber(int accountNumber) {
		return userList.get(accountNumber);
	}

	String passwordEncrypt(String password) {
		return password + "encrypt";
	}

	public void addManager(String name, Date dateOfBirth, String password, String ifsc, int amount) {
		AccountHolder user = new AccountHolder();
		user.setAccountHolderName(name);
		user.setPassword(passwordEncrypt(password));
		user.setIfscCode(ifsc);
		user.setBalance(amount);
		user.setIsManager(true);
		user.setDateOfBirth(dateOfBirth);
		user.setAccountCreationDateTime(new Date());
		userList.put(user.getAccountNumber(), user);
	}
}