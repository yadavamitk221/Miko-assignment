package micoAssignment;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class CreateTableFromQuery {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
//				Taking input query
		String Query = sc.nextLine();
		
//				Checking weather the input is Query is of create or insert;
		if(Query.charAt(0) == 'c' || Query.charAt(0) == 'C') {
			int i = 0;
			while(Query.charAt(i) != '(') {
				i++;
			}
			
//					sorting the query string to access the data;
			StringBuffer trimedQuerySB = new StringBuffer();
			for( ; i < Query.length(); i++) {
				trimedQuerySB.append(Query.charAt(i));
			} 
			
			String trimedQueryString = trimedQuerySB.toString();  
			
//					calling this method to create a header of the table
			
			createHeader(trimedQueryString);
			
		}else {
			int i = 0;
			while(Query.charAt(i) != '(') {
				i++;
			}
			
//					sorting the query string to access the data;
			StringBuffer trimedQuerySB = new StringBuffer();
			for( ; i < Query.length(); i++) {
				trimedQuerySB.append(Query.charAt(i));
			} 
			
			String trimedQueryString = trimedQuerySB.toString(); 
			
//					Calling this method to append the data inside already created table
			addValuesToTable(trimedQueryString);
		}
	}
	
	
	public static void addValuesToTable(String values) {
		String filePath = "C:\\Users\\amitkuya\\Desktop\\MicoAssignment\\Assignment1.txt";
        // New data to add to the table
        String[] newData = values.replaceAll("[();]", "").split(",");

        try {
            // Read the existing file
            FileReader fileReader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            StringBuilder tableData = new StringBuilder();
            String line;

            // Read the existing table data
            while ((line = bufferedReader.readLine()) != null) {
                tableData.append(line).append(System.lineSeparator());
            }

            bufferedReader.close();

            // Modify the table data by adding the new row
            String newRow = String.join("\t" + "|", newData);
            tableData.append(newRow).append(System.lineSeparator());

            // Write the updated table data back to the file
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(tableData.toString());
            fileWriter.close();

            System.out.println("Data added to the table successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
	}
	
	
	
	
	
//			Query to create the table header
	public static void createHeader(String trimedQueryString) {
	// Remove the parentheses and split the data by commas
		String[] trimedQuery = trimedQueryString.replaceAll("[();]", "").split(",");
		
		try {
			String filePath = "C:\\Users\\amitkuya\\Desktop\\MicoAssignment\\Assignment1.txt";
            FileWriter writer = new FileWriter(filePath);

            // Write table data to the file
                for (String cell : trimedQuery) {
                	cell = cell.trim();
                    writer.write(cell + "\t" + " | "); // Separate cells with a tab character
                }
   

            writer.close();
            System.out.println("Table created and written to the file successfully!");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
	}

}
