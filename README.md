# 📢 Clickbait Detection using MapReduce

## 🎯 Aim
To analyze fake news headlines and identify commonly used clickbait words using Hadoop MapReduce in Java. The project focuses on text preprocessing, filtering fake headlines, counting word frequency, and visualizing results using Python.

---

## 🗂️ Project Structure

```text
ClickbaitDetection/
│
├── src/                        # Java source files
│   ├── FakeNewsFilter.java
│   ├── ClickbaitMapper.java
│   ├── ClickbaitReducer.java
│   ├── ClickbaitDriver.java
│
├── input/                      # Input dataset
│   └── fake_news_dataset.csv
│
├── output/                     # Output directory
│   └── output.txt              # Word frequency output
│
├── build/                      # Compiled .class files
│
├── ClickbaitDetection.jar      # Packaged JAR file
├── generate_graph.py           # Python file for bar graph visualization
├── README.md                   # Project documentation



---

## ⚙️ Steps to Run (Command Line)

```bash
# Step 1: Create project directories
mkdir -p ~/ClickbaitDetection/{src,input,output,build}
cd ~/ClickbaitDetection

# Step 2: Add input dataset to input/
# (Add fake_news_dataset.csv manually)

# Step 3: Compile Java files
javac -classpath `hadoop classpath` -d build src/*.java

# Step 4: Package into JAR
jar -cvf ClickbaitDetection.jar -C build/ .

# Step 5: Run Java filter to clean fake news headlines
java -cp build FakeNewsFilter input/fake_news_dataset.csv input/filtered_fake_news.txt

# Step 6: Upload to HDFS
hdfs dfs -mkdir /clickbait_data
hdfs dfs -put input/filtered_fake_news.txt /clickbait_data/

# Step 7: Run MapReduce job
hadoop jar ClickbaitDetection.jar ClickbaitDriver /clickbait_data/filtered_fake_news.txt /clickbait_output

# Step 8: Get result back to local
hdfs dfs -cat /clickbait_output/part-r-00000 > output/output.txt

# Step 9: Run visualization script
python3 generate_graph.py
