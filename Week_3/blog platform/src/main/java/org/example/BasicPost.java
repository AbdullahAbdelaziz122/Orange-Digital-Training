package org.example;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BasicPost implements Post {

    private String title;
    private String content;

    public BasicPost(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void publish() {
        System.out.println("Publishing post: " + this.getTitle());
        System.out.println("Content: " + this.getContent());  // Ensure getContent() is called
    }
}
