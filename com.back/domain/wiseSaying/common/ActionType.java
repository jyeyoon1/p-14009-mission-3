package com.back.domain.wiseSaying.common;

public enum ActionType {
    등록, 삭제, 수정, 목록, 종료, 빌드, NULL;
    public static ActionType from(String input){
        if(input.startsWith("수정")) return 수정;
        if(input.startsWith("삭제")) return 삭제;
        if(input.startsWith("목록")) return 목록;
        try{
            return ActionType.valueOf(input);
        }catch(IllegalArgumentException e){
            return NULL;
        }
    }
}
