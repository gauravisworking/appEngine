package com.test.helloWorld.webservice;

import com.google.sitebricks.At;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.headless.Service;
import com.google.sitebricks.http.Get;

@At("/")
@Service
public class HelloWorld {

  @Get
  public Reply<String> getMessage() {
      return Reply.with("Hello World!!!!");
  }
}