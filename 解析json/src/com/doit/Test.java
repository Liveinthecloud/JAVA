package com.doit;

import java.io.*;

public class Test {
    public static void main(String [] arge) throws IOException {
        MoiveRate moiveRate;
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\Java\\解析json\\rating.json"), "UTF-8"));
        String s;
        int i=0;
        while ((s = br.readLine())!=null) {
            moiveRate = MoiveRateLogParse.parseLine(s);
            System.out.println(i+": "+moiveRate.getMovie()+"   "+moiveRate.getRate()+"   "+moiveRate.getTimeStamp()+"    "+moiveRate.getUid());
            i++;
        }
    }
}
