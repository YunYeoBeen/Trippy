package com.ssafy.trippy.Domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDetailLocation is a Querydsl query type for DetailLocation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDetailLocation extends EntityPathBase<DetailLocation> {

    private static final long serialVersionUID = -1219461833L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDetailLocation detailLocation = new QDetailLocation("detailLocation");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath detailLocationContent = createString("detailLocationContent");

    public final StringPath detailLocationName = createString("detailLocationName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath imgPath = createString("imgPath");

    public final QLocation location;

    public final QPost post;

    public final NumberPath<Float> rating = createNumber("rating", Float.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QDetailLocation(String variable) {
        this(DetailLocation.class, forVariable(variable), INITS);
    }

    public QDetailLocation(Path<? extends DetailLocation> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDetailLocation(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDetailLocation(PathMetadata metadata, PathInits inits) {
        this(DetailLocation.class, metadata, inits);
    }

    public QDetailLocation(Class<? extends DetailLocation> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.location = inits.isInitialized("location") ? new QLocation(forProperty("location")) : null;
        this.post = inits.isInitialized("post") ? new QPost(forProperty("post"), inits.get("post")) : null;
    }

}

