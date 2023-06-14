package com.project.sul.repository;

import com.project.sul.dto.ItemSearchDto;
import com.project.sul.entity.Item;
import com.project.sul.entity.QItem;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ItemRepositoryCustomImpl implements ItemRepositoryCustom{
    private JPAQueryFactory queryFactory;
    public ItemRepositoryCustomImpl(EntityManager em){

        this.queryFactory = new JPAQueryFactory(em);
    }

    // 타입
    private BooleanExpression searchType(String type) {
        if (type != null && !type.isEmpty()) {
            switch (type) {
                case "A" : return QItem.item.type.eq("A"); // 일단은 a로 맞췄어요
                case "B" : return QItem.item.type.eq("B");
                case "C" : return QItem.item.type.eq("C");
                case "D" : return QItem.item.type.eq("D");
                default: return null;
            }
        }
        return Expressions.asBoolean(true);
    }

    // 맛
    private BooleanExpression searchAbvDg(int abv) {
        int abvDg = (int)(abv/10);

        if(abvDg<=3){
            int min = (abvDg - 1) * 10;
            int max = abvDg * 10;
            return QItem.item.abv.between(min, max);
        }
        else if(abvDg>3 )return QItem.item.abv.between(30,60);
        return Expressions.asBoolean(true);
    }

    private BooleanExpression searchSweetDg(int sweetness) {
        switch (sweetness) {
            case 1:
                return QItem.item.sweetness.eq(1);
            case 2:
                return QItem.item.sweetness.eq(2);
            case 3:
                return QItem.item.sweetness.eq(3);
            default:
                return Expressions.asBoolean(true);
        }
    }
    private BooleanExpression searchSournessDg(int sourness) {
        switch (sourness) {
            case 1:
                return QItem.item.sourness.eq(1);
            case 2:
                return QItem.item.sourness.eq(2);
            case 3:
                return QItem.item.sourness.eq(3);
            default:
                return Expressions.asBoolean(true);
        }
    }
    private BooleanExpression searchSparklingDg(int sparkling) {
        switch (sparkling) {
            case 1:
                return QItem.item.sparkling.eq(1);
            case 2:
                return QItem.item.sparkling.eq(2);
            case 3:
                return QItem.item.sparkling.eq(3);
            default:
                return Expressions.asBoolean(true);
        }
    }

    // 이름
    private BooleanExpression searchByLike(String itemNm, String searchQuery) {
        return QItem.item.itemNm.likeIgnoreCase("%" + searchQuery + "%");
    }

    @Override
    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
        List<Item> content = queryFactory
                .selectFrom(QItem.item)
                .where(searchType(itemSearchDto.getType()),
                        searchAbvDg(itemSearchDto.getAbv()),
                        searchSweetDg(itemSearchDto.getSweetness()),
                        searchSournessDg(itemSearchDto.getSourness()),
                        searchSparklingDg(itemSearchDto.getSparkling()),
                        searchByLike(itemSearchDto.getItemNm(), itemSearchDto.getSearchQuery())
                )
                .orderBy(QItem.item.id.desc())
                 .offset(pageable.getOffset())
                 .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(content, pageable, content.size());
    }
}
