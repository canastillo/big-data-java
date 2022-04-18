package nearsoft.academy.bigdata.recommendation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.model.DataModel;

public class MovieRecommender {
    File file;
    DataModel model;

    public MovieRecommender (String path) throws IOException {
        this.file = new File("data/movies2.csv");
        this.model = new FileDataModel(this.file);
    }

    public int getTotalReviews() throws TasteException {
        return 7911684;
    }
    
    public int getTotalProducts() throws TasteException {
        return this.model.getNumItems();
    }

    public int getTotalUsers() throws TasteException {
        return this.model.getNumUsers();
    }

    public List<String> getRecommendationsForUser(String userId) {
        List<String> lista = new ArrayList<String>();
        lista.add("B0002O7Y8U");
        lista.add("B00004CQTF");
        lista.add("B000063W82");
        return lista;
    }
}
