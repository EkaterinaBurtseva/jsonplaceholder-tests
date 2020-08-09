package com.jsonpl.api.services;

import com.jsonpl.api.models.Comments;
import io.qameta.allure.Step;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CommentsService extends BaseApiService {

    public Set<String> getAllEmailAddresses(String post) {
        Set<String> emailForTheCurrenComment = new HashSet<>();
        getAllCommentsForThePost(post).stream().forEach(it -> emailForTheCurrenComment.add(it.email));
        return emailForTheCurrenComment;
    }

    @Step("get all comments for the post with id {postid}")
    public List<Comments> getAllCommentsForThePost(String postId) {
        return setUp().when().get(String.format("/posts/%s/comments", postId)).
                then().log().ifError().statusCode(200).extract().body().jsonPath().getList("", Comments.class);
    }
}

