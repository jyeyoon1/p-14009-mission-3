package com.back.domain.system.controller;

import com.back.AppContext;

public class SystemController {

    public void shutDown() {
        AppContext.scanner.close();
        System.out.println("프로그램을 종료합니다.");
    }
}
