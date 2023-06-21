package com.project.sul.repository;

import com.project.sul.constant.ItemSellStatus;
import com.project.sul.dto.ItemDto;
import com.project.sul.dto.ItemSearchDto;
import com.project.sul.dto.QItemDto;
import com.project.sul.entity.Item;
import com.project.sul.entity.QItem;
import com.project.sul.entity.QItemImg;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;

@Repository
public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {
    private JPAQueryFactory queryFactory;

    public ItemRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    // 품절여부
    private BooleanExpression searchStatusEq(ItemSellStatus itemSellStatus){
        return itemSellStatus == null ?
                null : QItem.item.itemSellStatus.eq(itemSellStatus);
    }

    // 타입
    private BooleanExpression searchType(String type) {
        if( type == null ){
            return Expressions.asBoolean(true);
        }
        switch (type) {
            case "A":
                return QItem.item.type.eq("A");
            case "B":
                return QItem.item.type.eq("B");
            case "C":
                return QItem.item.type.eq("C");
            case "D":
                return QItem.item.type.eq("D");
            default:
                return Expressions.asBoolean(true);
        }
    }

    private BooleanExpression searchByLike(String itemNm, String searchQuery) {
        if (itemNm != null && !itemNm.isEmpty() && searchQuery != null && !searchQuery.isEmpty()) {
            return QItem.item.itemNm.likeIgnoreCase("%" + searchQuery + "%");
        }
        return Expressions.asBoolean(true);
    }

    // 맛
    private BooleanExpression searchAbvDg(Integer abv) {
        if(null == abv){
            return QItem.item.abv.between(0, 60);
        }
        if(abv<=3){
            int min = (abv-1)*10; // 0/10/20
            int max = (abv * 10) -1; // 9/19/29까지
            return QItem.item.abv.between(min, max);
        } else if (abv > 3) return QItem.item.abv.between(30, 60);
        return QItem.item.abv.between(0, 60);
    }

    private BooleanExpression searchSweetDg(Integer sweetness) {
        if ( sweetness == null ){
            return Expressions.asBoolean(true);
        }
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

    private BooleanExpression searchSournessDg(Integer sourness) {
        if ( sourness == null ){
            return Expressions.asBoolean(true);
        }
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

    private BooleanExpression searchSparklingDg(Integer sparkling) {
        if ( sparkling == null ){
            return Expressions.asBoolean(true);
        }
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

//    private BooleanExpression searchSparklingDg(Collection<Integer> sparklingValues) {
//        return sparklingValues == null || sparklingValues.isEmpty()
//                ? Expressions.asBoolean(true)
//                : QItem.item.sparkling.in(sparklingValues);
//    }
//    private BooleanExpression searchSparklingDg(int sparklingValue) {
//        return sparklingValue == 0
//                ? Expressions.asBoolean(true)
//                : QItem.item.sparkling.eq(sparklingValue);
//    }

    // 이름


    // 관리자
//    @Override
//    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
//
//        List<Item> content = queryFactory
//            .selectFrom(QItem.item)
//            .where(searchType(itemSearchDto.getType()), // 타입
//                searchAbvDg(itemSearchDto.getAbv()),    // 맛
//                searchSweetDg(itemSearchDto.getSweetness()),
//                searchSournessDg(itemSearchDto.getSourness()),
//                searchSparklingDg(itemSearchDto.getSparkling()),
//                searchByLike(itemSearchDto.getItemNm(), // 이름
//                itemSearchDto.getSearchQuery())     // 서치쿼리
//            )
//            .orderBy(QItem.item.id.desc()) // 정렬
//            .offset(pageable.getOffset())
//            .limit(pageable.getPageSize())
//            .fetch();
//        return new PageImpl<>(content, pageable, content.size());
//    }

    @Override
    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
        JPAQuery<Item> query = queryFactory
                .selectFrom(QItem.item)
                .where(
                        searchType(itemSearchDto.getType()),
                        searchAbvDg(itemSearchDto.getAbv()),
                        searchSweetDg(itemSearchDto.getSweetness()),
                        searchSournessDg(itemSearchDto.getSourness()),
                        searchSparklingDg(itemSearchDto.getSparkling()),
                        searchByLike(itemSearchDto.getItemNm(), itemSearchDto.getSearchQuery())
                )
                .orderBy(QItem.item.id.desc());

        List<Item> content = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = query.fetchCount(); // Get the total count of items

        return new PageImpl<>(content, pageable, total);
    }





    // 고객
    @Override
    public Page<ItemDto> getItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
        QItem item = QItem.item;
        QItemImg itemImg = QItemImg.itemImg;


//        List<YourResultDtoClass> result = queryFactory
//                .select(Projections.constructor(YourResultDtoClass.class,
//                        item.itemId,
//                        item.itemNm,
//                        item.price,
//                        item.avgStar,
//                        item.numComment,
//                        itemImg.imgUrl))
//                .from(item)
//                .leftJoin(itemImg)
//                .on(item.itemId.eq(itemImg.itemId).and(itemImg.repImgYn.eq("Y")))
//                .limit(10)
//                .fetch();
        List<ItemDto> contents = queryFactory
            .select(
                    new QItemDto(
                            item.id,
                            item.itemNm,
                            item.price,
                            item.avgStar,
                            item.numComment,
                            item.itemDetail,
                            itemImg.imgUrl))
            .from(item)
            .leftJoin(itemImg )
            .on(item.id.eq(itemImg.item.id).and(itemImg.repImgYn.eq("Y")))
//                .fetchJoin()
//            .where(searchType(itemSearchDto.getType()), // 타입
//                    searchAbvDg(itemSearchDto.getAbv()),    // 맛
//                    searchSweetDg(itemSearchDto.getSweetness()),
//                    searchSournessDg(itemSearchDto.getSourness()),
//                    searchSparklingDg(itemSearchDto.getSparkling()),
//                    searchByLike(itemSearchDto.getItemNm(), // 이름
//                    itemSearchDto.getSearchQuery()),
//                    itemImg.repImgYn.eq("Y")
//            )
            .orderBy(QItem.item.id.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        long total = contents.size();
        return new PageImpl<>(contents, pageable, total);
    }

}