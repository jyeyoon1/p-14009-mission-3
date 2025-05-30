package com.back.domain.wiseSaying.util;

import com.back.domain.wiseSaying.common.WiseSayingType;
import com.back.domain.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WiseSayingJsonParser {

    //list -> JsonArray 형식으로 변환
    public static String toStringFromArray(List<WiseSaying> list){
        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        sb.append(list.stream().map(ws -> ws.toJson(1)).collect(Collectors.joining(",\n")));
        sb.append("\n]");
        return sb.toString();
    }

    //#.json 파일 -> WiseSaying 변환
    public static WiseSaying toWiseSayingFromString(String str){
        String[] sArr = str.substring(str.indexOf("{")+1, str.lastIndexOf("}")-1).split(",");
        WiseSaying wiseSaying = new WiseSaying();
        for(String strPair : sArr){
            String[] strPairArr = strPair.replaceAll("\"","").split(":",2);
            String value = strPairArr[1].trim();
            WiseSayingType wiseSayingType = WiseSayingType.from(strPairArr[0].trim());
            switch (wiseSayingType){
                case id:
                    wiseSaying.setId(Integer.parseInt(value));
                    break;
                case author:
                    wiseSaying.setAuthor(value);
                    break;
                case content:
                    wiseSaying.setContent(value);
                    break;
            }
        }
        return wiseSaying;
    }

    //data.json에서 데이터 불러올 때
    private static List<WiseSaying> toList(String content) {
        List<WiseSaying> wiseSayings = new ArrayList<>();
        String str = content.substring(content.indexOf("{"), content.lastIndexOf("}")+1);
        String[] strArr = str.split("},\\{");
        for(String s: strArr){
            wiseSayings.add(toWiseSayingFromString(s));
        }
        return wiseSayings;
    }
}
