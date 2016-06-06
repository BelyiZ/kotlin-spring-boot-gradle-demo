package ru.reksoft.contollers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class IndexController {

    @RequestMapping(value = "/", method = arrayOf(RequestMethod.GET))
    fun getIndexPage(): String = "index.html"
}