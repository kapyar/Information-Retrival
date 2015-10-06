import core.RPN.Finder;
import reader.Reader;

import java.io.IOException;

/**
 * Created by yaroslav on 10/5/15.
 */


public class Main {


    public static void main(String [] args) throws IOException {


        String path = "potter";

        Reader reader = new Reader();

        Finder finder = new Finder(reader.readFromDirectoryAndCreateIndex(path));

        System.out.println(finder.find("( Harry AND Potter ) OR car"));


    }

}
