package br.com.rmystems;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.stream.Stream;

public class BalancedBrackets {

    static Stack<String> stack = new Stack<>();

    static final Map<String, String> matchedBrackets = new HashMap<>();
    static final String openBrackets = "{[(";
    static final String closeBrackets = ")]}";

    static {
        matchedBrackets.put("{", "}");
        matchedBrackets.put("[", "]");
        matchedBrackets.put("(", ")");
    }

    static String isBalanced(String s) {

        if (s.length() == 0 && stack.isEmpty()) {
            return "YES";
        } else if (s.length() > 0) {
            String bracket = String.valueOf(s.charAt(0));
            if (openBrackets.contains(bracket)) {
                stack.push(bracket);
                return isBalanced(s.substring(1));
            } else if (closeBrackets.contains(bracket)) {
                if (!stack.isEmpty() && matchedBrackets.get(stack.peek()).equals(bracket)) {
                    stack.pop();
                    return isBalanced(s.substring(1));
                }
            }
        }

        stack.clear();
        return "NO";
    }

    public static void main(String[] args)  {

        ClassLoader classLoader = BalancedBrackets.class.getClassLoader();
        URL resource = classLoader.getResource("balance_brackets_file");
        try {
            if (resource != null) {
                String path = URLDecoder.decode(resource.getPath(), "utf-8");
                try (FileReader reader = new FileReader(new File(path));
                     BufferedReader br = new BufferedReader(reader)) {

                    String line;
                    int i = 1;
                    while ((line = br.readLine()) != null) {
                        System.out.println(i++ + ": " + isBalanced(line));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
