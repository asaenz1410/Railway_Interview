import io.railway.Railway;
import io.railway.StreamLogsResult;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class LogStreamer {

    public static void main(String[] args) {
        // Specify the path where you want to save the log file
        String logFilePath = "logs.txt";

        try {
            // Connect to the Railway deployment
            Railway railway = new Railway();

            // Stream the logs
            StreamLogsResult logsResult = railway.streamLogs().get();

            // Open the log file for writing
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath))) {
                // Read logs indefinitely
                while (true) {
                    String logLine = logsResult.getLogs().take();

                    // Write logs to the file
                    writer.write(logLine);
                    writer.newLine();
                    writer.flush();

                    // Print logs to the console
                    System.out.println(logLine);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
