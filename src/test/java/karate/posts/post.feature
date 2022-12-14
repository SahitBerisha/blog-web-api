Feature: Create a new Post

  Background:
    * url baseUrl
    * def categoryFeature = call read('../categories/post.feature')
    * def categoryId = categoryFeature.categoryId
    * def bearerToken = categoryFeature.bearerToken
    * def postsBaseUrl = '/api/v1/categories/' + categoryId + '/posts'
    * def postsPayload = read('../posts/post.json')
    * def requestPayload = postsPayload.postRequestPayload
    * def responsePayload = postsPayload.postResponsePayload

  Scenario: Create a new Post

    Given path postsBaseUrl
    And request requestPayload
    And header Accept = 'application/json'
    And header Authorization = bearerToken
    When method post
    Then status 201
    And match response == responsePayload

    * def postId = response.id