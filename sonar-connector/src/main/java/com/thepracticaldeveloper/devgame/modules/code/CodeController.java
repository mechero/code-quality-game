package com.thepracticaldeveloper.devgame.modules.code;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {"/code"})
public class CodeController {

    private final CodeService codeService;

    public CodeController(final CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping
    public CodeDetails getCode() {
        return codeService.getCodeDetails().orElse(new CodeDetails("-NOCODE-"));
    }
}
