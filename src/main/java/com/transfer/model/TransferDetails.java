package com.transfer.model;

public class TransferDetails {

    private UserAccountDetails userAccountFrom;
   	public void setUserAccountFrom(UserAccountDetails userAccountFrom) {
		this.userAccountFrom = userAccountFrom;
	}


	public void setUserAccountTo(UserAccountDetails userAccountTo) {
		this.userAccountTo = userAccountTo;
	}


	public void setMessage(String message) {
		this.message = message;
	}

	private UserAccountDetails userAccountTo;
    private String message;
    
    public TransferDetails()
    {
    	
    }

    public TransferDetails(UserAccountDetails userAccountFrom, UserAccountDetails userAccountTo, String message) {
        this.userAccountFrom = userAccountFrom;
        this.userAccountTo = userAccountTo;
        this.message = message;
        
    }
    
    
    public UserAccountDetails getUserAccountFrom() {
		return userAccountFrom;
	}

	public UserAccountDetails getUserAccountTo() {
		return userAccountTo;
	}

	public String getMessage() {
		return message;
	}


   
}
