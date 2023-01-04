package com.example.junit.service;

import com.example.junit.domain.BookRepository;
import com.example.junit.util.MailSenderStub;
import com.example.junit.web.dto.BookResponseDto;
import com.example.junit.web.dto.BookSaveRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class BookServiceTest {

    @Autowired // DI
    private BookRepository bookRepository;
    // 문제점 -> 서비스만 테스트하고 싶은데, 레포지토리 레이어가 함께 테스트 된다는 점...!
//               가짜 Repository, 가짜 MailSender... Mockito활용 : 가짜 객체 보관 환경
    @Test
    public void 책등록하기_테스트() {
        // given
        BookSaveRequestDto dto = new BookSaveRequestDto();
        dto.setTitle("junit강의");
        dto.setAuthor("seunGit");

        // stub
        MailSenderStub mailSenderStub = new MailSenderStub();
        // 가짜로 bookRepository 만들기 !!

        // when
        BookService bookService = new BookService(bookRepository, mailSenderStub);
        BookResponseDto bookResponseDto = bookService.책등록하기(dto);

        // then
        assertEquals(dto.getTitle(), bookResponseDto.getTitle());
        assertEquals(dto.getAuthor(), bookResponseDto.getAuthor());
    }
}
