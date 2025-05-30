package com.back.domain.wiseSaying.service;

import com.back.AppContext;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;
import com.back.domain.wiseSaying.util.StringUtil;

import java.util.List;
import java.util.stream.Collectors;

public class WiseSayingService {

    private final WiseSayingRepository wiseSayingRepository = AppContext.wiseSayingRepository;

    public void writeLastId(){
        wiseSayingRepository.saveId();
    }

    public int add(String author, String content){
        return wiseSayingRepository.save(new WiseSaying(StringUtil.removeSpecialChars(author), StringUtil.removeSpecialChars(content)));
    }

    public String list(){
        return  wiseSayingRepository.findAllOrderByDesc().stream().map(WiseSaying::toString).collect(Collectors.joining("\n"));
    }

    public boolean remove(int id){
        return wiseSayingRepository.deleteById(id);
    }

    public void update(WiseSaying wiseSaying, String author, String content){
        wiseSaying.setAuthor(StringUtil.removeSpecialChars(author));
        wiseSaying.setContent(StringUtil.removeSpecialChars(content));
        wiseSayingRepository.save(wiseSaying);
    }

    public boolean writeList(){
        return wiseSayingRepository.saveAll();
    }

    public WiseSaying getWiseSaying(int id){
        return wiseSayingRepository.findById(id);
    }

}
