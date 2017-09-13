/*
 * Copyright (c) 2017 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of General Electric Company.
 * The software may be used and/or copied only with the written permission of
 * General Electric Company or in accordance with the terms and conditions stipulated in the
 * agreement/contract under which the software has been supplied.
 */

package org.axonframework.sample.axonbank.myaxonbank.account;


import org.axonframework.sample.axonbank.myaxonbank.coreapi.AccountCreatedEvent;
import org.axonframework.sample.axonbank.myaxonbank.coreapi.CreateAccountCommand;
import org.axonframework.sample.axonbank.myaxonbank.coreapi.MoneyWithdrawnEvent;
import org.axonframework.sample.axonbank.myaxonbank.coreapi.WithdrawMoneyCommand;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;

public class AccountTest {

  private FixtureConfiguration<Account> fixture;

  @Before
  public void setup() throws Exception {
    fixture = new AggregateTestFixture<>(Account.class);
  }

  @Test
  public void createAccount() throws Exception {
    fixture.givenNoPriorActivity().when(new CreateAccountCommand("123", 1000))
        .expectEvents(new AccountCreatedEvent("123", 1000));
  }

  @Test
  public void withdrawReasonableAmount() {
    fixture.given(new AccountCreatedEvent("123", 1000)).when(new WithdrawMoneyCommand("123", 500))
        .expectEvents(new MoneyWithdrawnEvent("123", 500, -500));
  }

  @Test
  public void withdrawExcessAmount() {
    fixture.given(new AccountCreatedEvent("123", 1000)).when(new WithdrawMoneyCommand("123", 1001))
        .expectNoEvents().expectException(OverdraftLimitExceededException.class);
  }

  @Test
  public void withdrawTwice() {
    fixture.given(new AccountCreatedEvent("123", 1000), new MoneyWithdrawnEvent("123", 999, -1000))
        .when(new WithdrawMoneyCommand("123", 500)).expectNoEvents()
        .expectException(OverdraftLimitExceededException.class);
  }
}
