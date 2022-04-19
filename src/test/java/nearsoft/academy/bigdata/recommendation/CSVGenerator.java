package nearsoft.academy.bigdata.recommendation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVGenerator {
    private BufferedReader inputFile;
    private PrintWriter outputFile;
    private IndexStorage indexStorage;
    private int totalLines;

    public CSVGenerator(IndexStorage indexStorage) throws IOException {
        this.indexStorage = indexStorage;
        this.inputFile = new BufferedReader(new FileReader(new File("data/movies.txt")));
        this.outputFile = new PrintWriter(new BufferedWriter(new FileWriter("data/numeric.csv")));
        this.totalLines = 0;
    }

    public void generate() throws IOException {
        List<String> row = new ArrayList<String>();
        String currentLine;
        int userCount = 0;
        int productCount = 0;
        boolean isNewRegister = false;

        while ( (currentLine = this.inputFile.readLine()) != null ) {
            Pattern pattern = Pattern.compile("review/userId|product/productId|review/score");    
            Matcher matcher = pattern.matcher(currentLine);  

            if (matcher.find()) {
                String data = currentLine.split(": ")[1];
                String currentField = matcher.group();

                if ( currentField.equals("review/userId") ) {
                    isNewRegister = this.indexStorage.addUser(data, userCount);
                    if (isNewRegister) userCount += 1;

                    int numericId = this.indexStorage.getNumericUserId(data);
                    row.add(Integer.toString(numericId));
                }

                if ( currentField.equals("product/productId") ) {
                    isNewRegister = this.indexStorage.addProduct(data, productCount);
                    if (isNewRegister) productCount += 1;

                    int numericId = this.indexStorage.getNumericProductId(data);
                    row.add(Integer.toString(numericId));
                }

                if ( currentField.equals("review/score") ) {
                    row.add(data);
                    outputFile.println(String.join(",", row));
                    totalLines = totalLines + 1;
                    row.clear();
                }

                isNewRegister = false;
            }
        }

        outputFile.close();
    }

    public int getTotalLines() {
        return this.totalLines;
    }
}

