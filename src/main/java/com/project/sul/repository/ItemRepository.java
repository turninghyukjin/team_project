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
        List<Item> findByAbvLessThan(Integer abv);

        // 쿼리 이름 일부
        @Query("select i from Item i where i.itemNm like %:itemNm% order by i.price desc" )
        List<Item> findByItemNmPart(@Param("itemNm") String itemDetail);

        // 재료
        @Query("select i from Item i where i.ingredient like %:ingredient% order by i.price desc" )
        List<Item> findByIngredient(@Param("ingredient") String itemDetail);
}
