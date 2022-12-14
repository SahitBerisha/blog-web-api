Feature: Update a Post

  Background:
    * url baseUrl
    * def createFeature = call read('../posts/post.feature')
    * def bearerToken = createFeature.bearerToken
    * def id = createFeature.postId
    * def postsUrlBase = '/api/v1/posts/' + id

  Scenario: Update a Post by ID

    Given path postsUrlBase
    And request
    """
       {
          title: 'Karate testing updated',
          body: 'Karate tests are the best'
       }
    """
    And header Authorization = bearerToken
    When method PUT
    Then status 200
    And match response ==
    """
      {
        id: '#(id)',
        title: 'Karate testing updated',
        body: 'Karate tests are the best',
        user: '#notnull',
        category:
          {
            id: '#uuid',
            name: 'Information Technology'
          },
        tags: []
      }
    """