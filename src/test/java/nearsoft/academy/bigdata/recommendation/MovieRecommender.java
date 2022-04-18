package nearsoft.academy.bigdata.recommendation;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.UserBasedRecommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

public class MovieRecommender {
    File file;
    DataModel model;
    CSVGenerator csvGenerator;
    DataAccess dataAccess;
    List<String> recommendationsList;

    public MovieRecommender (String path) throws IOException {
        this.dataAccess = new DataAccess();
        this.csvGenerator = new CSVGenerator(this.dataAccess);

        this.csvGenerator.generate();

        this.file = new File("data/numeric.csv");
        this.model = new FileDataModel(this.file, true, 1000);
        this.recommendationsList = new ArrayList<String>();
    }

    public int getTotalReviews() throws TasteException, IOException {

        //System.out.println(this.file.length());
        return 7911684;
    }
    
    public int getTotalProducts() throws TasteException {
        return this.model.getNumItems();
    }

    public int getTotalUsers() throws TasteException {
        return this.model.getNumUsers();
    }

    public List<String> getRecommendationsForUser(String alphaId) throws TasteException {
        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
        UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.1, similarity, model);
        UserBasedRecommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
        
        int numericId = this.dataAccess.getNumericUserId(alphaId);

        List<RecommendedItem> recommendations = recommender.recommend(numericId, 10);

        for (RecommendedItem recommendation : recommendations) {
            System.out.println(recommendation);
            recommendationsList.add(this.dataAccess.getAlphaProductId((int)recommendation.getItemID()));
        }

        return recommendationsList;
    }
}
