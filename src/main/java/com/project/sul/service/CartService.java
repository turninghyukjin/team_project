//package com.project.sul.service;
//
//import com.project.sul.dto.CartDetailDto;
//import com.project.sul.dto.CartItemDto;
//import com.project.sul.dto.CartOrderDto;
//import com.project.sul.dto.OrderDto;
//import com.project.sul.entity.Cart;
//import com.project.sul.entity.CartItem;
//import com.project.sul.entity.Item;
//import com.project.sul.entity.Member;
//import com.project.sul.repository.CartItemRepository;
//import com.project.sul.repository.CartRepository;
//import com.project.sul.repository.ItemRepository;
//import com.project.sul.repository.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//import org.thymeleaf.util.StringUtils;
//
//import javax.persistence.EntityNotFoundException;
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class CartService {
//    private final ItemRepository itemRepository;
//    private final MemberRepository memberRepository;
//    private final CartRepository cartRepository;
//    private final CartItemRepository cartItemRepository;
//    private final OrderService orderService;
//
//    public Long addCart(CartItemDto cartItemDto, String email) {
//        Item item = itemRepository.findById(cartItemDto.getItemId())
//                .orElseThrow(EntityNotFoundException::new);
//        //장바구니에 담을  상품 엔티티 조회
//        Member member = memberRepository.findByEmail(email);
//        // 현재 로그인한 회원 엔티티 조회
//
//        Cart cart = cartRepository.findByMemberId(member.getId());
//        //현재 로그인한 회원의 장바구니 엔티티조회
//        if (cart == null) {
//            cart = Cart.createCart(member);
//            //상품을 처음으로 장바구니에 담을 경우 해당 회원의 장바구니 엔티티 생성
//            cartRepository.save(cart);
//        }
//
//
//        CartItem savedCartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());
//        //현재 상품이 장바구니에 이미들어있는지 조회합니다.
//        if (savedCartItem != null) {
//            savedCartItem.addCount(cartItemDto.getCount());
//            //장바구니에 이미 있는 상품인경우 기존 수량에 현재 장바구니에 담을 수량 만큼을 더해줍니다.
//            return savedCartItem.getId();
//        } else {
//            CartItem cartItem = CartItem.createCartItem(cart, item, cartItemDto.getCount());
//            //장바구니 엔티티, 상품엔티티, 장바구니에 담을 수량을 이용하여 cartItem엔티티를 생성
//            cartItemRepository.save(cartItem);
//            return cartItem.getId();
//        }
//    }
//
//    @Transactional(readOnly = true)
//    public List<CartDetailDto> getCartList(String email) {
//
//        List<CartDetailDto> cartDetailDtoList = new ArrayList<>();
//
//        Member member = memberRepository.findByEmail(email);
//        Cart cart = cartRepository.findByMemberId(member.getId());
//        if (cart == null) {
//            return cartDetailDtoList;
//        }
//
//        cartDetailDtoList = cartItemRepository.findCartDetailDtoList(cart.getId());
//        return cartDetailDtoList;
//    }
//
//
//    public boolean validateCartItem(Long cartItemId, String email) {
//        Member curMember = memberRepository.findByEmail(email);
//        CartItem cartItem = cartItemRepository.findById(cartItemId)
//                .orElseThrow(EntityNotFoundException::new);
//        Member savedMember = cartItem.getCart().getMember();
//
//        if (!StringUtils.equals(curMember.getEmail(), savedMember.getEmail())) {
//            return false;
//        }
//        return true;
//    }
//
//    public void updateCartItemCount(Long cartItemId, int count) {
//        CartItem cartItem = cartItemRepository.findById(cartItemId)
//                .orElseThrow(EntityNotFoundException::new);
//        cartItem.updateCount(count);
//    }
//
//    public void deleteCartItem(Long cartItemId) {
//        CartItem cartItem = cartItemRepository.findById(cartItemId)
//                .orElseThrow(EntityNotFoundException::new);
//        cartItemRepository.delete(cartItem);
//    }
//
////    public Long orderCartItem(List<CartOrderDto> cartOrderDtoList, String email) {
////        List<OrderDto> orderDtoList = new ArrayList<>();
////
////        for (CartOrderDto cartOrderDto : cartOrderDtoList) {
////            CartItem cartItem = cartItemRepository
////                    .findById(cartOrderDto.getCartItemId())
////                    .orElseThrow(EntityNotFoundException::new);
////
////            OrderDto orderDto = new OrderDto();
////            orderDto.setItemId(cartItem.getItem().getId());
////            orderDto.setCount(cartItem.getCount());
////            orderDtoList.add(orderDto);
////        } //장바구니 페이지에서 전달받은 주문상품번호를 이용하여 주문로직에 전달할 orderDto 객체를 만든다.
////
////        Long orderId = orderService.order(orderDtoList, email);
////        //장바구니에 담은 상품을 주문하도록 주문로직을 호출 한다.
////
////        for (CartOrderDto cartOrderDto : cartOrderDtoList) {
////            CartItem cartItem = cartItemRepository
////                    .findById(cartOrderDto.getCartItemId())
////                    .orElseThrow(EntityNotFoundException::new);
////            cartItemRepository.delete(cartItem);
////        } //장바구니 비우기
////
////        return orderId;
////
////    }
//
//
//}