package core.RPN;

/**
 * Created by yaroslav on 10/5/15.
 */
public class Utils {


    private static String [] operations = new String[]{"AND", "OR"};

    public static boolean isWord(String word){

        String [] notWords = new String[]{")","(","AND","OR"};


        for(int i = 0; i < notWords.length; ++i){
            if(notWords[i].equals(word)){
                return false;
            }
        }

        return true;
    }


    public static boolean isOper(String word){

        for(int i = 0; i < operations.length; ++i){
            if(operations[i].equals(word)){
                return true;
            }
        }

        return false;
    }



}
