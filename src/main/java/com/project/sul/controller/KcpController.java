package com.project.sul.controller;

import com.project.sul.service.OrderService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

@Controller
public class KcpController {

    private final IamportClient iamportClient;
    //토큰 발급을 위해 아임포트에서 제공해주는 rest api 사용.(gradle로 의존성 추가)

    private final OrderService orderService;

    @Autowired
    public KcpController(OrderService orderService) {
        this.iamportClient = new IamportClient("1570777386811537",
                "JymvxAKxb1rT6t8LyShl5aQnIFdWHTd1KbBRsQ1JBbA6WyRVNXhgdNTBzG2FvOrWHslvYs64u0oAAehL");
        this.orderService = orderService;
    }//생성자로 rest api key와 secret을 입력해서 토큰 바로생성.

    @ResponseBody
    @RequestMapping(value = "/Payments", method = RequestMethod.POST)
    public IamportResponse<Payment> paymentByImpUid(
            Model model,
            Locale locale,
            HttpSession session,
            @PathVariable(value = "imp_uid") String imp_uid) throws IamportResponseException, IOException {
        return iamportClient.paymentByImpUid(imp_uid);
    }


    public IamportResponse<Payment> paymentLookup(String impUid) throws IamportResponseException, IOException{
        return iamportClient.paymentByImpUid(impUid);
    }
    // impUid로 결제내역 조회.


    @GetMapping("/Payments")
    @PostMapping("/Payments")
    public String showKcpPaymentPage() {
        return "KcpPayment";
    }
    //showKcpPaymentPage 메서드: GET 및 POST 방식의 /Payments 경로에 대한 요청을 처리하는 메서드입니다.
    // 이 메서드는 "KcpPayment"라는 뷰 페이지를 반환합니다.

}
