import java.io.*;
import java.nio.file.*;

public class FakeNewsFilter {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage: FakeNewsFilter <input file> <output file>");
            System.exit(1);
        }

        Path inputFile = Paths.get(args[0]);
        Path outputFile = Paths.get(args[1]);

        try (BufferedReader reader = Files.newBufferedReader(inputFile);
             BufferedWriter writer = Files.newBufferedWriter(outputFile)) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length > 5 && fields[fields.length - 1].equalsIgnoreCase("Fake")) {
                    writer.write(fields[1] + "\n"); // Write only the title
                }
            }
        }
        System.out.println("Filtered Fake News Titles saved to " + args[1]);
    }
}
