package ru.reksoft.contollers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.reksoft.entities.Task
import ru.reksoft.repositories.TaskRepository
import java.util.*

@RestController
@RequestMapping("/tasks")
class TasksController {

    private @Autowired val tasksRepository: TaskRepository? = null

    @RequestMapping(value = "", method = arrayOf(RequestMethod.GET))
    fun getRandomTask(@RequestParam(required = false, defaultValue = "0") page: Int,
                      @RequestParam(required = false, defaultValue = "100500") size: Int): ResponseEntity<List<Task>> =
            ResponseEntity.ok(tasksRepository?.findAll(PageRequest(page, size))?.content ?: Collections.emptyList())

    @RequestMapping(value = "", method = arrayOf(RequestMethod.POST))
    fun createTask(@RequestBody text: String) = ResponseEntity.ok(tasksRepository?.save(Task(id = null, content = text)))

    @RequestMapping(value = "/{taskId}", method = arrayOf(RequestMethod.GET))
    fun getTask(@PathVariable taskId: String): ResponseEntity<Any> {
        val task = tasksRepository?.findOne(taskId)
        return if (task == null) {
            ResponseEntity("Task with id $taskId not exists", HttpStatus.NOT_FOUND)
        } else {
            ResponseEntity.ok(task)
        }
    }

    @RequestMapping(value = "/{taskId}", method = arrayOf(RequestMethod.PUT))
    fun editTask(@RequestBody task: Task): ResponseEntity<Any> {
        var oldTask = tasksRepository?.findOne(task.id)
        return if (oldTask == null) {
            ResponseEntity("Task with id ${task.id} not exists", HttpStatus.NOT_FOUND)
        } else {
            oldTask.content = task.content
            oldTask = tasksRepository?.save(oldTask)
            ResponseEntity.ok(oldTask)
        }
    }

    @RequestMapping(value = "/{taskId}", method = arrayOf(RequestMethod.DELETE))
    fun deleteTask(@PathVariable taskId: String): ResponseEntity<Any> = ResponseEntity.ok(tasksRepository?.delete(taskId))

    @RequestMapping(value = "/count", method = arrayOf(RequestMethod.GET))
    fun getTasksCount() = ResponseEntity.ok(tasksRepository?.count())
}