package com.etime.bean;

public class BookType {
    private int typeId;
    private String typeName;

    @Override
    public String toString() {
        return "图书类型编号："+typeId+", 图书类型："+typeName;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public BookType() {
    }

    public BookType(String typeName) {
        this.typeName = typeName;
    }

    public BookType(int typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

}
