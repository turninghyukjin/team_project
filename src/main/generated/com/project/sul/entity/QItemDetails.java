package com.project.sul.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QItemDetails is a Querydsl query type for ItemDetails
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItemDetails extends EntityPathBase<ItemDetails> {

    private static final long serialVersionUID = 1675161770L;

    public static final QItemDetails itemDetails = new QItemDetails("itemDetails");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> r_abv = createNumber("r_abv", Integer.class);

    public final NumberPath<Integer> r_sourness = createNumber("r_sourness", Integer.class);

    public final NumberPath<Integer> r_sparkling = createNumber("r_sparkling", Integer.class);

    public final NumberPath<Integer> r_sweetness = createNumber("r_sweetness", Integer.class);

    public final StringPath type = createString("type");

    public QItemDetails(String variable) {
        super(ItemDetails.class, forVariable(variable));
    }

    public QItemDetails(Path<? extends ItemDetails> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItemDetails(PathMetadata metadata) {
        super(ItemDetails.class, metadata);
    }

}

