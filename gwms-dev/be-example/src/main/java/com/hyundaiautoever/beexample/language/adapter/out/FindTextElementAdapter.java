package com.hyundaiautoever.beexample.language.adapter.out;

import com.hyundaiautoever.beexample.language.application.domain.QTextElement;
import com.hyundaiautoever.beexample.language.application.domain.QWebPageTextElement;
import com.hyundaiautoever.beexample.language.application.domain.TextElement;
import com.hyundaiautoever.beexample.language.application.port.out.FindTextElementPort;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Locale;

//@Repository
@RequiredArgsConstructor
public class FindTextElementAdapter implements FindTextElementPort {

    private final JPAQueryFactory queryFactory;

    @Override
    public TextElement existBy(Locale locale, String textCode) {
        return queryFactory
                .select(QTextElement.textElement)
                .from(QTextElement.textElement)
                .where(localeEq(locale),
                       textCodeEq(textCode)
                )
                .fetchFirst();
    }

    @Override
    public List<TextElement> findBy(String uri, Locale locale) {
        return queryFactory
                .select(QWebPageTextElement.webPageTextElement.textElement)
                .from(QWebPageTextElement.webPageTextElement)
                .where(uriEq(uri), localeEq(locale))
                .fetch();
    }

    private BooleanExpression localeEq(Locale locale) {
        return QTextElement.textElement.locale.eq(locale);
    }

    private BooleanExpression textCodeEq(String textCode) {
        return QTextElement.textElement.textCode.eq(textCode);
    }

    private BooleanExpression uriEq(String uri) {
        return QWebPageTextElement.webPageTextElement.webPage.uri.eq(uri);
    }

}
