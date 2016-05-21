package ru.reksoft.contollers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import ru.reksoft.entities.Joke
import org.springframework.web.bind.annotation.*
import ru.reksoft.repositories.JokeRepository
import java.util.*
import java.util.concurrent.ThreadLocalRandom

@RestController
@RequestMapping("/jokes")
class JokesController {

    private @Autowired val jokesRepository: JokeRepository? = null

    @RequestMapping(value = "", method = arrayOf(RequestMethod.GET))
    fun getRandomJoke(@RequestParam page: Int, @RequestParam size: Int): ResponseEntity<List<Joke>> =
            ResponseEntity.ok(jokesRepository?.findAll(PageRequest(page, size))?.content ?: Collections.emptyList())

    @RequestMapping(value = "", method = arrayOf(RequestMethod.POST))
    fun saveNewJoke(@RequestBody text: String) =
            ResponseEntity.ok(jokesRepository?.save(Joke(id = null, content = text)))

    @RequestMapping(value = "/{jokeId}", method = arrayOf(RequestMethod.GET))
    fun getJokeById(@PathVariable jokeId: String): ResponseEntity<Any> {
        val joke = jokesRepository?.findOne(jokeId)
        return if (joke == null)
            ResponseEntity("Joke with id $jokeId not exists", HttpStatus.NOT_FOUND)
        else
            ResponseEntity.ok(joke)
    }

    @RequestMapping(value = "/count", method = arrayOf(RequestMethod.GET))
    fun getJokesCount() = ResponseEntity.ok(jokesRepository?.count())
}