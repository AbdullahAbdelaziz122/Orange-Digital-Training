package org.example;



import org.example.decorators.MarkdownDecorator;
import org.example.decorators.ModerationDecorator;
import org.example.decorators.SEOOptimizerDecorator;
import org.example.decorators.WordCountDecorator;

import java.util.Arrays;
import java.util.List;


public class BlogApp {
    public static void main(String[] args) {
        // Create a basic post
        Post post = new BasicPost("My First Blog", "This is the content of my first blog post, including badword1 and badword2 .");

        // SEO DECORATOR
        List<String> tags = Arrays.asList("tag1", "tag2");
        post = new SEOOptimizerDecorator(post, tags.toArray(new String[0]));


        // WORD COUNT DECORATOR
        post = new WordCountDecorator(post);

        // Markdown DECORATOR
        post = new MarkdownDecorator(post);

//        // FILTER OR MODERATOR DECORATOR
        post = new ModerationDecorator(post);



        post.publish();
    }
}