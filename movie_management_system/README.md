## Entites:

1. Review
2. User
3. Movie

## Relationships

1. Each user can give one review per movie
2. User can give multiple reviews but one review per movie
3. Each movie can have multiple reviews

# Movie Management System

## Entity

### User

- **Fields**:
  - `userId` (Integer): Unique identifier for the user.
  - `name` (String): Name of the user.
  - `password` (String): User's password (hashed).
  - `phoneNumber` (Long)
  - `email` (String)
  - `dateOfBirth` (LocalDate): not compalsory
  - `Gender` (enum)

### Movie

- **Fields**:
  - `movieId` (Integer): Unique identifier for the movie.
  - `name` (String): Name of the movie.
  - `reviewId` (String): Unique code for the movie.
  - `rating` (String): PG13, R, A, U.
  - `type` (Array of String):
  - `releaseDate` (LocalDate)
  - `budget` (BigInt)
  - `reviewId` (FK)

### Review

- **Fields**:

  - `reviewId` (Integer): Unique identifier for the reviews.
  - `reviewTitle` (String): Review title
  - `reviewContent` (String).
  - `rating` (ENUM): 0 to 5
  - `userId` (FK)
  - `movieId` (FK)

- **Create User**: `POST /create_user`

  - Request Body:
    ```json
    {
      "userId": 1,
      "name": "John Doe",
      "password": "hashed_password",
      "phoneNumber": 1234567890,
      "email": "john.doe@example.com",
      "dateOfBirth": "1990-01-01",
      "gender": "Male"
    }
    ```
  - Response: Created user object

- **Get User**: `GET /users/{id}`

  - Response: User object

- **Update User**: `PUT /users/{id}`

  - Request Body: Updated user fields
  - Response: Updated user object

- **Delete User**: `DELETE /users/{id}`
  - Response: Status message with deleted user object

## Relationships

- **User - movie**: One-to-Many relationship where a user can have multiple movies associated with them.

## APIs

### User APIs

#### Basic CURD

### User API

- **Context Path**: `http://localhost:PORT/api/v1/mrms`

- **Create User**: `POST /create_user`

  - Request Body:
    ```json
    {
      "userId": 1,
      "name": "John Doe",
      "password": "hashed_password",
      "phoneNumber": 1234567890,
      "email": "john.doe@example.com",
      "dateOfBirth": "1990-01-01",
      "gender": "Male"
    }
    ```
  - Response: Created user object

- **Get User**: `GET /users/{id}`

  - Response: User object

- **Update User**: `PUT /users/{id}`

  - Request Body: Updated user fields
  - Response: Updated user object

- **Delete User**: `DELETE /users/{id}`
  - Response: Status message with deleted user object

### Movie APIs

- **Create movie**: `POST /post_movie`

  - Request Body:

    ```json
    {
      "movieId": 1,
      "name": "Inception",
      "reviewId": "REV123",
      "rating": "PG13",
      "type": ["Sci-Fi", "Thriller"],
      "releaseDate": "2010-07-16",
      "budget": 160000000,
      "reviewId": 1
    }
    ```

  - Response: Posted movie object

- **Get movie**: `GET /movie/{id}`

  - Response: movie object

- **Update movie**: `PUT /movies/{id}`

  - Request Body: Updated movie fields
  - Response: Updated movie object

- **Delete movie**: `DELETE /movies/{id}`
  - Response: Status message

### Review API

- **Create review**: `POST /post_review`

  - Request Body:

    ```json
    {
      "movieId": 1,
      "name": "Inception",
      "reviewId": "REV123",
      "rating": "PG13",
      "type": ["Sci-Fi", "Thriller"],
      "releaseDate": "2010-07-16",
      "budget": 160000000,
      "reviewId": 1
    }
    ```

  - Response: Posted review object

- **Get review**: `GET /review/{id}`

  - Response: review object

- **Update review**: `PUT /review/{id}`

  - Request Body: Updated review fields
  - Response: Updated review object with modified status code

- **Delete review**: `DELETE /review/{id}`
  - Response: Status message & review object

### Process API

- Movies which User has given Review
  => `GET /user/movie/{userID}`

  - Response : All Movie bodies which user has given Review

- Open Movie by ID
  => `GET /user/movie/{movieId}`

  - we can also use get movie by Id
  - Response: movie body

- Edit the Review by User
  => `PUT /user/movie/review/{reviewId}`

  - Request: Review body
  - Response: updated Review body

- Post new Review
  => `POST /user/movie/review/{movieId}`

  - Response: New review

## Getting Started

### Prerequisites

- JDK 11 or higher
- Maven
- MySQL

### Installation

1. **Clone the repository**:

   ```bash
   git clone https://github.com/your-repo/project.git
   cd project

   ```
