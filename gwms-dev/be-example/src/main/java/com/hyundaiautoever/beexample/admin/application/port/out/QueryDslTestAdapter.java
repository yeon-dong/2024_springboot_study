package com.hyundaiautoever.beexample.admin.application.port.out;

import com.hyundaiautoever.beexample.admin.application.domain.GwsExampleEntity;
import com.hyundaiautoever.beexample.admin.application.domain.QGwsExampleEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class QueryDslTestAdapter {

    private final JPAQueryFactory queryFactory;

    public List<GwsExampleEntity> findBy(Long id) {
        return queryFactory
                .select(QGwsExampleEntity.gwsExampleEntity)
                .from(QGwsExampleEntity.gwsExampleEntity)
                .where(
                        examepleIdEq(id)
                )
                .fetch();
    }

    private BooleanExpression examepleIdEq(Long userId) {
        return QGwsExampleEntity.gwsExampleEntity.id.eq(userId);
    }

}
