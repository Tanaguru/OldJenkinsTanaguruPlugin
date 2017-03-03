/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.tanaguru.rest.util;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author tanaguru
 */
public class UtilityCall {
    
  
	

public static  String getCode() {
  String chars ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ#&@()[]{}!?-_+1234567890";
  int length  =19;
  
  Random rand = new Random();
  StringBuilder sb = new StringBuilder();
  for (int i=0; i<length; i++) {
    sb.append(chars.charAt(rand.nextInt(chars.length())));
  }
  return sb.toString();
}

    
}
