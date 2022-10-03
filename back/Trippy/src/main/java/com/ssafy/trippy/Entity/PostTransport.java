package com.ssafy.trippy.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
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

    public PostTransport(Post post, Transport transport) {
        this.post = post;
        this.transport = transport;
    }
}
