package com.portifolio.joao.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/resources")
public class ResourcesController {

    @GetMapping("/static/images/{imageName}")
    @ResponseBody
    public byte[] getImage(@PathVariable String imageName) throws IOException {
        InputStream in = getClass().getResourceAsStream("/static/images/" + imageName);
        return IOUtils.toByteArray(in);
    }

    @GetMapping("/static/videos/{videoName}")
    public ResponseEntity<InputStreamResource> getVideo(@PathVariable String videoName) throws IOException {
        byte[] videoData = getResourceData("/static/videos/" + videoName);

        String mediaType = "application/octet-stream";

        if (videoName.endsWith(".mp4")) {
            mediaType = "video/mp4";
        } else if (videoName.endsWith(".webm")) {
            mediaType = "video/webm";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mediaType))
                .body(new InputStreamResource(new ByteArrayInputStream(videoData)));
    }

    private byte[] getResourceData(String resourcePath) throws IOException {
        try (InputStream in = getClass().getResourceAsStream(resourcePath)) {
            return IOUtils.toByteArray(in);
        }
    }



}