package com.etime.service;

import com.etime.bean.Book;

import java.util.List;

public interface BookService {
    List<Book> findAllBook(int role);
    void insertBook(Book book);
    void deleteBook(int bookId, String inScan);
    void updateBook(Book book);
    void setBookState(int bookId, int state);
    void findBookByName(String bookName,int role);
    void findBookByType(String typeName,int role);
    void findBookByAuthor(String author,int role);
}
