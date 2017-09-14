/*
 * Copyright (c) 2017 General Electric Company. All rights reserved.
 *
 * The copyright to the computer software herein is the property of General Electric Company.
 * The software may be used and/or copied only with the written permission of
 * General Electric Company or in accordance with the terms and conditions stipulated in the
 * agreement/contract under which the software has been supplied.
 */

package org.axonframework.sample.axonbank.myaxonbank;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.sample.axonbank.myaxonbank.coreapi.CreateAccountCommand;
import org.axonframework.sample.axonbank.myaxonbank.coreapi.WithdrawMoneyCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.axonframework.commandhandling.GenericCommandMessage.asCommandMessage;

@RestController
public class MyController {

  @Autowired
  private CommandBus commandBus;

  @RequestMapping(value = "/hello")
  public String say() {
    System.out.println("Command Bus>>>>" + commandBus);
    commandBus.dispatch(asCommandMessage(new CreateAccountCommand("1234", 1000)));
    commandBus.dispatch(asCommandMessage(new WithdrawMoneyCommand("1234", 800)));
    return "Hello!";

  }
}
