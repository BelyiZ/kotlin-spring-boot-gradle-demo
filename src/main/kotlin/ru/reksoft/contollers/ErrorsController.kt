package ru.reksoft.contollers

import org.springframework.boot.autoconfigure.web.ErrorController
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpServletRequest

@Controller
class ErrorsController: ErrorController {

    companion object {
        const val PATH: String = "/error";
    }
    
    @RequestMapping(value = PATH)
//    @ExceptionHandler
    fun error(req: HttpServletRequest, e: Exception): String = "${e.cause}<br>${e.stackTrace}"

    override fun getErrorPath(): String = PATH
}