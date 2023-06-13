package com.project.sul.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPaymentsInfo is a Querydsl query type for PaymentsInfo
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPaymentsInfo extends EntityPathBase<PaymentsInfo> {

    private static final long serialVersionUID = -1762509856L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPaymentsInfo paymentsInfo = new QPaymentsInfo("paymentsInfo");

    public final NumberPath<Integer> amount = createNumber("amount", Integer.class);

    public final StringPath buyerAddr = createString("buyerAddr");

    public final StringPath buyerPostcode = createString("buyerPostcode");

    public final StringPath impUid = createString("impUid");

    public final NumberPath<Long> kcpmentNo = createNumber("kcpmentNo", Long.class);

    public final StringPath kcpMethod = createString("kcpMethod");

    public final QMember member;

    public final StringPath merchantUid = createString("merchantUid");

    public final QOrder order;

    public QPaymentsInfo(String variable) {
        this(PaymentsInfo.class, forVariable(variable), INITS);
    }

    public QPaymentsInfo(Path<? extends PaymentsInfo> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPaymentsInfo(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPaymentsInfo(PathMetadata metadata, PathInits inits) {
        this(PaymentsInfo.class, metadata, inits);
    }

    public QPaymentsInfo(Class<? extends PaymentsInfo> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member"), inits.get("member")) : null;
        this.order = inits.isInitialized("order") ? new QOrder(forProperty("order"), inits.get("order")) : null;
    }

}

