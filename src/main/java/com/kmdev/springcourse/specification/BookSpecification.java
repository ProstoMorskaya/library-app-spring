package com.kmdev.springcourse.specification;

import com.kmdev.springcourse.models.Book;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    public static Specification<Book> nameStartWith(String prefix) {
        return (root, query, builder) -> {
            root.fetch("person", JoinType.LEFT);
            query.distinct(true);

            return builder.like(
                    builder.lower(root.get("name")),
                    prefix.toLowerCase() + "%"
            );
        };
    }
}
