package com.example.junit.service;

import com.example.junit.domain.Book;
import com.example.junit.domain.BookRepository;
import com.example.junit.web.dto.BookResponseDto;
import com.example.junit.web.dto.BookSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<BookResponseDto> 책목록보기() {
        return bookRepository.findAll().stream()
                .map(new BookResponseDto()::toDto)
                .collect(Collectors.toList());
    }
    // 3. 책 한건 보기
    public BookResponseDto 책한건보기(Long id){
        Optional<Book> bookOP = bookRepository.findById(id);
        if(bookOP.isPresent()){
            return new BookResponseDto().toDto(bookOP.get());
        } else {
            throw new RuntimeException("해당 아이디를 찾을 수 없습니다.");
        }
    }
    // 4. 책 삭제

    // 5. 책 수정
}
