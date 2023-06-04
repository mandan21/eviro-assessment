package com.eviro.assessment.grad001.MarthaNyalivane.controller;//package com.eviro.assessment.grad001.MarthaNyalivane;
//


import com.eviro.assessment.grad001.MarthaNyalivane.service.ImageService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/image")
public class ImageController {
    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(value = "/{name}/{surname}/{filename}")
    public FileSystemResource getHttpImageLink(
            @PathVariable String name,
            @PathVariable String surname,
            @PathVariable String filename) {
        return imageService.getHttpImageLink(name, surname, filename);
    }
}
