package ru.reksoft.repositories

import org.springframework.data.mongodb.repository.MongoRepository
import ru.reksoft.entities.Joke

interface JokeRepository : MongoRepository<Joke, String>