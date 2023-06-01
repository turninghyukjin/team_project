package com.project.sul.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

public class ItemRepositoryCustomImpl {
    // 우린 고객페이지는 하나로 갈거라 인터페이스+페이져블 처리 안하긴 했는데 인터페이스가 추후에 필요하면
    // interface ItemRepositoryCustom 를 만들어서 상속받게요.. 아직까진 모르겠어서 뒀어요
        private JPAQueryFactory queryFactory;

        public ItemRepositoryCustomImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }
}
