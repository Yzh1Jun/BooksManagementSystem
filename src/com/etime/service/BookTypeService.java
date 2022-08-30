package com.etime.service;

import com.etime.bean.BookType;

import java.util.List;

public interface BookTypeService {
    BookType findBookTypeByTypeId(int typeId);
    List<BookType> findAllBookType();
    int findTypeIdByTypeName(String typeName);
    void insertBookType(BookType bookType);
    void updateBookType(BookType bookType);
    void findBookTypeByName(String typeName);
}
