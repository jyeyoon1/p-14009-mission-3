package com.back.domain.wiseSaying.controller;

import com.back.AppContext;
import com.back.domain.wiseSaying.entity.CommandRequest;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.service.WiseSayingService;

import java.util.Scanner;

public class WiseSayingController {

    private final Scanner scanner = AppContext.scanner;
    private final WiseSayingService wiseSayingService = AppContext.wiseSayingService;

    public void shutdown(){
        wiseSayingService.writeLastId();
    }

    public void add(){
        System.out.print("명언 : ");
        String content = scanner.nextLine().trim();
        System.out.print("작가 : ");
        String author = scanner.nextLine().trim();
        System.out.printf("%d번 명언이 등록되었습니다.\n", wiseSayingService.add(author, content));
    }

    public void list(){
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        System.out.println(wiseSayingService.list());
    }

    public void remove(CommandRequest commandRequest){
        int id = commandRequest.getValidId();
        if(id < 0){
            System.out.println("잘못된 입력입니다.");
            return;
        }

        if(!wiseSayingService.remove(id)){
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
        }else{
            System.out.printf("%d번 명언이 삭제되었습니다.\n", id);
        }
    }

    public void update(CommandRequest commandRequest){
        int id = commandRequest.getValidId();
        if(id < 0){
            System.out.println("잘못된 입력입니다.");
            return;
        }

        WiseSaying wiseSaying = wiseSayingService.getWiseSaying(id);
        if(wiseSaying==null){
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", id);
        }else{
            System.out.printf("명언(기존) : %s\n명언 : ", wiseSaying.getContent());
            String content = scanner.nextLine().trim();
            System.out.printf("작가(기존) : %s\n작가 : ", wiseSaying.getAuthor());
            String author = scanner.nextLine().trim();
            wiseSayingService.update(wiseSaying, author, content);
        }
    }

    public void build(){
        boolean result = wiseSayingService.writeList();
        if(result){
            System.out.println("data.json 파일의 내용이 갱신되었습니다.");
        }else{
            System.out.println("data.json 파일 갱신이 실패되었습니다.");
        }
    }

}
