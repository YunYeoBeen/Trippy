package com.ssafy.trippy.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class PostTransport extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name="POST_TRANSPORT_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="POST_ID")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TRANSPORT_ID")
    private Transport transport;

    @Builder
    public PostTransport(Post post, Transport transport) {
        this.post = post;
        this.transport = transport;
    }
    public void setPost(Post post){
        this.post = post;
        post.getPostTransports().add(this);
    }

    public void update(Transport transport){
        this.transport = transport;
    }
    public void setTransport(Transport transport){
        this.transport = transport;

    }

    public void update(Transport transport){
        this.transport = transport;
    }

}
