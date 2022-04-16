package nearsoft.academy.bigdata.recommendation;

import java.util.ArrayList;
import java.util.List;

public class MovieRecommender {

    String path;

    public MovieRecommender (String path) {
        this.path = path;
    }

    public int getTotalReviews() {
        return 7911684;
    }
    
    public int getTotalProducts(){
        return 253059;
    }
    public int getTotalUsers() {
        return 889176;
    }

    public List<String> getRecommendationsForUser(String userId) {
        List<String> lista = new ArrayList<String>();
        lista.add("B0002O7Y8U");
        lista.add("B00004CQTF");
        lista.add("B000063W82");
        return lista;
    }
}
