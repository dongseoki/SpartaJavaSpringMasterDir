package com.sparta.bootcamp.week_01.repository;

import static com.sparta.bootcamp.week_01.entity.QCategory.category;
import static com.sparta.bootcamp.week_01.entity.QProduct.product;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.bootcamp.week_01.entity.Product;
import com.sparta.bootcamp.week_01.entity.QProduct;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
@RequiredArgsConstructor
public class ProductQueryRepository {

  private final JPAQueryFactory queryFactory;

  public List<Product> search(String name, BigDecimal minPrice, BigDecimal maxPrice) {
    return queryFactory.selectFrom(product)
        .where(
            equalName(name),
            betweenPrice(minPrice, maxPrice)
        )
        .fetch();
  }

  public List<Product> searchComplex(String name, BigDecimal minPrice, BigDecimal maxPrice) {
    return queryFactory.selectFrom(product)
        .innerJoin(category).on(product.category.eq(category)) // 추가 된 JOIN
        .where(
            equalName(name),
            betweenPrice(minPrice, maxPrice)
        )
        .fetch();
  }


  private BooleanExpression equalName(String name) {
    return StringUtils.hasText(name) ? product.name.eq(name) : null;
  }

  private BooleanExpression betweenPrice(BigDecimal minPrice, BigDecimal maxPrice) {
    return Objects.isNull(minPrice) && Objects.isNull(maxPrice) ? null
        : product.price.between(minPrice, maxPrice);
  }
}
