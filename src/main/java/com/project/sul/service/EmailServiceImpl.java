package com.project.sul.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    @Autowired
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;
    private String authNum;

//    public static final String ePassword = createKey();

    public void createKey() {
        StringBuffer key = new StringBuffer();
        Random random = new Random();

        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(3);

            switch (index) {
                case 0:
                    key.append((char) ((int) (random.nextInt(26)) + 97));
                    break;
                case 1:
                    key.append((char) ((int) (random.nextInt(26)) + 65));
                    break;
                case 2:
                    key.append((random.nextInt(10)));
                    break;
            }
        }
        authNum = key.toString();
    }

    //메일 양식 작성
    public MimeMessage createEmailForm(String email) throws MessagingException, UnsupportedEncodingException {

        createKey();
        String setFrom = "sul.teamproject@gmail.com";
        String toEmail = email;
        String title = "Sul 회원가입 인증 이메일";

        MimeMessage message = javaMailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, email); //보낼 이메일 설정
        message.setSubject(title); //제목 설정
        message.setFrom(setFrom); //보내는 이메일
        message.setText(setContext(authNum), "utf-8", "html");

        return message;
    }
//
//    private MimeMessage createMessage(String to) throws Exception {
//        System.out.println("보내는 대상 : " + to);
//        System.out.println("인증 번호 : " + ePassword);
//
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//
//        mimeMessage.addRecipients(RecipientType.TO, to);
//        mimeMessage.setSubject("Sul 회원가입 이메일 인증");
//
//        String msg="";
//        msg+= "<div style='margin:100px;'>";
//        msg+= "<h1> 안녕하세요 Sul입니다. </h1>";
//        msg+= "<br>";
//        msg+= "<p>아래 코드를 회원가입 창으로 돌아가 입력해주세요<p>";
//        msg+= "<br>";
//        msg+= "<p>감사합니다!<p>";
//        msg+= "<br>";
//        msg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
//        msg+= "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
//        msg+= "<div style='font-size:130%'>";
//        msg+= "CODE : <strong>";
//        msg+= ePassword+"</strong><div><br/> ";
//        msg+= "</div>";
//
//        mimeMessage.setText(msg, "utf-8", "html");//내용
//        mimeMessage.setFrom(new InternetAddress("sul.teamproject@gmail.com","Sul"));//보내는 사람
//
//        return mimeMessage;
//    }

    @Override
    public String sendSimpleMessage(String to) throws Exception {
        MimeMessage mimeMessage = createEmailForm(to);
        try {
            javaMailSender.send(mimeMessage);
        } catch (MailException me) {
            me.printStackTrace();
            throw new IllegalArgumentException();
        }
        return authNum;
    }

    //타임리프를 이용한 context 설정
    public String setContext(String code) {
        Context context = new Context();
        context.setVariable("code", code);
        return templateEngine.process("emailConfirm", context);
    }
}
