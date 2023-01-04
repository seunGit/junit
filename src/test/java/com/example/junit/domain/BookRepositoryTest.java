package com.example.junit.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest // DB와 관련된 컴포넌트만 메모리에 로딩
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    // 1. 책 등록
    @Test
    public void save_test(){
        System.out.println("책등록_test 실행");
    }
    // 2. 책 목록보기

    // 3. 책 한건 보기

    // 4. 책 수정

    // 5. 책 삭제
}
