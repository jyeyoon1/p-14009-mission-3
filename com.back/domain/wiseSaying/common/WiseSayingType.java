package com.back.domain.wiseSaying.common;

public enum WiseSayingType {
    id, content, author, NULL;
    public static WiseSayingType from(String input){
        try{
            return WiseSayingType.valueOf(input);
        }catch(IllegalArgumentException e){
            return null;
        }
    }
}
