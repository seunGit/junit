package com.example.junit.service;

import com.example.junit.domain.Book;
import com.example.junit.domain.BookRepository;
import com.example.junit.web.dto.BookResponseDto;
import com.example.junit.web.dto.BookSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    // 1. 책등록
    @Transactional(rollbackFor = RuntimeException.class)
    public BookResponseDto 책등록하기(BookSaveRequestDto dto){
        Book bookPS = bookRepository.save(dto.toEntity());
        return new BookResponseDto().toDto(bookPS);
    }
    // 2. 책 목록보기

    // 3. 책 한건 보기

    // 4. 책 삭제

    // 5. 책 수정
}
