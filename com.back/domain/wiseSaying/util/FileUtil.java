package com.back.domain.wiseSaying.util;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    static final String path = "db/wiseSaying";
    static final String fileTypeJson = ".json";
    static final String fileTypeText = ".txt";

    private boolean createDirectory(){
        boolean result = false;
        try{
            if(!checkDirectory()){
                Files.createDirectories(Paths.get(path));
            }
            result = true;
        }catch(IOException e){
            e.printStackTrace();
            System.out.println("db/wiseSaying 생성 실패");
        }
        return result;
    }

    private boolean checkDirectory(){
        return Files.exists(Paths.get(path));
    }

    public int loadLastId() {
        int id = 0;
        String fileName = path + File.separator + "lastId.txt";
        if(checkDirectory()) {
            try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                String line;
                while ((line = br.readLine()) != null) {
                    id = Integer.parseInt(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("lastId.txt 읽기 실패");
            }
        }
        return id;
    }

    public boolean writerLastId(int lastId) {
        boolean result = false;
        if(createDirectory()) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path + File.separator + "lastId" + fileTypeText, false))) {
                bw.write(lastId + "");
                result = true;
                System.out.println("lastId.txt 갱신 성공");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("lastId.txt 생성 실패");
            }
        }
        return result;
    }

    public boolean writer(String fileName, String toString) {
        boolean result = false;
        if(createDirectory()) {
            try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path +File.separator+ fileName + fileTypeJson, false), StandardCharsets.UTF_8))) {
                bw.write(toString);
                result = true;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.printf("%s.json 생성 실패\n", fileName);
            }
        }
        return result;
    }

    public boolean delete(int id) {
        boolean result = false;
        Path filePath = Paths.get(path+File.separator+id+fileTypeJson);
        try {
            if(Files.deleteIfExists(filePath)){
                result = true;
            }
        }catch (IOException e) {
            e.printStackTrace();
            System.out.printf("%d.json 삭제 실패\n", id);
        }
        return result;
    }

    public List<String> readJsonFiles(){
        List<String> jsonFiles = new ArrayList<>();
        if(checkDirectory()) {
            File dir = new File(path);
            File[] files = dir.listFiles((f, name) -> name.endsWith(fileTypeJson) && !name.contains("data"));
            if(files !=null){
                for(File file : files){
                    StringBuilder sb = new StringBuilder();
                    try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))){
                        String line;
                        while((line=br.readLine())!=null){
                            sb.append(line.trim());
                        }
                        jsonFiles.add(sb.toString());
                    }catch(IOException e){
                        e.printStackTrace();
                        System.out.printf("%s 읽어오기 실패\n", file.getName());
                    }
                }
            }
        }
        return jsonFiles;
    }

    public String readDataJson(){
        StringBuilder sb = new StringBuilder();
        String fileName = "data.json";
        if(checkDirectory()) {
            try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path+File.separator+fileName),StandardCharsets.UTF_8))){
                String line;
                while((line=br.readLine())!=null){
                    sb.append(line.trim());
                }
            }catch(IOException e){
                e.printStackTrace();
                System.out.println("data.json 읽기 실패\n");
            }
        }
        return sb.toString();
    }
}
