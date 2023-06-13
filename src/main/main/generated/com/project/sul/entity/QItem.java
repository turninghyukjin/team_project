package com.project.sul.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItem is a Querydsl query type for Item
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItem extends EntityPathBase<Item> {

    private static final long serialVersionUID = 1704157816L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItem item = new QItem("item");

    public final NumberPath<Integer> abv = createNumber("abv", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath itemDetail = createString("itemDetail");

    public final QItemDetails itemDetails;

    public final StringPath itemNm = createString("itemNm");

    public final EnumPath<com.project.sul.constant.ItemSellStatus> itemSellStatus = createEnum("itemSellStatus", com.project.sul.constant.ItemSellStatus.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> regTime = createDateTime("regTime", java.time.LocalDateTime.class);

    public final NumberPath<Integer> sourness = createNumber("sourness", Integer.class);

    public final NumberPath<Integer> sparkling = createNumber("sparkling", Integer.class);

    public final NumberPath<Integer> stockNumber = createNumber("stockNumber", Integer.class);

    public final NumberPath<Integer> sweetness = createNumber("sweetness", Integer.class);

    public final StringPath type = createString("type");

    public final DateTimePath<java.time.LocalDateTime> updateTime = createDateTime("updateTime", java.time.LocalDateTime.class);

    public QItem(String variable) {
        this(Item.class, forVariable(variable), INITS);
    }

    public QItem(Path<? extends Item> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItem(PathMetadata metadata, PathInits inits) {
        this(Item.class, metadata, inits);
    }

    public QItem(Class<? extends Item> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.itemDetails = inits.isInitialized("itemDetails") ? new QItemDetails(forProperty("itemDetails")) : null;
    }

}

