package com.hmel.central;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLogger {
  
  private static final Logger logger = LoggerFactory.getLogger(TestLogger.class);
  
  public static void main(String[] args) {
    System.out.println("Start");
    logger.info("Hello {} ","Sasha");
    logger.error("Hello {} ","Sasha");
    logger.debug("Hello {} ","Sasha");
    logger.warn("Hello {} ","Sasha");
    System.out.println("Stop");
    
  }

}
