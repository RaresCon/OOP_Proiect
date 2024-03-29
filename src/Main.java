import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import input.Input;
import jdk.jshell.spi.ExecutionControl;
import site.SiteConnection;

import java.io.File;
import java.io.IOException;

public final class Main {
    /**
     * default constructor
     */
    private Main() {
    }

    /**
     * main method of the connection to the site
     * @param args file input/output
     * @throws IOException error if the connection to the site is not established
     */
    public static void main(final String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Input inputData = objectMapper.readValue(new File(args[0]), Input.class);

        ArrayNode output = objectMapper.createArrayNode();

        try {
            SiteConnection.getSiteConnection().processInput(inputData, output);
        } catch (ExecutionControl.InternalException e) {
            throw new RuntimeException(e);
        }

        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(args[1]), output);
    }
}
