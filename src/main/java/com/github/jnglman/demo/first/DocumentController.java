package com.github.jnglman.demo.first;

import com.github.jnglman.demo.first.model.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
public class DocumentController {

    @PostMapping
    public ResponseEntity<?> newDocument(@Valid @RequestBody Document document) {
        System.out.println(document);
        return ResponseEntity.ok().build();
    }

}
