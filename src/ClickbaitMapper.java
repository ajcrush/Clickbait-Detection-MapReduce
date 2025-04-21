import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.HashSet;

public class ClickbaitMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    private final static IntWritable one = new IntWritable(1);
    private Text word = new Text();

    private static final HashSet<String> STOPWORDS = new HashSet<String>() {{
        add("the"); add("is"); add("and"); add("to"); add("a"); add("of"); add("in"); add("for");
    }};

    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        StringTokenizer tokenizer = new StringTokenizer(value.toString().replaceAll("[^a-zA-Z ]", "").toLowerCase());

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (!STOPWORDS.contains(token) && token.length() > 2) { // Ignore stopwords & short words
                word.set(token);
                context.write(word, one);
            }
        }
    }
}
