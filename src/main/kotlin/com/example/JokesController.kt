package com.example

import com.example.joke.Joke
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

@RestController
class JokesController {

    val counter = AtomicLong()

    @RequestMapping(value = "/jokes/random", method = arrayOf(RequestMethod.GET))
    fun greeting(@RequestParam(value = "name", defaultValue = "World") name: String): Joke {
        return Joke(counter.incrementAndGet(), "Hello, $name")
    }
}