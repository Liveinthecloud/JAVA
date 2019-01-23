package com.doit;

import com.google.gson.Gson;

public class MoiveRateLogParse {
    public static MoiveRate parseLine(String line){
        Gson gson = new Gson();
        MoiveRate moiveRate = gson.fromJson(line, MoiveRate.class);
        return moiveRate;
    }

}
