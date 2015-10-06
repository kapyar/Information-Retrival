package core.utils;

/**
 * Created by yaroslav on 10/5/15.
 */
public class Pair implements Comparable<Pair>{



    public String fileName;
    public int fileId;

    public Pair(String name, int id){

        this.fileName   = name;
        this.fileId     = id;

    }

    @Override
    public String toString() {
        return "Pair{" +
                "fileName='" + fileName + '\'' +
                ", fileId=" + fileId +
                '}';
    }


    public int compareTo(Pair o) {
        return fileName.compareTo(o.fileName);
    }
}
