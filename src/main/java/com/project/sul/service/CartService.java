package com.project.sul.service;

import com.project.sul.repository.ItemRepository;
import com.project.sul.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional

public class CartService {

    private final ItemRepository itemRepository;

    private final MemberRepository memberRepository;

    private final CartRepository cartRepository;

    private final CartItemRepository


}
