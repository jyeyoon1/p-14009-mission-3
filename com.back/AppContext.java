package com.back;

import com.back.domain.system.controller.SystemController;
import com.back.domain.wiseSaying.controller.WiseSayingController;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;
import com.back.domain.wiseSaying.service.WiseSayingService;

import java.util.Scanner;

public class AppContext {
    public static final Scanner scanner;
    public static final WiseSayingRepository wiseSayingRepository;
    public static final WiseSayingService wiseSayingService;
    public static final WiseSayingController wiseSayingController;
    public static final SystemController systemController;

    static{
        scanner = new Scanner(System.in);
        wiseSayingRepository = new WiseSayingRepository();
        wiseSayingService = new WiseSayingService();
        wiseSayingController = new WiseSayingController();
        systemController = new SystemController();
    }
}
