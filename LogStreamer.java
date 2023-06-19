import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class LogStreamer {
    public static void main(String[] args) {
        // Specify the path where you want to save the log file
        String logFilePath = "logs.txt";

        try {
            // Create a BufferedWriter to write logs to the file
            BufferedWriter writer = new BufferedWriter(new FileWriter(logFilePath));

            // Execute the Railway CLI command to stream logs
            ProcessBuilder processBuilder = new ProcessBuilder("railway logs");
            Process process = processBuilder.start();

            // Read logs from the process's input stream
            process.getInputStream().transferTo(writer);

            // Close the writer and wait for the process to complete
            writer.close();
            process.waitFor();

            System.out.println("Log streaming completed.");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
