package com.example.cornconvert.corn;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class cornController {

    private final CornService cornService;

    @PostMapping("/corn-convert")
    public String cornConvert(@RequestBody CornDto cornDto){

        String corn  = cornService.cornConvert(cornDto);

        return corn;
    }
}
