package com.banking.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Util {
	public static String fileWrite(String fileContent) {
		File file = new File("D:\\Study Material\\Programs\\Java\\Banking\\files\\transaction.txt");
		String content = fileContent;

		try (FileOutputStream outputStream = new FileOutputStream(file)) {
			byte[] strToBytes = content.getBytes();
			outputStream.write(strToBytes);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file.getAbsolutePath();
	}

	public static String nineDigitSpaceManager(float value) {
		String data = value + "";
		return nineDigitSpaceManager(data);
	}

	public static String nineDigitSpaceManager(String data) {
		int valueLength = data.length();
		int spaceCount = 9 - valueLength;
		System.out.println("Size before: "+valueLength);
		System.out.println("SpaceRequired: "+spaceCount);

		StringBuilder nineDigitString = new StringBuilder();
		nineDigitString.append(data);

		for (; spaceCount > 0; --spaceCount) {
			nineDigitString.append(" ");
		}
		return nineDigitString.toString();

	}
}