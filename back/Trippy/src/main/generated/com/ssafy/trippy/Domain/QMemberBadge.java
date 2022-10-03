package com.ssafy.trippy.Domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMemberBadge is a Querydsl query type for MemberBadge
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberBadge extends EntityPathBase<MemberBadge> {

    private static final long serialVersionUID = -440332104L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QMemberBadge memberBadge = new QMemberBadge("memberBadge");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final QBadge badge;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath desc = createString("desc");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMemberBadge(String variable) {
        this(MemberBadge.class, forVariable(variable), INITS);
    }

    public QMemberBadge(Path<? extends MemberBadge> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QMemberBadge(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QMemberBadge(PathMetadata metadata, PathInits inits) {
        this(MemberBadge.class, metadata, inits);
    }

    public QMemberBadge(Class<? extends MemberBadge> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.badge = inits.isInitialized("badge") ? new QBadge(forProperty("badge")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}

