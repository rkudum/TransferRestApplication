package com.transfer.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAccountDetails {
	
	private StringBuffer name;
	private int accountNumber;
	private StringBuffer sortCode;
	private BigDecimal balance;	
	
    public UserAccountDetails()
    {
    	
    }

	public UserAccountDetails(StringBuffer name,int accountNumber,StringBuffer sortCode,BigDecimal balance)
	{
		this.name= name;
		this.accountNumber=accountNumber;
		this.sortCode = sortCode;
		this.balance = balance;
	}
	
	
	public StringBuffer getName() {
		return name;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public StringBuffer getSortCode() {
		return sortCode;
	}

	public BigDecimal getBalance() {
		return balance;
	}
	
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	

}
