package com.transfer.test;



import java.math.BigDecimal;
import java.math.RoundingMode;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import com.transfer.model.TransferDetails;

public class TransferSteps {
	
	private static final Logger log = LoggerFactory.getLogger(TransferSteps.class);
	RestTemplate restTemplate = new RestTemplate();;
	TransferDetails transferdetails;	
	
		
	
	@Given("two valid accounts")
    public void validAccounts() {		
		log.info("==== Given Two Valid Accounts ========");
	}
     
	
	@Given("two accounts")
    public void twoAccounts() {		
		log.info("==== Given Two Accounts ========");
	}
	
    @When("Transfer money from Account A to Account B")
    public void transferMoneyFromA2B() 
    {
         transferdetails = restTemplate.getForObject("http://localhost:8080/transfer?accountFrom=12345678&accountTo=87654321&moneytoTransfer=22.50",TransferDetails.class);
    }
     
    @Then("Balance of Account A is current balance minus the money transfered and Balance of Account B is current Balance plus money to be transfered")
    public void checkBankBalncesAandB() 
    {    	
    	Assert.assertEquals(transferdetails.getUserAccountFrom().getBalance().setScale(2, RoundingMode.HALF_UP), new BigDecimal(977.50).setScale(2, RoundingMode.HALF_UP));
    	Assert.assertEquals(transferdetails.getUserAccountTo().getBalance().setScale(2, RoundingMode.HALF_UP), new BigDecimal(122.50).setScale(2, RoundingMode.HALF_UP));
    }
    
    
    @When("Transfer money from Account B to Account A")
    public void transferMoneyFromB2A() 
    {
         transferdetails = restTemplate.getForObject("http://localhost:8080/transfer?accountFrom=87654321&accountTo=12345678&moneytoTransfer=22.50",TransferDetails.class);
    }
     
    @Then("Balance of Account B is current balance minus the money transfered and Balance of Account A is current Balance plus money to be transfered")
    public void checkBankBalncesBandA() 
    {    	
    	Assert.assertEquals(transferdetails.getUserAccountFrom().getBalance().setScale(2, RoundingMode.HALF_UP), new BigDecimal(100.00).setScale(2, RoundingMode.HALF_UP));
    	Assert.assertEquals(transferdetails.getUserAccountTo().getBalance().setScale(2, RoundingMode.HALF_UP), new BigDecimal(1000.00).setScale(2, RoundingMode.HALF_UP));
    }
    
    
    @When("Transfer money from Account A to Account B with insufficient balance")
    public void transferMoneyFromA2BWithSufficentBalance() 
    {
         transferdetails = restTemplate.getForObject("http://localhost:8080/transfer?accountFrom=12345678&accountTo=87654321&moneytoTransfer=1222.50",TransferDetails.class);
    }
     
    @Then("A Message stating insufficient balance should be returned")
    public void checkInsuffientBalanceMessage() 
    {    	
    	Assert.assertEquals(transferdetails.getMessage(), "TRANSFER WAS UNSUCCESSFUL Due to Insufficent Balance!");
    }
    
    @When("Transfer money from Account A to Account B where account A is invalid")
    public void transferMoneyFromA2BWithINvalidAccountdeails() 
    {
         transferdetails = restTemplate.getForObject("http://localhost:8080/transfer?accountFrom=33445566&accountTo=87654321&moneytoTransfer=1222.50",TransferDetails.class);
    }
    
    @When("Transfer money from Account A to Account B where account B is invalid")
    public void transferMoneyFromA2BWithINvalidAccountdeailsB() 
    {
         transferdetails = restTemplate.getForObject("http://localhost:8080/transfer?accountFrom=12345678&accountTo=99999999&moneytoTransfer=12.50",TransferDetails.class);
    }
     
    @Then("A Message stating invalid account message is returned")
    public void checkInvalidAccountBalanceMessage() 
    {    	
    	Assert.assertEquals(transferdetails.getMessage(), "TRANSFER WAS UNSUCCESSFUL Due to Invalid Account!");
    }
    
     
    
 

}
