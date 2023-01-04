package com.example.junit.service;

import static org.assertj.core.api.Assertions.*;

import com.example.junit.domain.BookRepository;
import com.example.junit.util.MailSender;
import com.example.junit.web.dto.BookResponseDto;
import com.example.junit.web.dto.BookSaveRequestDto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

//    @Autowired // DI
//    private BookRepository bookRepository;
//     문제점 -> 서비스만 테스트하고 싶은데, 레포지토리 레이어가 함께 테스트 된다는 점...!
//               가짜 Repository, 가짜 MailSender...(익명 클래스) Mockito활용 : 가짜 객체 보관 환경

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private MailSender mailSender;
    @Test
    public void 책등록하기_테스트() {
        // given
        BookSaveRequestDto dto = new BookSaveRequestDto();
        dto.setTitle("junit강의");
        dto.setAuthor("seunGit");

        // stub (가설)
        when(bookRepository.save(any())).thenReturn(dto.toEntity());
        when(mailSender.send()).thenReturn(true);

        // when
        BookResponseDto bookResponseDto = bookService.책등록하기(dto);

        // then
//        assertEquals(dto.getTitle(), bookResponseDto.getTitle());
//        assertEquals(dto.getAuthor(), bookResponseDto.getAuthor());
        assertThat(dto.getTitle()).isEqualTo(bookResponseDto.getTitle());
        assertThat(dto.getAuthor()).isEqualTo(bookResponseDto.getAuthor());
    }
}