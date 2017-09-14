/*
 * Copyright (c) 2017 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of General Electric Company.
 * The software may be used and/or copied only with the written permission of
 * General Electric Company or in accordance with the terms and conditions stipulated in the
 * agreement/contract under which the software has been supplied.
 */

package org.axonframework.sample.axonbank.myaxonbank.receiver;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.sample.axonbank.myaxonbank.coreapi.AccountCreatedEvent;
import org.axonframework.sample.axonbank.myaxonbank.coreapi.MoneyWithdrawnEvent;
import org.springframework.stereotype.Component;

@Component
public class EventListener {

  @EventHandler
  public void on(AccountCreatedEvent accountCreatedEvent) {
    System.out.println("In Account Created Listener" + accountCreatedEvent);
  }

  @EventHandler
  public void on(MoneyWithdrawnEvent withdrawnEvent) {
    System.out.println("In Account Created Listener" + withdrawnEvent);
  }
}
