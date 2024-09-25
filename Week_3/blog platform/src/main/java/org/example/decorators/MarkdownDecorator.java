package org.example.decorators;

import org.example.Post;

public class MarkdownDecorator extends PostDecorator{

    public MarkdownDecorator(Post decoretedPost){
        super(decoretedPost);
    }

    @Override
    public String getContent(){
        return applyMarkdown(super.getContent());
    }

    public String applyMarkdown(String content){
        return "**" + content + "**";
    }


    @Override
    public void publish(){
        System.out.println("Applying Markdown Formatting...");
        super.publish();

    }
}
