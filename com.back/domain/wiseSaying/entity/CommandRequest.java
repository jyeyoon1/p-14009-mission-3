package com.back.domain.wiseSaying.entity;

import com.back.domain.wiseSaying.common.ActionType;

import java.util.HashMap;
import java.util.Map;

public class CommandRequest {
    private ActionType actionType;
    private Map<String, String> params;

    private final static String id= "id";
    private final static String keywordType = "keywordType";
    private final static String keyword = "keyword";

    public CommandRequest(String cmd) {
        this.actionType = ActionType.from(cmd);
        this.params = new HashMap<>();
        if(actionType!=null && cmd.contains("?")) {
            String[] parmasSplit = cmd.split("\\?",2)[1].split("&");
            for(String parmas : parmasSplit){
                String[] keyValue = parmas.split("=",2);
                params.put(keyValue[0].trim(),keyValue[1].trim());
            }
        }
    }

    public ActionType getActionType() {
        return actionType;
    }

    public int getValidId() {
        if (!(actionType == ActionType.수정 || actionType == ActionType.삭제)) {
            return -1;
        }

        String value = params.get("id");
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException | NullPointerException e) {
            return -1;
        }
    }

    public String checkKeywordByParamKey() { //13단계 수정 필요
        return actionType.equals(ActionType.목록) && (params.containsKey(keyword) || params.containsKey(keywordType)) ? params.get(keyword) : null;
    }
}
