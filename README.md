# POSTS

A Java Springboot application to handle social media posts. 
In the future this will be part of a bigger microservices project. 

Currently exposing the following API endpoints:

POST /api/v1/post/{userID} - DONE - need to validate input
can create a new post 

GET /api/v1/posts - DONE
get all posts on the platform. 

GET /api/v1/posts/user/{userId}
get all posts, for a given user id. 

POST /api/v1/post/users
get posts for multiple users- a list of userIds

GET /api/v1/post/{postId}
get a given post

DELETE /api/v1/post/{postId}
delete post by postID

Using an in memory H2 database for now.
