package com.hyundaiautoever.beexample.language.application.domain;

import com.hyundaiautoever.beexample.infrastructure.config.auditing.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @ToString
@NoArgsConstructor
public class WebPage extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String uri;

    public WebPage(String uri) {
        this.uri = uri;
    }

    @OneToMany(mappedBy = "webPage")
    private final List<WebPageTextElement> textElements = new ArrayList<>();

    public static WebPage of(String uri) {
        return new WebPage(uri);
    }

}
