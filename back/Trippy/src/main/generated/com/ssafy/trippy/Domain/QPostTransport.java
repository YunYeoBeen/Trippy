package com.ssafy.trippy.Domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QPostTransport is a Querydsl query type for PostTransport
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPostTransport extends EntityPathBase<PostTransport> {

    private static final long serialVersionUID = -484709832L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QPostTransport postTransport = new QPostTransport("postTransport");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QPost post;

    public final QTransport transport;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QPostTransport(String variable) {
        this(PostTransport.class, forVariable(variable), INITS);
    }

    public QPostTransport(Path<? extends PostTransport> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QPostTransport(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QPostTransport(PathMetadata metadata, PathInits inits) {
        this(PostTransport.class, metadata, inits);
    }

    public QPostTransport(Class<? extends PostTransport> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.post = inits.isInitialized("post") ? new QPost(forProperty("post"), inits.get("post")) : null;
        this.transport = inits.isInitialized("transport") ? new QTransport(forProperty("transport")) : null;
    }

}

