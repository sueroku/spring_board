//package com.beyond.board.post.service;
//
//import org.springframework.batch.core.JobParameter;
//import org.springframework.batch.core.JobParameters;
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.util.HashMap;
//import java.util.Map;
//
//// batch 를 돌리기 위한 스케쥴러 정의 // 암튼 배치와 스케줄러 완전히 다른게 아니라 배치가 스케줄러를 이용한다고!
//@Component
//public class PostBatchScheduler {
//
//    @Autowired
//    private JobLauncher jobLauncher;
//    @Autowired // 싱글톤으로 주입받기 그래요 싱글톤으로 안만들어져있어서
//    private PostJobConfiguration postJobConfiguration;
//
//    @Scheduled(cron = "0 0/1 * * * *")
//    public void batchScheduler(){
////        PostJobConfiguration postJobConfiguration = new PostJobConfiguration(); //  이거 아니래
//        Map<String, JobParameter> configMap = new HashMap<>();
//        configMap.put("time", new JobParameter(System.currentTimeMillis()));
//        JobParameters jobParameters = new JobParameters(configMap);
//        try{
//            jobLauncher.run(postJobConfiguration.excuteJob(), jobParameters);
//        }catch (Exception e){ // 원래는 하나하나 해서 찾아 내야한다. 이렇게 하면 안된다.
//            e.printStackTrace();
//        }
//    }
//}
