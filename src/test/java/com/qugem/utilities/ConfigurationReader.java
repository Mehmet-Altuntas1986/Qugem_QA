package com.qugem.utilities;
/*
 * this method reads the the properties file to use credentials with definitions
 * simple key value structure
 *
 * */

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {

  private static Properties properties; // Properties object creation

  static {
    try {
      String path = "configuration.properties"; // data location
      FileInputStream input = new FileInputStream(path);
      properties = new Properties(); //
      properties.load(input);

      input.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static String get(String keyName) {
    return properties.getProperty(keyName);

  }

  public static void main(String[] args) {
    properties.setProperty("test","dasda");
  }
}
