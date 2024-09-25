package org.example.decorators;

import org.example.Post;

public class WordCountDecorator extends PostDecorator {

    public WordCountDecorator(Post decoratedPost) {
        super(decoratedPost);
    }

    @Override
    public void publish() {
        System.out.println("----------------------------------------");
        System.out.println("Word count: " + countWords(super.getContent()));
        System.out.println("----------------------------------------");
        super.publish();
    }

    private int countWords(String content) {
        return content.split("\\s+").length;
    }
}
