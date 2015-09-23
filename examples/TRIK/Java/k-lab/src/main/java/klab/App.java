package klab;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * K-Lab Trik demo project!
 *
 */
public class App {
  // I2C command thread.
  public static Thread ct;

  public static String lineSensor1;
  public static String lineSensor2;

  public static void main(String[] args) throws IOException, InterruptedException {
    init();
    while (true) {
      loop();
    }
  }

  public static void loop() throws IOException, InterruptedException {
    System.out.println("Line sensor 1: " + lineSensor1);
    System.out.println("Line sensor 2: " + lineSensor2);
    Thread.sleep(1000);
  }

  public static void init() throws IOException, InterruptedException {
    // Command thread.
    ct = new Thread() {
      // I2C command numbers for analog sensors.
      final int A1 = 0x25;
      final int A2 = 0x24;
      final int A3 = 0x23;
      final int A4 = 0x24;
      final int A5 = 0x21;
      final int A6 = 0x20;

      // I2C command numbers for motors.
      final int M1 = 0x14;
      final int M2 = 0x15;
      final int M3 = 0x16;
      final int M4 = 0x17;

      // I2C command numbers for encoders.
      final int B1 = 0x30;
      final int B2 = 0x31;
      final int B3 = 0x32;
      final int B4 = 0x33;

      public void run() {
        while (true) {
          try {
             // Read all sensors.
             lineSensor1 = getValue(A5);
             lineSensor2 = getValue(A6);
          }
          catch (IOException e) {
            System.out.println("IO Error");
          }
          catch (InterruptedException e) {
            System.out.println("InterruptedException");
          }
        }
      }
    };
    ct.start();
  }

/*  public static  void testI2cGetEncoderTime() {
    setMotorValue(0x14, 20);

    System.out.println("Started");
    long i = 0;
    while (i++ < 100000) {
      String enc = getValue(0x30);
      System.out.println("Value: " + enc);
    }
  }

  public static void testI2cGetAnalogTime() {
    System.out.println("Started");
    long i = 0;
    long tt = 0;
    while (i++ < 100000) {
      long t = System.currentTimeMillis();

      if (i % 20 == 0) {
        setMotorValue(0x14, 100);
      }
      readValues();
      t = System.currentTimeMillis() - t;
      tt += t;
      System.out.println("Time: " + t + "ms");
      System.out.println("Full time: " + ((double)tt)/i + "ms");
    }
  }*/

  public static void readValues() throws IOException, InterruptedException {
    String a5 = getValue(0x21);
    String a6 = getValue(0x20);
  }

  private static void androidControl(Socket socket) throws IOException, InterruptedException {
    try {
    final InputStream in = socket.getInputStream();
    while (socket.isConnected()) {
      if (in.available() > 0) {
        int command = in.read();
        System.out.println("Command: " + command);
        switch (command) {
          case 0:
            setMotorValue(0x14, 100);
            setMotorValue(0x15, 100);
            break;
          case 1:
            setMotorValue(0x14, 100);
            setMotorValue(0x15, -100);
            break;
          case 2:
            setMotorValue(0x14, -100);
            setMotorValue(0x15, 100);
            break;
          case 3:
            setMotorValue(0x14, -100);
            setMotorValue(0x15, -100);
            break;
          case 4:
            setMotorValue(0x14, 0);
            setMotorValue(0x15, 0);
            break;
          case 5:
            return;
        }
      }
    }

    } finally {
      socket.close();
    }
  }

  private static void setMotorValue(int motor, int value) throws IOException {
    if (value >= 0) {
      //System.out.println("i2cset -y 2 0x48 0x" + Integer.toHexString(motor) + " 0x" + Integer.toHexString(value) + " w");
      Runtime.getRuntime().exec("i2cset -y 2 0x48 0x" + Integer.toHexString(motor) + " 0x" + Integer.toHexString(value) + " w");
    } else {
      String hex = Integer.toHexString(value);
      hex = hex.substring(hex.length()-2, hex.length());
      //System.out.println("i2cset -y 2 0x48 0x" + Integer.toHexString(motor) + " 0x" + Integer.toHexString(value) + " w");
      Runtime.getRuntime().exec("i2cset -y 2 0x48 0x" + Integer.toHexString(motor) + " 0x" + hex + " w");

    }
  }

  private static String getValue(int port) throws IOException, InterruptedException {
    Process i2cget = Runtime.getRuntime().exec("i2cget -y 2 0x48 0x" + Integer.toHexString(port));
    InputStream in = i2cget.getInputStream();
    byte[] buf = new byte[8];
    int size = 0;

    while (in.available() < 5) {
    }
    size = i2cget.getInputStream().read(buf);

    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < size; i++) {
      builder.append((char) buf[i]);
    }
    return "size: " + size + "value: " + builder.toString();
  }

}
