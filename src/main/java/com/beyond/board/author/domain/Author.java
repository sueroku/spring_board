package com.beyond.board.author.domain;

import com.beyond.board.author.dto.AuthorListResDto;
import com.beyond.board.author.dto.AuthorUpdateReqDto;
import com.beyond.board.common.BaseTimeEntity;
import com.beyond.board.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
//import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Builder // builder 어노테이션을 통해 매개변수의 순서, 매개변수의 개수 등을 유연하게 세팅하여 생성자로서 활용
@AllArgsConstructor // builder와 같이 가야한다.
@NoArgsConstructor // 얘도
public class Author extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 20)
    private String name;
    @Column(nullable = false, length = 30, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
//    일반적으로 부모 엔티티(자식 객체에 영향을 끼칠 수 있는 엔티티)에 cascade 옵션을 설정
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL) // remove persist 보려고 all로 해놓았다. 실무에서  all은 많이 안쓴다. 나중에는 delYn으로 처리할때 persist는 많이 쓴다.
    private List<Post> posts; // = new ArrayList<>(); #071901 해결 첫번째시도


//    위의 생성자 세트 어노테이션(@B ~ @N)이 만약에 없다면
//    @Builder // 이런식으로...   // create 때 썼다...
//    public Author(String name, String email, String password, Role role){
//        this.name = name;
//        this.email = email;
//        this.password = password;
//        this.role = role;
//    }

    public AuthorListResDto listFromEntity(){
//        return new AuthorListResDto(this.id, this.name, this.email);
        return new AuthorListResDto().builder()
                .id(this.id).email(this.email).name(this.name).build();
    }

    public void updateAuthor(AuthorUpdateReqDto dto){
        this.name = dto.getName();
        this.password = dto.getPassword();
    }


//    public AuthorDetResDto detFromEntity(){
//        LocalDateTime localDateTime = this.getCreatedTime();
//        String date = localDateTime.getYear() + "년" + localDateTime.getMonthValue() + "월" + localDateTime.getDayOfMonth() + "일";
//        return new AuthorDetResDto // 이것도 빌더로
//    } // --> AuthorDetResDto 안으로 옮김
}