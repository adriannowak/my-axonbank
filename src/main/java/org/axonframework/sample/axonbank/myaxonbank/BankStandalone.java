/*
 * Copyright (c) 2017 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of General Electric Company.
 * The software may be used and/or copied only with the written permission of
 * General Electric Company or in accordance with the terms and conditions stipulated in the
 * agreement/contract under which the software has been supplied.
 */

package org.axonframework.sample.axonbank.myaxonbank;

import static org.axonframework.commandhandling.GenericCommandMessage.asCommandMessage;

import org.axonframework.config.Configuration;
import org.axonframework.config.DefaultConfigurer;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.sample.axonbank.myaxonbank.account.Account;
import org.axonframework.sample.axonbank.myaxonbank.coreapi.CreateAccountCommand;

public class BankStandalone {

  public static void main(String[] args) {
    Configuration config =
        DefaultConfigurer.defaultConfiguration().configureEventBus(e -> new SimpleEventBus())
            .configureAggregate(Account.class).buildConfiguration();
    config.start();

    config.commandBus().dispatch(asCommandMessage(new CreateAccountCommand("123", 1000)));
  }
}
