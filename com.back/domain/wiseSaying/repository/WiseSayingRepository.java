package com.back.domain.wiseSaying.repository;

import com.back.AppContext;
import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.util.FileUtil;
import com.back.domain.wiseSaying.util.WiseSayingJsonParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class WiseSayingRepository {

    private int lastId;
    private List<WiseSaying> wiseSayings;
    private final FileUtil fileUtil = new FileUtil();

    public WiseSayingRepository() {
        lastId = loadLastId();
        wiseSayings = findAll();
    }

    public void saveId(){
        fileUtil.writerLastId(lastId);
    }

    private int loadLastId() {
        return fileUtil.loadLastId();
    }

    public int save(WiseSaying wiseSaying) {
        if(wiseSaying.isNew()){
            wiseSaying.setId(++lastId);
            wiseSayings.add(wiseSaying);
        }else{
            int idx = findIndexById(wiseSaying.getId());
            wiseSayings.set(idx, wiseSaying);
        }
        fileUtil.writer(String.valueOf(lastId), wiseSaying.toJson(0));
        return wiseSaying.getId();
    }

    public WiseSaying findById(int id) {
        return wiseSayings.stream().filter(x -> x.getId() == id).findFirst().orElse(null);
    }

    public List<WiseSaying> findAll() {
        wiseSayings = new ArrayList<>();
        for(String str : fileUtil.readJsonFiles()){
            wiseSayings.add(WiseSayingJsonParser.toWiseSayingFromString(str));
        }
        return wiseSayings;
    }

    public boolean saveAll() {
        return fileUtil.writer("data", WiseSayingJsonParser.toStringFromArray(wiseSayings));
    }

    public boolean deleteById(int id) {
        boolean result = false;
        int idx = findIndexById(id);
        if(idx > -1){
            wiseSayings.remove(idx);
            fileUtil.delete(id);
            result = true;
        }
        return result;
    }

    public List<WiseSaying> findAllOrderByDesc() {
        List<WiseSaying> copy = new ArrayList<>(wiseSayings);
        Collections.reverse(copy);
        return copy;
    }

    private int findIndexById(int id) {
        return IntStream.range(0, wiseSayings.size()).filter(idx -> id == wiseSayings.get(idx).getId()).findFirst().orElse(-1);
    }
}
