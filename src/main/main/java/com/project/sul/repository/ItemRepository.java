package com.project.sul.repository;

import com.project.sul.dto.ItemSearchDto;
import com.project.sul.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long>, QuerydslPredicateExecutor<Item>, ItemRepositoryCustom {
        List<Item> findByItemNm(String itemNm);

        List<Item> findByPriceLessThan(Integer price);
        List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);

//        쿼리...
        @Query("select i from Item i where i.itemDetail like %:itemDetail% order by i.price desc" )
        List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);
}
