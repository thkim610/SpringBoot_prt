package me.devKim.blog.prt.controller;

import me.devKim.blog.prt.dto.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller//컨트롤러라는 것을 명시적으로 표시 (빈에 등록)
public class ExampleController {
    // "/thymeleaf/example" - GET요청
    @GetMapping("/thymeleaf/example")
    public String thymeleafExample(Model model){
        // exampleperson 생성 후, 모델 객체에 담아 넘김.
        Person examplePerson = new Person();
        examplePerson.setId(1L);
        examplePerson.setName("kim");
        examplePerson.setAge(20);
        examplePerson.setHobby(List.of("운동","독서"));

        model.addAttribute("examplePerson",examplePerson);
        model.addAttribute("today", LocalDate.now());
        return "example"; //뷰 이름 반환.
    }

}
