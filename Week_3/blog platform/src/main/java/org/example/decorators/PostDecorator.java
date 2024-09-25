package org.example.decorators;

import org.example.Post;


public abstract class PostDecorator implements Post {
    protected Post decoratedPost;

    public PostDecorator(Post decoratedPost) {
        this.decoratedPost = decoratedPost;
    }

    @Override
    public String getTitle() {
        return decoratedPost.getTitle();
    }

    @Override
    public String getContent() {
        return decoratedPost.getContent();
    }

    @Override
    public void publish() {
        decoratedPost.publish();
    }

    public PostDecorator() {
    }

    public Post getDecoratedPost() {
        return decoratedPost;
    }

    public void setDecoratedPost(Post decoratedPost) {
        this.decoratedPost = decoratedPost;
    }
}
