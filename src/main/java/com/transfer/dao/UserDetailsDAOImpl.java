package com.transfer.dao;

import java.math.BigDecimal;
import java.util.HashMap;

import com.transfer.model.UserAccountDetails;

public class UserDetailsDAOImpl implements UserDetailsDAO{
	
	private static HashMap<Integer,UserAccountDetails> users;

	public UserDetailsDAOImpl()
	{
		if(users == null)
		{
			users = new HashMap<Integer,UserAccountDetails>();
			createMockUsers();
		}
	}
	
	@Override
	public UserAccountDetails getUserDetails(Integer accountNumber) {		
		return users.get(accountNumber);
	}
	
	public void updateUser(UserAccountDetails userAccountDetails)
	{
		users.put(userAccountDetails.getAccountNumber(), userAccountDetails);
	}

	
	public void createMockUsers()
	{
		users.put(12345678,new UserAccountDetails(new StringBuffer("OMKUDUM"),12345678,new StringBuffer("804630"),new BigDecimal ("1000")));
    	users.put(87654321, new UserAccountDetails(new StringBuffer("BHAKTIKUDUM"),87654321,new StringBuffer("909090"),new BigDecimal ("100")));
	}
}
