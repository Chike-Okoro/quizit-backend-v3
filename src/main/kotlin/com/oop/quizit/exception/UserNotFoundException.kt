package com.oop.quizit.exception

import org.springframework.http.HttpStatus

class UserNotFoundException(status: HttpStatus, message: String): Exception(message) {
}