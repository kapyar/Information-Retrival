package core.RPN;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * Created by yaroslav on 10/5/15.
 */
public class Finder {


    public static final String WHITE_SPACE = " ";
    private static Logger log = LoggerFactory.getLogger(Finder.class);

    private String rpn;
    private Stack<String> stack;



    public Finder(){

        stack = new Stack<String>();
    }


    public int [] find(String expression){


        String rpn = tranformIntoRPN(expression);
        String [] rpnSplited = rpn.split(WHITE_SPACE);

        Stack<List<Integer>> stack = new Stack<List<Integer>>();

        for(int i = 0; i < rpnSplited.length; ++i){

            log.debug("Token: {}",rpnSplited[i]);

        }




        return null;
    }


    private Set<Integer> intersect(List<Integer> list1, List<Integer> list2){

        Set<Integer> set = new HashSet<Integer>(list1);

        set.retainAll(list2);

        return set;
    }

    private Set<Integer> unite(List<Integer> list1, List<Integer> list2){

        Set<Integer> set = new HashSet<Integer>(list1);

        set.addAll(list2);
        return set;
    }


    private String tranformIntoRPN(String expression) {

        String [] splitedExpression = expression.
                split(WHITE_SPACE);

        for(int i = 0; i < splitedExpression.length; ++i){

            String word = splitedExpression[i];

            if(Utils.isWord(word)){
                rpn+= splitedExpression[i]+ WHITE_SPACE;
            }else if(Utils.isOper(splitedExpression[i])){
                if(stack.get(stack.size() - 1) != "(" && word.equals("AND")){

                    rpn += stack.pop() + WHITE_SPACE;
                }
                stack.push(word);
            }else if(!word.equals(")")){
                stack.push(word);
            }else{
                String tempOp = stack.pop();

                while (!tempOp.equals("(")){
                    rpn += tempOp;
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
