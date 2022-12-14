Feature: Read Posts

  Background:
    * url baseUrl
    * def createFeature = call read('../posts/post.feature')
    * def bearerToken = createFeature.bearerToken
    * def postId = createFeature.postId
    * def postsBaseUrl = '/api/v1/posts/'

  Scenario: Get one Post by ID

    Given path postsBaseUrl + postId
    And header Authorization = bearerToken
    When method GET
    Then status 200
    And match response ==
    """
      {
        id: '#(postId)',
        title: 'Karate testing',
        body: 'Demo for creating test with karate framework',
        user: '#notnull',
        category:
          {
            id: '#uuid',
            name: 'Information Technology'
          },
        tags: []
      }
    """

  Scenario: Get all Posts

    Given path postsBaseUrl
    And header Authorization = bearerToken
    And param from = '2022-11-24T20:00:00Z'
    And param to = '2022-11-27T20:20:00Z'
    When method GET
    Then status 200
