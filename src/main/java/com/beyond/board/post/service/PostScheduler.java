//package com.beyond.board.post.service;
//
//import com.beyond.board.post.domain.Post;
//import com.beyond.board.post.repository.PostRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.time.LocalDateTime;
//
//@Component
//public class PostScheduler {
//    private final PostRepository postRepository;
//    @Autowired
//    public PostScheduler(PostRepository postRepository) {
//        this.postRepository = postRepository;
//    }
//
////    아래의 스케줄의 cron부는 각 자리마다 "초 분 시간 일 월 요일"을 의미
////    예를 들어) * * * * * * : 매일 매시 매분 매초마다 시간 (체크)
////    예를 들어) 0 0 * * * * : 매일 매시 0분 0초에 시간 (체크)
////    예를 들어) 0 0 11 * * * : 매일 11시에 시간 (체크)
////    에를 들어) 0 0/1 * * * * : 매일 매시 1분마다
////    에를 들어) 0 1 * * * * :   매일 매시 1분에
//    @Scheduled(cron = "0 0/1 * * * *") // 스케줄러로 쓰겠다 선언. 중요!
//    @Transactional
//    public void postSchedule(){
//        System.out.println("==예약글쓰기 스케쥴러 시작==");
//        Page<Post> posts = postRepository.findByAppointment(Pageable.unpaged(),"Y");
//        for(Post p : posts){
//            if(p.getAppointmentTime().isBefore(LocalDateTime.now())){
//                p.updatePostAppointment("N"); // 세이브 안해줘도 변경사항에 대한 걸 알아서 해준다. = 더티체킹 by @Transactional
//            }
//        }
//    }
//
//
//}
