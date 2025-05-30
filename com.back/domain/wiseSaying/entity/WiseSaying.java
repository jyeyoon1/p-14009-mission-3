package com.back.domain.wiseSaying.entity;

public class WiseSaying {
    private int id;
    private String author;
    private String content;

    public WiseSaying() {}
    public WiseSaying(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public boolean isNew(){
        return getId() == 0;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String toString() {
        return String.format("%d / %s / %s", id, author, content);
    }
    //WiseSaying -> JsonObject 로 변환
    public String toJson(int indent){
        String tab = "\t".repeat(indent);
        return tab + "{\n" +
                tab + "\t\"id\": "+id+",\n" +
                tab + "\t\"content\": \""+content+"\",\n" +
                tab + "\t\"author\": \""+author+"\"\n" +
                tab + "}";
    }
}
