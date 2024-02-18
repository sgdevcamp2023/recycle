package com.zzaug.review.domain.persistence.review;

import com.zzaug.review.entity.review.query.ReviewQueryEntity;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ReviewSearchQueryRepository {

    private final ElasticsearchOperations elasticsearchOperations;

    @Autowired
    public ReviewSearchQueryRepository(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public List<ReviewQueryEntity> findAllByAuthorIdAndContentContainingAndIsDeletedIsFalse(
            Long authorId, String query){
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.boolQuery()
                        .must(QueryBuilders.matchQuery("authorId", authorId))
                        .must(QueryBuilders.matchQuery("content", query))
                        .must(QueryBuilders.matchQuery("isDeleted", false)))
                .build();
        SearchHits<ReviewQueryEntity> searchHits = elasticsearchOperations
                .search(searchQuery, ReviewQueryEntity.class, IndexCoordinates.of("review"));
        return searchHits.stream()
                .map(SearchHit::getContent)
                .collect(Collectors.toList());
    }
}
