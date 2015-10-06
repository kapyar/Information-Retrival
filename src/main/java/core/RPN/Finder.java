package core.RPN;

import core.Index;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by yaroslav on 10/5/15.
 */
public class Finder {


    public static final String WHITE_SPACE = " ";
    private static Logger log = LoggerFactory.getLogger(Finder.class);

    private String rpn;
    private Stack<String> stack;
    private List<Index> index;



    public Finder(List<Index> index){

        this.index = index;
        this.stack = new Stack<String>();
        this.rpn = "";
    }


    public List<Integer> find(String expression){


        String rpn = tranformIntoRPN(expression);
        String [] rpnSplited = rpn.split(WHITE_SPACE);

        Stack<List<Integer>> stack = new Stack<List<Integer>>();

        for(int i = 0; i < rpnSplited.length; ++i){

            log.debug("Token: {}",rpnSplited[i]);

            if(Utils.isWord(rpnSplited[i])){
                log.info("Word is: {}", rpnSplited[i]);

                 stack.push(index.get(binarySearch(index, rpnSplited[i])).getArray());
            } else if (Utils.isOper(rpnSplited[i])){
                if(stack.size() > 2){

                    List<Integer> a = stack.pop();
                    List<Integer> b = stack.pop();

                    if(rpnSplited[i].equals("AND")){
                        stack.push(intersect(a,b));
                    }

                    if(rpnSplited[i].equals("OR")){
                        stack.push(unite(a, b));
                    }

                }

            }

        }

        return stack.pop();
    }

    private List<Integer> getListByWord(){


        return null;

    }

    private int binarySearch(List<Index> list, String word){


        int left    = 0;
        int right   = list.size();

        //check if in range
        if(list.get(0).getWord().compareTo(word) > 0){ return -1;}
        if(list.get(list.size() - 1).getWord().compareTo(word) < 0){return -1;}



        while(left <= right){

            int mid = left + (right - left) / 2;

            if(list.get(mid).getWord().compareTo(word) > 0){
                right = mid - 1;
            }else if(list.get(mid).getWord().compareTo(word) < 0){
                left = mid + 1;
            }else
            {
                return mid;
            }


        }



        return -1;

    }


    private List<Integer> intersect(List<Integer> list1, List<Integer> list2){

        Set<Integer> set = new HashSet<Integer>(list1);

        set.retainAll(list2);

        return new ArrayList<Integer>(set);
    }

    private List<Integer> unite(List<Integer> list1, List<Integer> list2){

        Set<Integer> set = new HashSet<Integer>(list1);

        set.addAll(list2);
        return new ArrayList<Integer>(set);
    }


    private String tranformIntoRPN(String expression) {

        String [] splitedExpression = expression.
                split(WHITE_SPACE);

        for(int i = 0; i < splitedExpression.length; ++i){



            String word = splitedExpression[i];

            if(Utils.isWord(word)){
                rpn += splitedExpression[i]+ WHITE_SPACE;

            }else if(Utils.isOper(splitedExpression[i])){

                if(!stack.isEmpty()) {
                    if (!"(".equals(stack.get(stack.size() - 1)) && word.equals("AND")) {
                        rpn += stack.pop() + WHITE_SPACE;
                    }
                }
                stack.push(word);
            }else if(!word.equals(")")){

                stack.push(word);
            }else{

                String tempOp = stack.pop();

                while (!tempOp.equals("(")){

                    rpn += tempOp + WHITE_SPACE ;
                    tempOp = stack.pop();

                }
            }

        }

        while (!stack.isEmpty()){
            rpn += stack.pop();
        }

        log.debug("RPN is {}", rpn);


        return rpn;


    }

}
