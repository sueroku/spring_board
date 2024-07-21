package com.beyond.board.post.repository;

import com.beyond.board.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
//    jpql을 적용하여 네이밍룰을 통한 방식이 아닌 메서드 생성
//    select p.*, a.* from post p left join author a on p.author_id=a.id;
    @Query("select p from Post p left join fetch p.author") // => select p.*, a.* from post p left join author a on p.author_id=a.id;
    List<Post> findAllFetch();

    //    select p.* from post p left join author a on p.author_id=a.id; == > 조회결과에 author객체 없음. => 만약에 코드에서 author객체 참조에는 똑같은 n+1이슈 발생
//    아래의 join문은 author객체를 통한 조건문으로 post를 filtering 할때 사용 ==> select p.* from post p left join author a on p.author_id=a.id where a.email like 'hong%';
    @Query("select p from Post p left join p.author") //    ==> select p.* from post p left join author a on p.author_id=a.id;
    List<Post> findAllLeftjoin();
}
