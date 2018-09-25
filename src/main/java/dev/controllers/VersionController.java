package dev.controllers;

import dev.entities.Version;
import dev.repositories.VersionRepo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * WEB API : Version applicative.
 */
@RestController
public class VersionController {

    private VersionRepo versionRepo;

    public VersionController(VersionRepo versionRepo) {
        this.versionRepo = versionRepo;
    }


    @GetMapping("/versions")
    public List<Version> getVersion() {
        return this.versionRepo.findAll();
    }

}
