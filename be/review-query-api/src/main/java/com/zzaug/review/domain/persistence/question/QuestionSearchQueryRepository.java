package com.zzaug.review.domain.persistence.question;

import com.zzaug.review.entity.question.query.QuestionQueryEntity;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionSearchQueryRepository {
    private final ElasticsearchOperations elasticsearchOperations;

    @Autowired
    public QuestionSearchQueryRepository(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public QuestionQueryEntity findByQuestionIdAndContentContainingAndIsDeletedIsFalse(
            Long questionId, String query){
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.boolQuery()
                        .must(QueryBuilders.matchQuery("questionId", questionId))
                        .must(QueryBuilders.matchQuery("content", query))
                        .must(QueryBuilders.matchQuery("isDeleted", false)))
                .build();
        SearchHit<QuestionQueryEntity> searchHits = elasticsearchOperations
                .searchOne(searchQuery, QuestionQueryEntity.class, IndexCoordinates.of("question"));
        if (searchHits != null) {
            return searchHits.getContent();
        } else {
            return null;
        }
    }
}
