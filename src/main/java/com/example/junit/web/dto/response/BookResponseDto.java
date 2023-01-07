package com.example.junit.web.dto.response;

import com.example.junit.domain.Book;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BookResponseDto {
    private Long id;
    private String title;
    private String author;

    @Builder
    public BookResponseDto(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }
}