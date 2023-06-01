package com.eviro.assessment.grad001.MarthaNyalivane;

import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/image")
public class ImageController {
    @GetMapping(value = "/{name}/{surname}/{\\w\\.\\w}")
    public FileSystemResource gethttpImageLink(@PathVariable String name, @PathVariable String surname){
        //return the result
        return null;
    }
}