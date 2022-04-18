package nearsoft.academy.bigdata.recommendation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CSVGenerator {
    DataAccess dataAccess;
    BufferedReader br;
    FileReader fr;
    File file;
    String st;

    public CSVGenerator(DataAccess dataAccess) throws FileNotFoundException {
        this.dataAccess = dataAccess;
        this.file = new File("data/movies.txt");
        this.br = new BufferedReader(new FileReader(file));
    }

    public void generate() throws IOException {
        PrintWriter csv = new PrintWriter(new BufferedWriter(new FileWriter("data/numeric.csv")));
        List<String> row = new ArrayList<String>();
        String csvLine = "";

        int userCount = 0;
        int productCount = 0;
        boolean wasCreated = false;

        while ((this.st = this.br.readLine()) != null) {
            Pattern pattern = Pattern.compile("review/userId|product/productId|review/score");    
            Matcher matcher = pattern.matcher(st);  

            if (matcher.find()) {
                String data = st.split(": ")[1];

                if (matcher.group().equals("review/userId")) {
                    wasCreated = this.dataAccess.addUser(data, userCount);
                    if (wasCreated) userCount += 1;
                    row.add(this.dataAccess.usersHash.get(data).toString());
                }

                if (matcher.group().equals("product/productId")) {
                    wasCreated = this.dataAccess.addProduct(data, productCount);
                    if (wasCreated) productCount += 1;
                    row.add(this.dataAccess.productsHash.get(data).toString());
                }

                wasCreated = false;

                if ( matcher.group().equals("review/score") ) {
                    row.add(data);

                    csvLine = String.join(",", row);
                    csv.println(csvLine);

                    csvLine = "";
                    row.clear();
                }
            }
        }

        csv.close();
    }
}

