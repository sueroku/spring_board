package com.beyond.board.author.repository;

import com.beyond.board.author.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
//    findBy컬럼명의 규칙으로 자동으로 where 조건문을 사용한 쿼리 생성
//    그외 : findBy컬럼1And컬럼2(findByNameAndEmail), findBy컬럼1Or컬럼2
//    findByAgeBetween(int satrt, int end) // start와 end는 변수명이라 아무거나 상관 없음
//    findByAgeLessThan(int age)  ,  findByAgeGreaterThan(int age)  ,  findByAgeGreaterThanEqual(int age)  //  이상인지 초과인지 이하인지 미만인지...
//    findByAgeIsNull, findByAgeIsNotNull   //  List<Author> findByNameIsNull();
//    List<Author> findAllOrderByEmail();
    Optional<Author> findByEmail(String email);

}
