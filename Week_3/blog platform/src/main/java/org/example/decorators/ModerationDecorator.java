package org.example.decorators;

import org.example.Post;

import java.util.Arrays;
import java.util.List;


public class ModerationDecorator extends PostDecorator {

    private static final List<String> BAD_WORDS = Arrays.asList(
            "badword1", "badword2", "badword3", "badword4", "badword5",
            "badword6", "badword7", "badword8", "badword9", "badword10",
            "badword11", "badword12", "badword13", "badword14", "badword15",
            "badword16", "badword17", "badword18", "badword19", "badword20"
    );

    private final Post decoratedPost;

    public ModerationDecorator(Post decoratedPost) {
        super(decoratedPost);
        this.decoratedPost = decoratedPost;

    }


    @Override
    public String getContent() {
        String content = decoratedPost.getContent();
        return moderateContent(content);
    }

    private String moderateContent(String content) {
//        System.out.println("Original content: " + content);

        String[] words = content.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            if (BAD_WORDS.contains(words[i].toLowerCase())) {
                words[i] = "-----------";
            }
        }

        return String.join(" ", words);
    }



    @Override
    public void publish() {
        System.out.println("Moderating content before publishing...");
        decoratedPost.publish();
        System.out.println("-----------------------------------");
        System.out.println("Filtered Content: "+ this.getContent());
    }

}
