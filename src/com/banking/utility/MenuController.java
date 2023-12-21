package com.banking.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import com.banking.bean.AccountHolder;
import com.banking.exceptions.AgeExceededException;
import com.banking.exceptions.AgeException;
import com.banking.exceptions.IncorrectPassword;
import com.banking.exceptions.InsufficientBalanceException;
import com.banking.exceptions.ManagerRemoveException;
import com.banking.exceptions.NegativeAmountException;
import com.banking.exceptions.NegativeInterestRateException;
import com.banking.exceptions.NegativeTransactionChargeException;
import com.banking.exceptions.SameAccountTransferException;
import com.banking.exceptions.UserExistException;
import com.banking.exceptions.UserNotActiveException;
import com.banking.exceptions.UserNotFoundException;
import com.banking.service.ManagerService;
import com.banking.service.ManagerServiceImpl;
import com.banking.service.UserService;
import com.banking.service.UserServiceImpl;

public class MenuController {

	UserService userService = new UserServiceImpl();
	ManagerService managerService = new ManagerServiceImpl();

	public MenuController() {
		String stringDateOfBirth = "01/01/1990";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date dateOfBirth = sdf.parse(stringDateOfBirth);
			managerService.addManager("Manager", dateOfBirth, "123", "456", 0);
		} catch (ParseException pe) {
			System.out.println("Invalid Date Format");
			pe.getCause();
		}
	}

	public void mainMenu() {
		System.out.println("\t\t\tWELCOME TO BANK");
		System.out.println("1. New User");
		System.out.println("2. Login");
		System.out.println("3. Exit");
		System.out.print("Enter you choice: ");
		int choice = getScanner().nextInt();
		mainMenuController(choice);
	}

	public void mainMenuController(int choice) {
		switch (choice) {
		case 1:
			try {
				newUser();
			} catch (NegativeAmountException nae) {
				nae.getCause();
			} catch (ParseException pe) {
				System.out.println("Invalid Date Format");
				pe.getCause();
			}
			break;
		case 2:
			login();
			break;
		case 3:
			System.out.println("Application Closed");
			System.exit(0);
			break;
		default:
			System.out.print(Constants.INVALID_CHOICE);
		}
	}

	void newUser() throws NegativeAmountException, ParseException {
		System.out.print("Enter Name: ");
		String name = getScanner().nextLine();
		System.out.println("Enter Date of Birth(dd/mm/yyyy): ");
		String stringDateOfBirth = getScanner().nextLine();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dateOfBirth = sdf.parse(stringDateOfBirth);
		System.out.print("Set Password: ");
		String password = getScanner().nextLine();
		System.out.print("Enter Amount to deposit: ");
		float amount = getScanner().nextFloat();
		if (amount < 0) {
			throw new NegativeAmountException();
		}
		System.out.print("Enter IFSC Code: ");
		String ifsc = getScanner().nextLine();
		try {
			int accNum = managerService.createAccount(name, dateOfBirth, password, ifsc, amount);
			System.out.println(Constants.USER_ADDED + accNum);
		} catch (UserExistException uee) {
			uee.getCause();
		} catch (AgeException ae) {
			ae.getCause();
		} catch (AgeExceededException aee) {
			aee.getCause();
		}
	}

	void login() {
		System.out.print("Enter Account Number: ");
		int accountNumber = getScanner().nextInt();
		System.out.print("Enter Password: ");
		String password = getScanner().nextLine();
		try {
			AccountHolder user = managerService.login(accountNumber, password);
			while (true) {
				if (user.isManager()) {
					while (true) {
						managerMenu();
						int choice = getScanner().nextInt();
						managerController(choice);
						if (choice == 8) {
							break;
						}
					}
				}
				userMenu();
				int choice = getScanner().nextInt();
				userController(choice, user);
				if (choice == 10) {
					break;
				}
			}
		} catch (UserNotFoundException unfe) {
			unfe.getCause();
		} catch (IncorrectPassword ip) {
			ip.getCause();
		} catch (UserNotActiveException unae) {
			unae.getCause();
		}
	}

	public void managerMenu() {
		System.out.println("\t\t\tMANAGER SERVICES");
		System.out.println("1. Credit Interest");
		System.out.println("2. Show All User");
		System.out.println("3. Show User by Account Number");
		System.out.println("4. Show Account by Balance Order");
		System.out.println("5. Remove Account");
		System.out.println("6. Change Transaction Charge");
		System.out.println("7. Change Interest Rate");
		System.out.println("8. Exit Manager Menu");
		System.out.print("Enter Your Choice: ");
	}

	public void managerController(int choice) {
		switch (choice) {
		case 1:
			System.out.println(managerService.creditInterest());
			break;
		case 2:
			managerService.showAllAccount();
			break;
		case 3:
			try {
				showAccount();
			} catch (UserNotFoundException unfe) {
				unfe.getCause();
			}
			break;
		case 4:
			managerService.showAccountByBalance();
			break;
		case 5:
			removeAccount();
			break;
		case 6:
			try {
				System.out.println(managerService.changeTransactionCharges());
			} catch (NegativeTransactionChargeException ntce) {
				ntce.getCause();
			}
			break;
		case 7:
			try {
				System.out.println(managerService.changeInterestRate());
			} catch (NegativeInterestRateException nire) {
				nire.getCause();
			}
			break;
		case 8:
			break;
		default:
			System.out.println(Constants.INVALID_CHOICE);
		}
	}

	void showAccount() throws UserNotFoundException {
		System.out.print("Enter Account Number: ");
		int accountNumber = getScanner().nextInt();
		if (managerService.getUserByAccountNumber(accountNumber) == null) {
			throw new UserNotFoundException();
		} else {
			System.out.println(managerService.getUserByAccountNumber(accountNumber));
		}
	}

	void removeAccount() {
		System.out.print("Enter Account Number: ");
		int accountNumber = getScanner().nextInt();
		try {
			System.out.println(managerService.deleteAccount(accountNumber));
		} catch (ManagerRemoveException mre) {
			mre.getCause();
		} catch (UserNotFoundException unfe) {
			unfe.getCause();
		}
	}

	public void userMenu() {
		System.out.println("1. Withdraw");
		System.out.println("2. Deposit");
		System.out.println("3. Money Transfer");
		System.out.println("4. Transaction History");
		System.out.println("5. Statements");
		System.out.println("6. Update Profile");
		System.out.println("7. Close Account");
		System.out.println("8. Show Profile");
		System.out.println("9. Accrued Interest");
		System.out.println("10. Log Out");
		System.out.print("Enter your choice: ");
	}

	public void userController(int choice, AccountHolder user) {
		switch (choice) {
		case 1:
			withdraw(user);
			break;
		case 2:
			deposit(user);
			break;
		case 3:
			try {
				transfer(user);
			} catch (UserNotFoundException unfe) {
				unfe.getCause();
			} catch (UserNotActiveException unae) {
				unae.getCause();
			}
			break;
		case 4:
			userService.transactionHistory(user);
			break;
		case 5:
			userService.Statement(user);
			break;
		case 6:
			updateProfile(user);
			break;
		case 7:
			closeAccount(user);
			break;
		case 8:
			System.out.println(managerService.readAccount(user));
			break;
		case 9:
			System.out.println(userService.accruedInterest(user));
			break;
		case 10:
			break;
		default:
			System.out.println(Constants.INVALID_CHOICE);
		}
	}

	void withdraw(AccountHolder user) {
		System.out.print("Enter Amount to withdraw: ");
		float amount = getScanner().nextFloat();
		try {
			float amountRemaining = userService.withdrawMoney(amount, user);
			System.out.println(Constants.WITHDRAW_SUCCESSFULLY);
			System.out.println("Amount Remaining: " + amountRemaining);
		} catch (InsufficientBalanceException ibe) {
			ibe.getCause();
		} catch (NegativeAmountException nae) {
			nae.getCause();
		}
	}

	void deposit(AccountHolder user) {
		System.out.print("Enter Amount to deposit: ");
		float amount = getScanner().nextFloat();
		try {
			float amountRemaining = userService.depositMoney(amount, user);
			System.out.println(Constants.DEPOSITED_SUCCESSFULLY);
			System.out.println("New Amount: " + amountRemaining);
		} catch (NegativeAmountException nae) {
			nae.getCause();
		}
	}

	public void transfer(AccountHolder user) throws UserNotFoundException, UserNotActiveException {
		System.out.print("Enter Account Number to whom you want to transfer: ");
		int toTransferAccountNumber = getScanner().nextInt();
		AccountHolder toTransferUser = managerService.getUserByAccountNumber(toTransferAccountNumber);
		if (toTransferUser != null) {
			if (toTransferUser.isActive()) {
				System.out.print("Enter amount to transfer: ");
				int amount = getScanner().nextInt();
				System.out.print("Description: ");
				String description = getScanner().nextLine();
				try {
					System.out.println(userService.transferMoney(user, toTransferUser, amount, description));
				} catch (InsufficientBalanceException ibe) {
					ibe.getCause();
				} catch (NegativeAmountException nae) {
					nae.getCause();
				} catch (SameAccountTransferException sate) {
					sate.getCause();
				}
			} else {
				throw new UserNotActiveException();
			}
		} else {
			throw new UserNotFoundException();
		}
	}

	public void updateProfile(AccountHolder user) {
		System.out.print("Enter Updated Name: ");
		String updateName = getScanner().nextLine();
		System.out.println(managerService.updateAccount(updateName, user));
	}

	public void closeAccount(AccountHolder user) {
		user.setActive(false);
		System.out.println(Constants.ACCOUNT_DISABLE);
	}

	public Scanner getScanner() {
		return new Scanner(System.in);
	}
}