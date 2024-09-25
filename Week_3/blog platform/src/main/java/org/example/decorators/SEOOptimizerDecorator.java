package org.example.decorators;

import org.example.Post;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SEOOptimizerDecorator extends PostDecorator {

    private List<String> tags = new ArrayList<>();

    public SEOOptimizerDecorator(Post decoratedPost) {
        super(decoratedPost);
    }

    public SEOOptimizerDecorator(Post decoratedPost, String[] userTags ) {
        super(decoratedPost);
        tags.addAll(Arrays.asList(userTags));
    }

    @Override
    public void publish() {
        System.out.println("Optimizing for SEO...");

        addMetaTags(super.getTitle(), super.getContent(), this.tags);
        super.publish();
    }

    private void addMetaTags(String title, String content, List<String> tags) {

        System.out.println("Meta tags added for SEO.");
        tags.forEach(System.out::println);
        System.out.println("------------------------------------");
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void addTag(String tag){
        tags.add(tag);
    }
}
