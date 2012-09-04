import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GatherDups {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Application to read in a file
		// that contains two columns.
		// One column needs to be unique
		// Output all the values of column 2
		// in a ';' separated list
		
		// Example:
		// <input>
		// test1@example.com	John Smith
		// test1@example.com	Joan Smith
		
		// <output>
		// test1@example.com	John Smith;Joan Smith
		
		BufferedReader brInput;
		HashMap<String, String> hmUnique;
		ArrayList<String> alDups;
		String filename = null;
		String sLine = null;
		String[] aLine = new String[2];
		String val0 = null;
		String val1 = null;

		filename = args[0];
		
		try {
			brInput = new BufferedReader(new FileReader(filename));
			hmUnique = new HashMap<String, String>();
			alDups = new ArrayList<String>();

			while(( sLine = brInput.readLine()) != null){
				//Split the line into two columns
				aLine = sLine.split("\t");
				val0 = aLine[0];
				val1 = aLine[1];

				if (hmUnique.containsKey(val0)){
					String existingValues = hmUnique.get(val0);
					existingValues += ";" + val1;

					hmUnique.remove(val0);
					hmUnique.put(val0, existingValues);
						
					if (!(alDups.contains(val0))){
						alDups.add(val0);
					}
				}else{
					hmUnique.put(val0, val1);
					alDups.add(val0);
				}
			}
			
			Iterator<String> iList = alDups.iterator();
			
			while (iList.hasNext()){
				String key = iList.next();
				System.out.println(key + "\t" + hmUnique.get(key));
			}
			
			brInput.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
