# POSTS

A Java Springboot application to handle social media posts. 
In the future this will be part of a bigger microservices project. 

Currently exposing the following API endpoints:

POST /api/v1/post

GET /api/v1/posts

GET /api/v1/posts/user/{userId}

GET /api/v1/post/{postId}

DELETE /api/v1/post/{postId}

Using an in memory H2 database for now.
