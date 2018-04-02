
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.*;
import java.lang.*;
import java.io.*;

import static java.lang.System.out;

public class Solution {
    public static double count(String s) throws Exception{
        double a = 0;
        if ((s.contains("+")) || (s.contains("-"))) {
            if (s.lastIndexOf("+") > s.lastIndexOf("-")) {
                a = count(s.substring(0, s.indexOf("+"))) + count(s.substring(s.indexOf("+") + 1, s.length()));
            } else {
                a = count(s.substring(0, s.lastIndexOf("-"))) - count(s.substring(s.lastIndexOf("-") + 1, s.length()));
            }
        } else if ((s.contains("*")) || (s.contains("/"))) {
            if (s.lastIndexOf("*") > s.lastIndexOf("/")) {
                a = count(s.substring(0, s.indexOf("*"))) * count(s.substring(s.indexOf("*") + 1, s.length()));
            } else {
                a = count(s.substring(0, s.lastIndexOf("/"))) / count(s.substring(s.lastIndexOf("/") + 1, s.length()));
            }
        } else {
            a = Double.parseDouble(s);
        }
        //System.out.print(s + " ");
        //System.out.println(a);
        return a;
    }

    public static ArrayList<Pair<Integer, Integer>> brackets(String s) throws Exception{
        ArrayList<Integer> beg = new ArrayList<Integer>();
        ArrayList<Pair<Integer, Integer>> res = new ArrayList<Pair<Integer, Integer>>();
        char[] s1 = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (s1[i] == '(') beg.add(i);
            if (s1[i] == ')') {
                int last = i;
                int start = beg.get(beg.size() - 1);
                beg.remove(beg.size() - 1);
                Pair<Integer, Integer> pair = new Pair<>(start, last);
                res.add(pair);
                // System.out.println(start + " " + last);
            }
        }
        return res;
    }

    public static double result(String s)throws Exception{
        s = "1+" + s + "-1";
        ArrayList<Pair<Integer, Integer>> t = new ArrayList<Pair<Integer, Integer>>();
        t = brackets(s);
        int[][] x = new int[t.size()][2];
        for (int i = 0; i < t.size(); i++) {
            x[i][0] = t.get(i).getKey();
            x[i][1] = t.get(i).getValue();
        }
        int d = x.length;
        for (int i = 0; i < d; i++) {
            System.out.println(s);
            t = brackets(s);
            for (int j = 0; j < t.size(); j++) {
                x[j][0] = t.get(j).getKey();
                x[j][1] = t.get(j).getValue();
            }
            int start = x[0][0];
            int last = x[0][1];
            System.out.println(start + " " + last);
            double f = count(s.substring(start + 1, last));
            s = s.substring(0, start) + f + s.substring(last + 1, s.length());
        }
        double a = count(s);
        return a;
    }
}

