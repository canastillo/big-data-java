package nearsoft.academy.bigdata.recommendation;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class DataAccess {
    Hashtable<String,Integer> usersHash;
    Hashtable<String,Integer> productsHash;
    List<String> usersArray;
    List<String> productsArray;

    public DataAccess() {
        this.usersHash = new Hashtable<String,Integer>();
        this.usersArray = new ArrayList<String>();
        this.productsHash = new Hashtable<String,Integer>();
        this.productsArray = new ArrayList<String>();
    }

    public boolean addProduct(String data, int productCount) {
        if (this.productsHash.get(data) == null) {
            this.productsHash.put(data,productCount);
            this.productsArray.add(data);
            return true;
        }

        return false;
    }

    public boolean addUser(String data, int userCount) {
        if (this.usersHash.get(data) == null) {
            this.usersHash.put(data,userCount);
            this.usersArray.add(data);
            return true;
        }

        return false;
    }

    public int getNumericUserId(String alphaId) {
        return this.usersHash.get(alphaId);
    }

    public String getAlphaProductId(int numericId) {
        return this.productsArray.get(numericId);
    }

}
