package com.back;

import com.back.domain.system.controller.SystemController;
import com.back.domain.wiseSaying.common.ActionType;
import com.back.domain.wiseSaying.controller.WiseSayingController;
import com.back.domain.wiseSaying.entity.CommandRequest;

import java.util.Scanner;

public class App {
    public void run(){
        Scanner scanner = AppContext.scanner;
        WiseSayingController wiseSayingController = AppContext.wiseSayingController;
        SystemController systemController = AppContext.systemController;
        System.out.println("== 명언 앱 ==");
        while(true){
            System.out.print("명령 ) ");
            String input = scanner.nextLine().trim();
            CommandRequest commandRequest = new CommandRequest(input);
            switch(commandRequest.getActionType()){
                case 종료 :
                    wiseSayingController.shutdown();
                    systemController.shutDown();
                    return;
                case 등록 :
                    wiseSayingController.add();
                    break;
                case 목록 :
                    wiseSayingController.list();
                    break;
                case 수정 :
                    wiseSayingController.update(commandRequest);
                    break;
                case 삭제 :
                    wiseSayingController.remove(commandRequest);
                    break;
                case 빌드 :
                    wiseSayingController.build();
                    break;
                default :
                    System.out.println("잘못된 명령어입니다.");
                    break;
            }
        }
    }
}

