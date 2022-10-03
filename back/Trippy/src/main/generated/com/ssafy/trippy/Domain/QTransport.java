package com.ssafy.trippy.Domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTransport is a Querydsl query type for Transport
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTransport extends EntityPathBase<Transport> {

    private static final long serialVersionUID = 1153887864L;

    public static final QTransport transport = new QTransport("transport");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QTransport(String variable) {
        super(Transport.class, forVariable(variable));
    }

    public QTransport(Path<? extends Transport> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTransport(PathMetadata metadata) {
        super(Transport.class, metadata);
    }

}

