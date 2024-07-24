package com.beyond.board.post.service;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.service.AuthorService;
import com.beyond.board.post.domain.Post;
import com.beyond.board.post.dto.PostDetResDto;
import com.beyond.board.post.dto.PostListResDto;
import com.beyond.board.post.dto.PostSaveReqDto;
import com.beyond.board.post.dto.PostUpdateReqDto;
import com.beyond.board.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional (readOnly = true)
public class PostService {
    private final PostRepository postRepository;
    private final AuthorService authorService; // post에 author넣을래 ver1  // 누가 author객체 찾을래? repo 부를건지~ service 부를건지~ 잘고려 근데 여기서는 서비스로 하는 이유 간단한 서비스이기도 하고 레포에서 가져오는 경우 이미 어떠서비스에서 잡은 걸 또 반복하고 있고.. 뭐 복잡해.... 잘 다시 생각해봐.....// 근데 순환참조 걸릴수도 있어요.
//    (author)Service 또는 Repository를 autowired할지는 상황에 따라 다르나,
//    service 레벨에서 코드가 고도화되어 있고, 코드의 중복이 심할 경우, service레이어를 autowired
//    그러나, 순환참조가 발생할 경우에는 repo~~ 를 autowired  // 그때그때 달라요..
    @Autowired
    public PostService(PostRepository postRepository, AuthorService authorService) {
        this.postRepository = postRepository;
        this.authorService = authorService;
    }

//    authorService에서 author 객체를 찾아 post의 toEntity에 넘기고,
//    jpa가 author객체에서 author_id를 찾아 db에는 author_id가 들어감.
//    @Transactional
//    public Post postCreate(PostSaveReqDto dto){
//        Author author = authorService.authorFindByEmail(dto.getEmail());
//        Post savePost = postRepository.save(dto.toEntity(author));
//        return savePost;
//    //        Post post = dto.toEntity();
//    //        Post savedPost = postRepository.save(post);
//    //        return savedPost;
//    }

    @Transactional
    public Post postCreate(PostSaveReqDto dto){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Author author = authorService.authorFindByEmail(email); // Author author = authorService.authorFindByEmail(dto.getEmail);
        String appointment = null;
        LocalDateTime appointmentTime = null;
        if(dto.getAppointment().equals("Y") && !dto.getAppointmentTime().isEmpty()){
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            appointmentTime = LocalDateTime.parse(dto.getAppointmentTime(),dateTimeFormatter);
            LocalDateTime now = LocalDateTime.now();
            if(appointmentTime.isBefore(now)){ // 어포인트타임이 나우보다 이전이면
                throw new IllegalArgumentException("시간입력이 잘못되었습니다.");
            }
            System.out.println(dto);
        }
        Post savePost = postRepository.save(dto.toEntity(author, appointmentTime));
        return savePost;
    }


//    public List<PostListResDto> postList(){
//        List<PostListResDto> postResDtos = new ArrayList<>();
////        List<Post> posts = postRepository.findAll();
////        List<Post> posts = postRepository.findAllFetch();
//        List<Post> posts = postRepository.findAllLeftjoin();
//        for(Post p : posts){
//            postResDtos.add(p.listFromEntity());
//        }
//        return postResDtos;
//    } 기존 버전


    public Page<PostListResDto> postList(Pageable pageable){
//        Page<Post> posts = postRepository.findAll(pageable);
        Page<Post> posts = postRepository.findByAppointment(pageable, "N");
        Page<PostListResDto> postListResDtos = postListResDtos = posts.map(a->a.listFromEntity());
        return postListResDtos;
    }


    public Page<PostListResDto> postListPage(Pageable pageable){
        Page<Post> posts = postRepository.findAll(pageable);
        Page<PostListResDto> postListResDtos = posts.map(a->a.listFromEntity());
        return postListResDtos;
    } // restfull 방식

    public PostDetResDto postDetail(Long id){
        Post post = postRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Post is not found!"));
        return post.detFromEntity();
    }

    @Transactional
    public void postDelete(Long id){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Post post = postRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Post is not found"));
        if(!post.getAuthor().getEmail().equals(email)){
            throw new IllegalArgumentException("본인의 게시글이 아닙니다.");
        }
        postRepository.deleteById(id);
    }

    @Transactional
    public void postUpdate(Long id, PostUpdateReqDto dto){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Post post = postRepository.findById(id).orElseThrow(()->new EntityNotFoundException("Post is not found!"));
        if(!post.getAuthor().getEmail().equals(email)){
            throw  new IllegalArgumentException("본인의 게시글이 아닙니다.");
        }
        post.updatePost(dto);
        postRepository.save(post);
    }
}
