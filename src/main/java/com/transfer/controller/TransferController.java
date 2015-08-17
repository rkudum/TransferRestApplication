package com.transfer.controller;

import java.math.BigDecimal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.transfer.dao.UserDetailsDAOImpl;
import com.transfer.model.TransferDetails;
import com.transfer.model.UserAccountDetails;

@RestController
public class TransferController {

	private static final String template = "TRANSFER WAS %s!";
	private static String message;

	@RequestMapping("/transfer")
	public @ResponseBody TransferDetails Transfer(@RequestParam Integer accountFrom, @RequestParam Integer accountTo,
			@RequestParam BigDecimal moneytoTransfer) throws InterruptedException {

		UserDetailsDAOImpl userDetailsDAO = new UserDetailsDAOImpl();
		UserAccountDetails userAccountToDetails = null;
		UserAccountDetails userAccountFromDetails = userDetailsDAO.getUserDetails(accountFrom);

		if (userAccountFromDetails == null) {
			message = "UNSUCCESSFUL Due to Invalid Account";
		} else {
			// Check if there is enough money in the source account to transfer
			// the required amount
			if (userAccountFromDetails.getBalance().compareTo(moneytoTransfer) == -1) {
				message = "UNSUCCESSFUL Due to Insufficent Balance";
			} else {
				userAccountToDetails = userDetailsDAO.getUserDetails(accountTo);
				if (userAccountToDetails != null) {

					userAccountFromDetails.setBalance(userAccountFromDetails.getBalance().subtract(moneytoTransfer));
					userAccountToDetails.setBalance(userAccountToDetails.getBalance().add(moneytoTransfer));
					userDetailsDAO.updateUser(userAccountFromDetails);
					userDetailsDAO.updateUser(userAccountToDetails);
					message = "SUCCESSFULLY Transfered money to " + userAccountToDetails.getName();
				} else {
					message = "UNSUCCESSFUL Due to Invalid Account";
				}

			}
		}

		return new TransferDetails(userAccountFromDetails, userAccountToDetails, String.format(template, message));
	}
}
