import org.springframework.data.mongodb.repository.MongoRepository

interface JokeRepository : MongoRepository<Joke, String>