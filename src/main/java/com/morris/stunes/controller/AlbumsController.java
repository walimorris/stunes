package com.morris.stunes.controller;

import com.morris.stunes.model.Album;
import com.morris.stunes.repository.RepositoryRDSAurora;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AlbumsController {

    @Autowired
    RepositoryRDSAurora rdsAurora;

    @GetMapping("/albums")
    public String albumsForm(Model model) {
        model.addAttribute("albums", new Album());
        return "albums";
    }

    @PostMapping("/albums")
    public String submitAlbumQuery(@ModelAttribute Album albums, Model model) {
        List<Album> resultingAlbums = rdsAurora.getAlbumsLikeTitle(albums.getTitle());
        model.addAttribute("albumsList", resultingAlbums);
        return "albumsresult";
    }

    @PostMapping("/albums/fromartist")
    public String submitAlbumQuery(Model model, @RequestParam(value = "id") int artistId) {
        List<Album> resultingAlbums = rdsAurora.getAllAlbumsWithArtistId(artistId);
        model.addAttribute("albumsList", resultingAlbums);
        return "albumsresult";
    }
}
