package com.example.junit.web;

import com.example.junit.service.BookService;
import com.example.junit.web.dto.response.BookResponseDto;
import com.example.junit.web.dto.request.BookSaveRequestDto;
import com.example.junit.web.dto.response.CMResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BookApiController {

    private final BookService bookService;

    // 1. 책 등록
    // key=value&key=value
    // {"key" : "value", "key" : "value"}
    @PostMapping("/api/v1/book")
    public ResponseEntity<?> saveBook(@RequestBody @Valid BookSaveRequestDto bookSaveRequestDto, BindingResult bindingResult) {

        // AOP 처리하는게 좋음 !!
        if(bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError fe : bindingResult.getFieldErrors()) {
                errorMap.put(fe.getField(), fe.getDefaultMessage());
            }
            System.out.println("=======================================");
            System.out.println(errorMap.toString());
            System.out.println("=======================================");

            throw new RuntimeException(errorMap.toString());
        }
        BookResponseDto bookResponseDto = bookService.책등록하기(bookSaveRequestDto);
        return new ResponseEntity<>(CMResponseDto.builder().code(1).msg("책 등록 성공").body(bookResponseDto).build(),
                HttpStatus.CREATED); // 201 = insert
    }
    // 2. 책 목록보기
    public ResponseEntity<?> getBookList() {
        return null;
    }
    // 3. 책 한건 보기
    public ResponseEntity<?> getBookOne() {
        return null;
    }
    // 4. 책 삭제하기
    public ResponseEntity<?> deleteBook() {
        return null;
    }
    //  5. 책 수정하기
    public ResponseEntity<?> updateBook() {
        return null;
    }
}
