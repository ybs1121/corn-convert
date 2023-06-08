package com.example.cornconvert.controller;


import com.example.cornconvert.DynamicScheduler;
import com.example.cornconvert.command.AbstractCommand;
import com.example.cornconvert.command.FINGERCommand;
import com.example.cornconvert.command.KAKAOCommand;
import com.example.cornconvert.dao.EmailDAO;
import com.example.cornconvert.mapper.TestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;
import java.util.List;
import java.util.Properties;


@RestController
@RequiredArgsConstructor
public class TestController {

    private AbstractCommand abstractCommand;

    private final DynamicScheduler dynamicScheduler;
    private final TestMapper mapper;

    @PostMapping("/")
    public String test(@RequestParam(name = "command") String command) throws InterruptedException {

        String response = "OK";

        if(command.equals("KAKAO")){
            abstractCommand = new KAKAOCommand();
            abstractCommand.execute();
        }
        else if(command.equals("FINGER")){
            abstractCommand = new FINGERCommand();
            abstractCommand.execute();
        }


        return response;

    }


    @GetMapping("/rerun")
    public String rerun(){
//        dynamicScheduler.schedulerStart();
        return mapper.test();
    }


    @GetMapping("/send-mail")
    public String sendMail(){

        final String username = "marketing@finger.co.kr";
        final String password = "finger2167!";

        List<EmailDAO> emailList = mapper.getEmailList();


        // 이메일 설정
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "211.111.214.24"); // 이메일 서버 호스트
        props.put("mail.smtp.port", "25"); // 이메일 서버 포트 번호

        // 세션 생성
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // 이메일 생성
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("marketing@finger.co.kr"));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("ybs11225@gmail.com")); // 수신자 이메일 주소
            message.setSubject("프리마켓 서비스 종료 안내"); // 이메일


            String filePath = "C:\\Users\\FINGER\\Desktop\\email.txt";
            StringBuilder stringBuilder = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
//            String content = stringBuilder.toString();

            // HTML 본문 작성
            String htmlBody = stringBuilder.toString();
            message.setContent(htmlBody, "text/html; charset=utf-8");

            // 이메일 전송
            for (EmailDAO emailDAO : emailList) {
                try{

                System.out.println(emailDAO.getEmail());
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailDAO.getEmail()));
                Transport.send(message);
//                Transport.send(message);
                mapper.updateEmail(emailDAO.getEmail());

                Thread.sleep(10000);
                }catch (Exception e){
                    System.out.println("확인필요");
                }
            }

            System.out.println("이메일이 성공적으로 전송되었습니다.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return "OK";
    }


    @GetMapping("/resend-mail")
    public String resendMail(){

        final String username = "marketing@finger.co.kr";
        final String password = "finger2167!";

        List<EmailDAO> emailList = mapper.getEmailList();


        // 이메일 설정
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "211.111.214.24"); // 이메일 서버 호스트
        props.put("mail.smtp.port", "25"); // 이메일 서버 포트 번호

        // 세션 생성
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // 이메일 생성
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("marketing@finger.co.kr"));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("ybs11225@gmail.com")); // 수신자 이메일 주소
            message.setSubject("프리마켓 서비스 종료 안내"); // 이메일


            String filePath = "C:\\Users\\FINGER\\Desktop\\email.txt";
            StringBuilder stringBuilder = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
//            String content = stringBuilder.toString();

            // HTML 본문 작성
            String htmlBody = stringBuilder.toString();
            message.setContent(htmlBody, "text/html; charset=utf-8");

            // 이메일 전송
//            for (EmailDAO emailDAO : emailList) {
                try{

//                    System.out.println(emailDAO.getEmail());
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("12oznqwbzvbqweioqwemk@nate.com"));
                    Transport.send(message);
//                    Transport.send(message);
//                    mapper.updateEmail(emailDAO.getEmail());

                    Thread.sleep(10000);
                }catch (Exception e){
                    System.out.println("확인필요");
                }
//            }

            System.out.println("이메일이 성공적으로 전송되었습니다.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return "OK";
    }
}
