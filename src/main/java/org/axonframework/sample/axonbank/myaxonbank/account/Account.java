/*
 * Copyright (c) 2017 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of General Electric Company.
 * The software may be used and/or copied only with the written permission of
 * General Electric Company or in accordance with the terms and conditions stipulated in the
 * agreement/contract under which the software has been supplied.
 */

package org.axonframework.sample.axonbank.myaxonbank.account;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.sample.axonbank.myaxonbank.coreapi.AccountCreatedEvent;
import org.axonframework.sample.axonbank.myaxonbank.coreapi.CreateAccountCommand;
import org.axonframework.sample.axonbank.myaxonbank.coreapi.MoneyWithdrawnEvent;
import org.axonframework.sample.axonbank.myaxonbank.coreapi.WithdrawMoneyCommand;

import lombok.NoArgsConstructor;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@NoArgsConstructor
public class Account {

  @AggregateIdentifier
  private String accountId;
  private int balance;
  private int overdraftLimit;

  @CommandHandler
  public Account(CreateAccountCommand createAccountCommand) {
    apply(new AccountCreatedEvent(createAccountCommand.getId(),
        createAccountCommand.getOverdraftLimit()));
    System.out.println("Account Create Command");
  }

  @EventSourcingHandler
  public void on(AccountCreatedEvent accountCreatedEvent) {
    this.accountId = accountCreatedEvent.getAccountId();
    this.overdraftLimit = accountCreatedEvent.getOverdraftLimit();
    System.out.println("Account Created Event");
  }

  @CommandHandler
  public void handle(WithdrawMoneyCommand withdrawMoneyCommand)
      throws OverdraftLimitExceededException {
    System.out.println("Withdraw Money Command");
    if (balance + overdraftLimit >= withdrawMoneyCommand.getAmount()) {
      apply(new MoneyWithdrawnEvent(accountId, withdrawMoneyCommand.getAmount(),
          balance - withdrawMoneyCommand.getAmount()));
    } else {
      throw new OverdraftLimitExceededException();
    }
  }

  @EventSourcingHandler
  public void on(MoneyWithdrawnEvent moneyWithdrawnEvent) {
    System.out.println("Money Withdrawn Event");
    this.balance = moneyWithdrawnEvent.getBalance();
  }
}
