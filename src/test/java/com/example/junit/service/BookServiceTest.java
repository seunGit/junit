package com.example.junit.service;

import com.example.junit.domain.Book;
import com.example.junit.domain.BookRepository;
import com.example.junit.util.MailSender;
import com.example.junit.web.dto.response.BookResponseDto;
import com.example.junit.web.dto.request.BookSaveRequestDto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;

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

        // v build.gradle 의존성 주입 - testImplementation("org.assertj:assertj-core:3.23.1")
        assertThat(bookResponseDto.getTitle()).isEqualTo(dto.getTitle());
        assertThat(bookResponseDto.getAuthor()).isEqualTo(dto.getAuthor());
    }
    @Test
    public void 책목록보기_테스트() {
        // given(파라미터로 들어올 데이터)

        // stub(가설)
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "junit강의", "seunGit"));
        books.add(new Book(2L, "spring강의", "seunGit2"));
        when(bookRepository.findAll()).thenReturn(books);

        // when(실행)
        List<BookResponseDto> bookResponseDtoList = bookService.책목록보기();

        // print
        bookResponseDtoList.stream().forEach((b)-> {
            System.out.println("================= 테스트");
            System.out.println(b.getId());
            System.out.println(b.getTitle());
        });

        // then(검증)
        assertThat(bookResponseDtoList.get(0).getTitle()).isEqualTo("junit강의");
        assertThat(bookResponseDtoList.get(0).getAuthor()).isEqualTo("seunGit");
        assertThat(bookResponseDtoList.get(1).getTitle()).isEqualTo("spring강의");
        assertThat(bookResponseDtoList.get(1).getAuthor()).isEqualTo("seunGit2");
    }

    @Test
    public void 책한건보기_테스트() {
        // given
        Long id = 1L;

        // stub
        Book book = new Book(1L, "junit강의", "seunGit");
        Optional<Book> bookOP = Optional.of(book);
        when(bookRepository.findById(id)).thenReturn(bookOP);

        // when
        BookResponseDto bookResponseDto = bookService.책한건보기(id);

        // then
        assertThat(bookResponseDto.getTitle()).isEqualTo(book.getTitle());
        assertThat(bookResponseDto.getAuthor()).isEqualTo(book.getAuthor());

        System.out.println("bookResponseDto.getTitle() : " + bookResponseDto.getTitle());
        System.out.println("bookResponseDto.getAuthor() : " + bookResponseDto.getAuthor());
        System.out.println("book.getTitle() : " + book.getTitle());
        System.out.println("book.getAuthor() : " + book.getAuthor());
    }

    @Test
    public void 책수정하기_테스트() {
        // given
        Long id = 1L;
        BookSaveRequestDto dto = new BookSaveRequestDto();
        dto.setTitle("spring강의");  // junit 강의
        dto.setAuthor("seunGit2");  // seunGit
        // stub
        Book book = new Book(1L, "junit강의", "seunGit");
        Optional<Book> bookOP = Optional.of(book);
        when(bookRepository.findById(id)).thenReturn(bookOP);

        // when
        BookResponseDto bookResponseDto = bookService.책수정하기(id, dto);

        // then
        assertThat(bookResponseDto.getTitle()).isEqualTo(dto.getTitle());
        assertThat(bookResponseDto.getAuthor()).isEqualTo(dto.getAuthor());
    }
}