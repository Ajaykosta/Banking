package com.banking.exceptions;

import com.banking.utility.Constants;

public class AgeExceededException extends Exception{
	public AgeExceededException() {
		System.out.println(Constants.AGE_EXCEEDED);
	}
}
