# POSTS

A Java Springboot application to handle social media posts. 
In the future this will be part of a bigger microservices project. 

Currently exposing the following API endpoints:

POST /api/v1/post/{userID} - DONE - need to validate input
can create a new post 

GET /api/v1/posts - DONE
get all posts on the platform. 

GET /api/v1/posts/user/{userId} - DONE
get all posts, for a given user id. 
Empty string if not found

GET /api/v1/posts?users={userIds} - TO DO
get all posts, for a list of user Ids (Ie frinds list)

GET /api/v1/posts/{postId}
get a given post for id, or return error

PUT /api/v1/posts/{postId}
update post by ID

DELETE /api/v1/posts/{postId}
delete post by postID

Using an in memory H2 database for now.

Otherbacklog:
- Exception handling
- Securing API endpoints
- Setup code clean and formatting tool
