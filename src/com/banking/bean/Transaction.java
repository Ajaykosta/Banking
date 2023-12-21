package com.banking.bean;

import java.util.Date;

import com.banking.utility.DateUtil;

public class Transaction {
	private String transactionType;
	private String transactionMode;
	private float amount;
	private float transactionCharge;
	private float totalAmount;
	private String description;
	private String date;

	public Transaction(String transactionType, String transactionMode, float amount, float transactionCharge,
			float totalAmount, String description) {
		super();
		this.transactionType = transactionType;
		this.transactionMode = transactionMode;
		this.amount = amount;
		this.transactionCharge = transactionCharge;
		this.totalAmount = totalAmount;
		this.description = description;
		this.date = DateUtil.convertDateToddMMyy(new Date());
	}

	public String getDate() {
		return date;
	}

	public float getTransactionCharge() {
		return transactionCharge;
	}


	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionMode() {
		return transactionMode;
	}

	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Transaction [Date =" + date + "Transaction Type=" + transactionType + ", Transaction Mode="
				+ transactionMode + ", Amount=" + amount + ", Transaction Charge=" + transactionCharge
				+ ", Total Amount=" + totalAmount + ", Description=" + description + "]";
	}
}