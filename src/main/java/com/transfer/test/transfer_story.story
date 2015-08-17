Scenario: Transfer money from Account A to Account B
Given two valid accounts
When Transfer money from Account A to Account B
Then Balance of Account A is current balance minus the money transfered and Balance of Account B is current Balance plus money to be transfered


Scenario: Transfer money from Account B to Account A
Given two valid accounts
When Transfer money from Account B to Account A
Then Balance of Account B is current balance minus the money transfered and Balance of Account A is current Balance plus money to be transfered


Scenario: Transfer money from Account A to Account B is unsuccessful because of insufficient balance.
Given two valid accounts
When Transfer money from Account A to Account B with insufficient balance
Then A Message stating insufficient balance should be returned


Scenario: Transfer money from Account A to Account B where account A is invalid.
Given two accounts
When Transfer money from Account A to Account B where account A is invalid
Then A Message stating invalid account message is returned

Scenario: Transfer money from Account A to Account B where account B is invalid.
Given two accounts
When Transfer money from Account A to Account B where account B is invalid
Then A Message stating invalid account message is returned




