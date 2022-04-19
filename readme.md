# Big Data Exercises

This repo contains several common big data exercises.

* **MovieRecommender** Uses Amazon movie reviews sample data   [stanford.edu/data/web-Movies.html](http://snap.stanford.edu/data/web-Movies.html) for a simple movie recommender

    
 
 
## Setup

1. Install the  JDK 7.0
2. [Download & Install Maven](http://maven.apache.org/download.cgi)
3. Decompress the "movies.txt.gz" file from [stanford.edu/data/web-Movies.html](http://snap.stanford.edu/data/web-Movies.html)
4. Place the resulting "movies.txt" archive in a folder named "data" in the root directory of the project  

The structure should look like this:
```
- src
- data/
    - movies.txt
- target
```

## How to run tests

    #from the repository root
    mvn test
 
