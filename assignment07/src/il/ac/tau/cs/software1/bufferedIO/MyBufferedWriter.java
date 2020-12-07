package il.ac.tau.cs.software1.bufferedIO;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;


/**************************************
 *  Add your code to this class !!!   *
 **************************************/

public class MyBufferedWriter implements IBufferedWriter{
	private int bufferSize;
	private FileWriter fWriter;
	private char[] charsToWrite = new char[0];
	
	public MyBufferedWriter(FileWriter fWriter, int bufferSize){
		this.bufferSize = bufferSize;
		this.fWriter = fWriter;
	}

	
	@Override
	public void write(String str) throws IOException {
		char[] currChars = concat(this.charsToWrite, str.toCharArray());
		int timesToWrite = currChars.length % bufferSize;
		while (currChars.length >= bufferSize) {
			fWriter.write(Arrays.copyOfRange(currChars, 0, bufferSize));
			currChars = Arrays.copyOfRange(currChars, bufferSize, currChars.length);
			
		}
		this.charsToWrite = currChars;
	}
	
	@Override
	public void close() throws IOException {
		if (charsToWrite.length > 0) {
			fWriter.write(charsToWrite);
		}
		fWriter.close();
	}
	
	public static char[] concat(char[] first, char[] second) {
		char[] result = Arrays.copyOf(first, first.length + second.length);
		System.arraycopy(second, 0, result, first.length, second.length);
		return result;
	}
}