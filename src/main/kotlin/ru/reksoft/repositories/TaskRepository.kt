package ru.reksoft.repositories

import org.springframework.data.mongodb.repository.MongoRepository
import ru.reksoft.entities.Task

interface TaskRepository : MongoRepository<Task, String>