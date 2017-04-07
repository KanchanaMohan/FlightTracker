package Helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

import Constant.QueryConstants;

/**
 * 
 * Helper class for printing to console and output file
 *  
 * @author Kanchana Mohan
 */
public class PrintHelper {
	private final static Logger LOGGER = Logger.getLogger(PrintHelper.class.getName());
	private final static String CLASS_NAME = PrintHelper.class.getName();
	private static PrintHelper print = new PrintHelper();
	private boolean isCreate = true;

	private PrintHelper() {
		if (isCreate) {
			try {
				File log = new File(QueryConstants.OUTPUT_FILE_PATH);
				if (log.exists()) {
					FileWriter fileOut = new FileWriter(QueryConstants.OUTPUT_FILE_PATH);
					fileOut.write("");
					fileOut.write(QueryConstants.FILE_VERSION);
					System.out.println(QueryConstants.FILE_VERSION);
					fileOut.close();
				}

				isCreate = false;
			} catch (IOException e) {
				LOGGER.severe("IOException in" + CLASS_NAME + "class");
			}
		}

	}

	public static PrintHelper getInstance() {
		return print;
	}

	public static void createFile() {

	}

	public void output(String strOutput) {

		System.out.println(strOutput);
		String methodName = "output";
		try {
			File log = new File(QueryConstants.OUTPUT_FILE_PATH);
			if (!log.exists()) {

				log.createNewFile();
			}

			FileWriter fileWriter = new FileWriter(log, true);
			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.newLine();
			bufferedWriter.write(strOutput);
			bufferedWriter.close();
		} catch (IOException e) {
			LOGGER.severe("IOException in method" + methodName + "in" + CLASS_NAME + "class" + QueryConstants.OUTPUT_ERROR_MSG);

		}

	}
}
