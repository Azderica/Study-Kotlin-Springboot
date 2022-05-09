package com.example.mvc.controller.get

import com.example.mvc.model.http.UserRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController             // REST API Controller
@RequestMapping("/api")     // http://localhost:8080/api
class GetApiController {

    @GetMapping(path = ["/hello", "/abcd"])   // GET http://localhost:8080/api/hello
    fun hello(): String {
        return "hello kotlin"
    }

    // GET http://localhost:8080/api/requestMapping
    @RequestMapping(method = [RequestMethod.GET], path = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping"
    }

    // GET http://localhost:8080/api/get-mapping/path-variable/steve/20
    @RequestMapping("/get-mapping/path-variable/{name}/{age}")
    fun pathVariable(@PathVariable name: String, @PathVariable age: Int): String {
        println("${name}, ${age}")
        return name + " " + age
    }

    // GET http://localhost:8080/api/get-mapping/path-variable/steve/20
    @RequestMapping("/get-mapping/path-variable2/{name}/{age}")
    fun pathVariable2(@PathVariable(value = "name") _name: String, @PathVariable age: Int): String {
        val name = "kotlin"

        println("${_name}, ${age}")
        return _name + " " + age
    }

    // query paramter 예시
    // http://localhost:8080/api/get-mapping/query-param?name=steve&age=20
    @GetMapping("/get-mapping/query-param")
    fun queryParam(
        @RequestParam name: String,
        @RequestParam(value = "age") age: Int
    ): String {
        println("${name}, ${age}")
        return name + " " + age
    }

    // name, age, address, email
    // http://localhost:8080/api/get-mapping/query-param/object?name=steve&age=10&email=steve@gmail.com&address=address-
    @GetMapping("/get-mapping/query-param/object")
    fun queryParamObject(
        userRequest: UserRequest
    ): UserRequest {
        println(userRequest)
        return userRequest
    }

    // http://localhost:8080/api/get-mapping/query-param/map?name=steve&age=10&email=steve@gmail.com&address=address-&phone-number=01011112222    @GetMapping("/get-mapping/query-param/map")
    fun queryParamMap(@RequestParam map: Map<String, Any>): Map<String, Any> {
        println(map)
        val phoneNumber = map.get("phone-number")
        return map
    }

}