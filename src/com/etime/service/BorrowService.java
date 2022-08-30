package com.etime.service;

import com.etime.bean.BorrowRecord;
import com.etime.dto.BorrowRecordDto;

import java.util.List;

public interface BorrowService {
    List<BorrowRecordDto> findAllUserAndBookBorrow();
    void findBookBorrowByUserId(int userId);
    void borrowBook(int userId, int bookId, int number);
    List<BorrowRecord> userFindBookBorrowByUserId(int userId,int ...state);
    void returnBook(int userId, int bookId, int number);
}
