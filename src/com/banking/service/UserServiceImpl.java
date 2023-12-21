package com.banking.service;

import com.banking.bean.AccountHolder;
import com.banking.bean.Transaction;
import com.banking.exceptions.InsufficientBalanceException;
import com.banking.exceptions.NegativeAmountException;
import com.banking.exceptions.SameAccountTransferException;
import com.banking.utility.Constants;
import com.banking.utility.Constants.transactionType;
import com.banking.utility.Util;

public class UserServiceImpl implements UserService {
	ManagerService managerService = new ManagerServiceImpl();

	public float withdrawMoney(float amount, AccountHolder user)
			throws InsufficientBalanceException, NegativeAmountException {
		if (amount < 0) {
			throw new NegativeAmountException();
		}
		if (user.getBalance() < amount) {
			throw new InsufficientBalanceException();
		} else {
			user.setBalance(user.getBalance() - amount);
			updateTransaction(user, amount, transactionType.Debit.toString(), "Withdraw", null, 0);
			return user.getBalance();
		}
	}

	public float depositMoney(float amount, AccountHolder user) throws NegativeAmountException {
		if (amount < 0) {
			throw new NegativeAmountException();
		}
		user.setBalance(user.getBalance() + amount);
		updateTransaction(user, amount, transactionType.Credit.toString(), "Deposit", null, 0);
		return user.getBalance();
	}

	public String transferMoney(AccountHolder fromUser, AccountHolder toUser, int amount, String description)
			throws InsufficientBalanceException, NegativeAmountException, SameAccountTransferException {
		if (amount < 0) {
			throw new NegativeAmountException();
		} else if (fromUser.getAccountNumber() == toUser.getAccountNumber()) {
			throw new SameAccountTransferException();
		} else if (fromUser.getIfscCode().equals(toUser.getIfscCode())) {
			if (fromUser.getBalance() < amount) {
				throw new InsufficientBalanceException();
			} else {
				fromUser.setBalance(fromUser.getBalance() - amount);
				toUser.setBalance(toUser.getBalance() + amount);
				updateTransaction(toUser, amount, transactionType.Credit.toString(), "Transfer",
						fromUser.getAccountNumber() + "-" + description, 0);
				updateTransaction(fromUser, amount, transactionType.Debit.toString(), "Transfer",
						toUser.getAccountNumber() + "-" + description, 0);
			}
		} else {
			if (fromUser.getBalance() < (amount + managerService.getTransactionCharge())) {
				throw new InsufficientBalanceException();
			} else {
				fromUser.setBalance(fromUser.getBalance() - amount - managerService.getTransactionCharge());
				toUser.setBalance(toUser.getBalance() + amount);
				updateTransaction(toUser, amount, transactionType.Credit.toString(), "Transfer",
						fromUser.getAccountNumber() + "-" + description, 0);
				updateTransaction(fromUser, amount, transactionType.Debit.toString(), "Transfer",
						toUser.getAccountNumber() + "-" + description, managerService.getTransactionCharge());
			}
		}
		return Constants.MONEY_TRSNSFER;
	}

	public void transactionHistory(AccountHolder user) {
		StringBuilder trxs = new StringBuilder();
		trxs.append("-------------------------------------------------------\n");
		trxs.append("Dates       |" + Util.nineDigitSpaceManager("Debit") + "|" + Util.nineDigitSpaceManager("Credit")
				+ "|" + Util.nineDigitSpaceManager("Balance") + "|Description\n");
		trxs.append("-------------------------------------------------------\n");
		for (Transaction trx : user.getTransactions()) {
			trxs.append(trx.getDate() + "  |");
			// fixed if type is debit
			trxs.append(trx.getTransactionType().equals("Debit") ? "" : "         |");
			// replaced blank credit amount with space
			// trxs.append(trx.getTransactionType().equals("Debit")?trx.getAmount():"\t " );
			trxs.append(Util.nineDigitSpaceManager(trx.getAmount()));
			trxs.append(trx.getTransactionType().equals("Debit") ? "|         " : "");
			trxs.append("|" + Util.nineDigitSpaceManager(trx.getTotalAmount()) + "");
			trxs.append("|" + trx.getTransactionMode());
			trxs.append(trx.getDescription() == null ? "" : "-");
			trxs.append("\n");
		}
		trxs.append("-------------------------------------------------------\n");
		String filePath = Util.fileWrite(trxs.toString());
		System.out.println("the file successfully created at: " + filePath);
		System.out.println(trxs.toString());
	}

	public void Statement(AccountHolder user) {
		for (int i = 0; i < user.getTransactions().size(); i++) {
			System.out.println(user.getTransactions().get(i));
		}
	}

	public float accruedInterest(AccountHolder user) {
		float sum = 0;
		for (int i = 0; i < user.getTransactions().size(); i++) {
			if (user.getTransactions().get(i).getTransactionMode().equalsIgnoreCase("Interest")) {
				sum = sum + user.getTransactions().get(i).getAmount();
			}
		}
		return sum;
	}

	private void updateTransaction(AccountHolder user, float amount, String transactionType, String transactionMode,
			String description, float transactionCharges) {
		Transaction transaction = null;
		transaction = new Transaction(transactionType, transactionMode, amount, transactionCharges, user.getBalance(),
				description);
		System.out.println("balance ====> " + user.getBalance());
		user.getTransactions().add(transaction);
	}
}