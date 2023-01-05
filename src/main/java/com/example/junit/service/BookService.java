package com.example.junit.service;

import com.example.junit.domain.Book;
import com.example.junit.domain.BookRepository;
import com.example.junit.util.MailSender;
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
    private final MailSender mailSender;

    // 1. 책등록
    @Transactional(rollbackFor = RuntimeException.class)
    public BookResponseDto 책등록하기(BookSaveRequestDto dto){
        Book bookPS = bookRepository.save(dto.toEntity());
        if (bookPS != null) {  // 메일보내기 메서드 호출 (return true or false)
            if (!mailSender.send()) {
                throw new RuntimeException("메일이 전송되지 않았습니다.");
            }
        }
        return bookPS.toDto();
    }

    // 2. 책 목록보기
    public List<BookResponseDto> 책목록보기() {
        List<BookResponseDto> bookResponseDtoList = bookRepository.findAll().stream()
//                .map((bookPS) -> bookPS.toDto())
                .map(Book::toDto)
                .collect(Collectors.toList());

        // print
        bookResponseDtoList.stream().forEach((b)-> {
            System.out.println(b.getId());
            System.out.println(b.getTitle());
            System.out.println("=============== 서비스 레이어");

        });
        return bookResponseDtoList;
    }

    // 3. 책 한건 보기
    public BookResponseDto 책한건보기(Long id){
        Optional<Book> bookOP = bookRepository.findById(id);
        if(bookOP.isPresent()){
            Book bookPS = bookOP.get();
            return bookPS.toDto();
        } else {
            throw new RuntimeException("해당 아이디를 찾을 수 없습니다.");
        }
    }

    // 4. 책 삭제
    @Transactional(rollbackFor = RuntimeException.class)
    public void 책삭제하기(Long id) { // 4
        bookRepository.deleteById(id); // 1, 2, 3
    }

    // 5. 책 수정
    @Transactional(rollbackFor = RuntimeException.class)
    public void 책수정하기(Long id, BookSaveRequestDto dto) {
        Optional<Book> bookOP = bookRepository.findById(id);
        if(bookOP.isPresent()) {
            Book bookPS = bookOP.get();
            bookPS.update(dto.getTitle(), dto.getAuthor());
        } else {
            throw new RuntimeException("해당  아이디를 찾을 수 없습니다.");
        }
    }  // 매서드 종료시에 더티체킹(flush)으로 update 됩니다.
}