import reader.Reader;

import java.io.IOException;

/**
 * Created by yaroslav on 10/5/15.
 */


public class Main {


    public static void main(String [] args) throws IOException {


        String path = "potter";

        Reader reader = new Reader();

        reader.readFromDirectoryAndCreateIndex(path);


    }

}
