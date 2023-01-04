package com.example.junit.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest // DB와 관련된 컴포넌트만 메모리에 로딩
public class BookRepositoryTest {

    @Autowired  // DI
    private BookRepository bookRepository;

//    @BeforeAll // 테스트 시작전에 한번만 실행
    @BeforeEach // 각 테스트 시작전에 한번씩 실행
    public void 데이터준비() {
        System.out.println("================================================");
        String title = "junit";
        String author = "seunGit2";
        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();
        bookRepository.save(book);
    } // 트랜잭션 종료 돼었다면 말이안됨.
    // 가정 1: [ 데이터준비() + 1.책등록 ] (T) , [ 데이터준비() + 2.책목록보기 ] (T) -> 사이즈 1  (검증완료)
    // 가정 2: [ 데이터준비() + 1.책등록 + 데이터준비() + 2.책목록보기 ] (T) -> 사이즈 2  (검증실패)

    // 1. 책 등록
    @Test
    public void save_test(){
        // given (데이터 준비)
        String title = "junit5";
        String author = "seunGit";
        Book book = Book.builder()
                .title(title)
                .author(author)
                .build();
        // when ( 테스트 실행 )
        Book bookPS = bookRepository.save(book);
        // then (검증)
        assertEquals(title, bookPS.getTitle());
        assertEquals(author, bookPS.getAuthor());

        System.out.println("bookPS.getTitle = " + bookPS.getTitle());
        System.out.println("bookPS.getAuthor = " + bookPS.getAuthor());
    } // 트랜잭션 종료 (저장된 데이터를 초기화함)

    // 2. 책 목록보기
    @Test
    public void 책목록보기_test() {

        // given (데이터 준비)
        String title = "junit";
        String author = "seunGit2";

        // when ( 테스트 실행 )
        List<Book> booksPS = bookRepository.findAll();

        System.out.println("사이즈 : =========================================================== : " + booksPS.size());
        
        // then (검증)
        assertEquals(title, booksPS.get(0).getTitle());
        assertEquals(author, booksPS.get(0).getAuthor());
        System.out.println("booksPS.size() = " + booksPS.size());

    }
    // 3. 책 한건 보기

    @Test
    public void 책한건보기_test() {
        // given (데이터 준비)
        String title = "junit";
        String author = "seunGit2";

        // when ( 테스트 실행 )
        Book bookPS = bookRepository.findById(1L).get();

        // then (검증)
        assertEquals(title, bookPS.getTitle());
        assertEquals(author, bookPS.getAuthor());
    }
    // 4. 책 수정

    // 5. 책 삭제
} // 트랜잭션 종료 (저장된 데이터를 초기화함)
