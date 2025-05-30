package com.back.domain.wiseSaying.util;

public class StringUtil {
    //~`!@#$%^&*()_-+{}[]|\:;"'<>/ 제거
    public static String removeSpecialChars(String str){
        return str.replaceAll("[^가-힣a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ ,.=?]", "");
    }
    //숫자외에는 제거하기
    public static String removeChars(String str){
        return str.replaceAll("[^0-9]","");
    }
}
