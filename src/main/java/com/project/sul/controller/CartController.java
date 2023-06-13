//package com.project.sul.controller;
//
//import com.project.sul.dto.CartDetailDto;
//import com.project.sul.dto.CartItemDto;
//import com.project.sul.dto.CartOrderDto;
//import com.project.sul.service.CartService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.FieldError;
//import org.springframework.web.bind.annotation.*;
//
//import javax.validation.Valid;
//import java.security.Principal;
//import java.util.List;
//
//@Controller
//@RequiredArgsConstructor
//public class CartController {
//    private final CartService cartService;
//
//    @PostMapping(value = "/cart")
//    public @ResponseBody ResponseEntity order(@RequestBody @Valid CartItemDto cartItemDto,
//                                              BindingResult bindingResult, Principal principal) {
//
//        //장바구니에 담을 상품 정보를 받는 cartItemDto 객체에 데이터 바인딩(변수와 값을) 시 에러가 있는지 검사합니다.
//        if (bindingResult.hasErrors()) {
//            StringBuilder sb = new StringBuilder(); //문자열을 추가하거나 수정할때 사용
//            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//
//            for (FieldError fieldError : fieldErrors) {
//                sb.append(fieldError.getDefaultMessage());
//            }
//
//            return new ResponseEntity<String>(sb.toString(), HttpStatus.BAD_REQUEST);
//        }
//        String email = principal.getName();
//        Long cartItemId;
//
//        try {
//            cartItemId = cartService.addCart(cartItemDto, email);
//        } catch (Exception e) {
//            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<Long>(cartItemId, HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/cart")
//    public String orderHist(Principal principal, Model model) {
//        List<CartDetailDto> cartDetailList = cartService.getCartList(principal.getName());
//        model.addAttribute("cartItems", cartDetailList);
//        return "cart/cartList";
//    }
//
//    //update와 유사
//    @PatchMapping(value = "/cartItem/{cartItemId}")
//    public @ResponseBody ResponseEntity updateCartItem(@PathVariable("cartItemId") Long cartItemId, int count, Principal principal) {
//
//        if (count <= 0) {
//            return new ResponseEntity<String>("최소 1개 이상 담아주세요", HttpStatus.BAD_REQUEST);
//        } else if (!cartService.validateCartItem(cartItemId, principal.getName())) {
//            return new ResponseEntity<String>("수정 권한이 없습니다.", HttpStatus.FORBIDDEN);
//        }
//
//        cartService.updateCartItemCount(cartItemId, count);
//        return new ResponseEntity<Long>(cartItemId, HttpStatus.OK);
//    }
//
//    @DeleteMapping(value = "/cartItem/{cartItemId}")
//    public @ResponseBody ResponseEntity deleteCartItem(@PathVariable("cartItemId") Long cartItemId, Principal principal) {
//
//        if (!cartService.validateCartItem(cartItemId, principal.getName())) {
//            return new ResponseEntity<String>("수정 권한이 없습니다.", HttpStatus.FORBIDDEN);
//        }
//
//        cartService.deleteCartItem(cartItemId);
//
//        return new ResponseEntity<Long>(cartItemId, HttpStatus.OK);
//    }
//    @PostMapping(value = "/cart/orders")
//    public @ResponseBody ResponseEntity orderCartItem(@RequestBody CartOrderDto cartOrderDto, Principal principal){
//
//        List<CartOrderDto> cartOrderDtoList = cartOrderDto.getCartOrderDtoList();
//
//        if(cartOrderDtoList == null || cartOrderDtoList.size() == 0){
//            return new ResponseEntity<String>("주문할 상품을 선택해주세요", HttpStatus.FORBIDDEN);
//        }
//
//        for (CartOrderDto cartOrder : cartOrderDtoList) {
//            if(!cartService.validateCartItem(cartOrder.getCartItemId(), principal.getName())){
//                return new ResponseEntity<String>("주문 권한이 없습니다.", HttpStatus.FORBIDDEN);
//            }
//        }
//
//        Long orderId = cartService.orderCartItem(cartOrderDtoList, principal.getName());
//        //현재로그인한 사용자
//        return new ResponseEntity<Long>(orderId, HttpStatus.OK);
//    }
//
//}
//
//
//
//
//
//
//
//
//
//
//
