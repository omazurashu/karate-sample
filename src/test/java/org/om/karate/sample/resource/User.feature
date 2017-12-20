Feature: Test User API

  Background:
    * url baseUrl

  Scenario: Get all predefined users
    Given path '/users'
    When method GET
    Then status 200
    And assert response.length == 3
    And match response[*].id == [1, 2, 3]
    And match response[0].nickName == 'user1'

  Scenario: Manage new user
    Given path '/users'
    And request { nickName: 'user100' }
    When method post
    Then status 201
    And match response == {id: '#number', nickName: 'user100'}

    * def id = response.id
    Given path '/users', id
    When method get
    Then status 200
    And match response == {id: '#(id)', nickName: 'user100'}

    Given path '/users'
    When method GET
    Then status 200
    And assert response.length == 4
    And match response[*].id == [1, 2, 3, 4]
    And match response[*].nickName == ['user1', 'user2', 'user3', 'user100']

    Given path '/users', id
    When method delete
    Then status 204

    Given path '/users'
    When method GET
    Then status 200
    And assert response.length == 3
    And match response[*].id == [1, 2, 3]
