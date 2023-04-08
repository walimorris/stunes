package com.morris.stunes.controller;

import com.morris.stunes.model.Album;
import com.morris.stunes.repository.RepositoryRDSAurora;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StunesController {

    @Autowired
    RepositoryRDSAurora rdsAurora;

    @GetMapping("/")
    public String index(Model model) {
        List<Album> albums = rdsAurora.getAllAlbums();
        model.addAttribute("albums", albums);
        return "index";
    }
}
