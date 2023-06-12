package com.hnucm.C202001020212;

public class Star {

    public int code;
    public String status;
    public String msg;
    public DataDTO data;

    public static class DataDTO {
        public int id;
        public String fortuneSummary;
        public String luckyStar;
        public String signText;
        public String unSignText;
    }
}