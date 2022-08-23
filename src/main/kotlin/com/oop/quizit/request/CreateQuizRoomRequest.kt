package com.oop.quizit.request

import lombok.Data
import javax.validation.constraints.NotBlank

@Data
class CreateQuizRoomRequest {
     val name: @NotBlank String? = null
     val description: @NotBlank String? = null
}