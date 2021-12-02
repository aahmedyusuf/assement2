import java.io.*;
import java.util.*;

public class Lexical {
   
    static String lexmen = "";
    static HashMap<String, Integer> map = new HashMap<>();
    static HashMap<String, String> order = new HashMap<>();

    public static void main(String args[]) throws IOException {
        set_Order();
        in_Map();
        File file = new File("/Users/anwar/PLC/Assigment/test.txt");

        BufferedReader br = new BufferedReader(new FileReader(file));
 
        String line;

        while ((line = br.readLine()) != null)
        {
            // Print the string

            for(int i = 0; i < line.length(); i++){


                identify(line.charAt(i), false);

            }
        }
 
        identify(' ', true);

        System.out.println("Next token is: -1 , Next Lexmen is  EOF");

    }

    
    static void in_Map(){

        map.put("+",2);
        map.put("-",3);
        map.put("/",4);
        map.put("*",5);
        map.put("(",6);
        map.put(")",7);
        map.put("=",9);
        map.put("%",10);
        map.put("#",11);
        map.put("!",12);
        map.put("^",13);
        map.put("for",30);
        map.put("if",31);
        map.put("else",32);
        map.put("while",33);
        map.put("do",34);
        map.put("int",35);
        map.put("float",36);
        map.put("switch",37);
        map.put("void",38);
        map.put("main",39);
        map.put("{",40);
        map.put("}",41);


    }

    static void set_Order(){

        order.put("{", "Enter <Block>");
        order.put("}","Exit <Block>");
        order.put("main", "Enter <Main>");
        order.put("void", "Enter <Void>");
        order.put("(", "Enter <Expression>");
        order.put(")", "Exit <Expression>");


    }

    static void identify(Character c, boolean finalC){

        boolean isValid = map.containsKey(c+"");

        if(c == ' '){
            isValid = true;
        }
        if(isValid == false){
            lexmen += c+"";
        }else{
            
            int tokenId = isFloat(lexmen) == false ? 0 : 15;
            tokenId = isDigit(lexmen) == false ? 0 : 8;
            
            if(map.containsKey(lexmen)){//string
                tokenId = map.get(lexmen);
            }

            if(lexmen.length() > 1){
                System.out.println("Next token is: " + tokenId + ", Next Lexmen is " + lexmen);
                Check_state(lexmen);
            }
            if(map.get(c+"") != null){

                System.out.println("Next token is: " +  map.get(c+"") + ", Next Lexmen is " + c);
                Check_state(c+"");
            }
            lexmen = "";

        }

    }

    private static void Check_state(String s){

        if(order.containsKey(s)){
            System.out.println(order.get(s));
        }

    }

    static boolean isFloat(String s){

        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i)=='.'){
                return true;
            }
        }
        return false;

    }

    static boolean isDigit(String s){
        for(int i = 0; i < s.length(); i++){
            if(Character.isLetter(s.charAt(i))){
                return false;
            }
        }
        return true;
    }
}