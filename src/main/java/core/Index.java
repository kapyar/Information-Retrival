package core;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yaroslav on 10/5/15.
 */
public class Index {

    private String word;

    public List<Integer> getArray() {
        return array;
    }

    private List<Integer> array;



    public Index(String word, int docId){

        this.word  = word;
        this.array = new ArrayList<Integer>();
        this.array.add(docId);

    }


    public void addDocId(int id){
        this.array.add(id);
    }


    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }





    @Override
    public String toString() {
        return "Index{" +
                "word='" + word + '\'' +
                ", array=" + array +
                '}';
    }


}
