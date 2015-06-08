package Tools;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**Useful methods present in project*/
public class Tools {

	/**This method is used for loading map from file*/
	public static String loadFileAsString(String path) {
		StringBuilder builder = new StringBuilder();
		
		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
			String line;
			
			while((line = bufferedReader.readLine()) != null)
				builder.append(line + "\n");
			
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	public static int parseInt(String number) {
		try {
			return Integer.parseInt(number);
		} catch(NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
