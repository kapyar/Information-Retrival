package reader;

import core.Index;
import core.utils.Pair;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



/**
 * Created by yaroslav on 10/5/15.
 */
public class Reader {


    private static Logger log = LoggerFactory.getLogger(Reader.class);

    private List<Pair> pairsDocID;

    private List<Index> index;


    public Reader(){
        this.pairsDocID = new ArrayList<Pair>();
        this.index = new ArrayList<Index>();
    }



    public List<Index> readFromDirectoryAndCreateIndex(String path) throws IOException {

        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        int orderNumber = 0;

        List<Pair> pairsOfWord = new ArrayList<Pair>();

        log.info("Start parsing folder: {}",path);

        for (int i = 0; i < listOfFiles.length; i++) {
            File file = listOfFiles[i];
            if (file.isFile() && file.getName().endsWith(".txt")) {

                long startTime = System.currentTimeMillis();

                log.info("Start parsing: {}",file.getName());

                this.pairsDocID.add(new Pair(file.getName(), orderNumber++));

                String content = FileUtils.readFileToString(file);
                String [] wordsAlone = content.split(" ");

//                add words with its docID

                for(int j = 0; j < wordsAlone.length; ++j){
                    pairsOfWord.add(new Pair(wordsAlone[j], orderNumber));
                }

                long finishTime = System.currentTimeMillis();

                log.info("Finish parsing: {} in {} ms",file.getName(), finishTime - startTime);
            }
        }

//        create a index

        log.info("Start creating index: ");
        long startTime = System.currentTimeMillis();



        log.info("Start sorting");
        Collections.sort(pairsOfWord);
        log.info("Finish sorting");


        Pair tempPair = pairsOfWord.get(0);
        this.index.add(new Index(tempPair.fileName, tempPair.fileId));

        for(int i = 1; i < pairsOfWord.size(); ++i){

            if(tempPair.fileName.equals(pairsOfWord.get(i).fileName)){
                if(tempPair.fileId < pairsOfWord.get(i).fileId ){
                    this.index.get(this.index.size() - 1).getArray().add(pairsOfWord.get(i).fileId);
                    tempPair = pairsOfWord.get(i);
                }
            }else{
                tempPair = pairsOfWord.get(i);
                this.index.add(new Index(pairsOfWord.get(i).fileName, pairsOfWord.get(i).fileId));
            }
        }

        long finishTime = System.currentTimeMillis();

        log.info("Finish creating index in {} ms", finishTime - startTime);
        log.info("All words size: {}",pairsOfWord.size());
        log.debug("Index size: {}", index.size());


        return index;
    }




}
