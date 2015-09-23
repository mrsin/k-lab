package triktest1;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AVL
 */
public class TrikTest1 {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		
	}

	//включить двигатель
	public static void engineState(int value) {
		if (value < -100){
			value = -100;
		}
		if (value > 100) {
			value = 100;
		}
		String hexStr = Integer.toHexString(value);
		if (hexStr.length() > 2){
			hexStr = hexStr.substring(hexStr.length() - 2);
		}
		System.out.print (hexStr);
		System.out.print(" ");
		try {
			// 0x48 0x14 - один из этих параметров - это номер двигателя, не помню какой именно
			Runtime.getRuntime().exec("i2cset -y 2 0x48 0x14 0x" +hexStr + " w");
		} catch (IOException ex) {
			Logger.getLogger(TrikTest1.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	//чтение аналогового сигнала с датчика, i - ноимер от 1 до 4
	public static int getAnalog(int i) {
		String[] adresses = {"","0x25","0x24","0x23","0x22","0x21","0x20"};
		try {
			Process exec = Runtime.getRuntime().exec("i2cget -y 2 0x48 " + adresses[i]);
			InputStream in = exec.getInputStream();
			InputStreamReader reader = new InputStreamReader(in);
			char[] data = new char[2];
			reader.read(data);
			reader.read(data);
			String hex = String.valueOf(data);
			exec.waitFor();
			return Integer.parseInt(hex, 16);
		} catch (IOException ex) {
			Logger.getLogger(TrikTest1.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InterruptedException ex) {
			Logger.getLogger(TrikTest1.class.getName()).log(Level.SEVERE, null, ex);
		}
		return -1;
	}
}
