package com.micoder.sbdemo.controllers.web

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class AdminWebController {
    @GetMapping("/req/login")
    fun login(): String = "login"

    @GetMapping("/req/signup")
    fun signup(): String = "signup"

    @GetMapping("/")
    fun home(): String = "index"
}