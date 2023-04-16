package com.morris.stunes.controller;

import com.morris.stunes.model.Album;
import com.morris.stunes.repository.AuroraAlbumRepository;
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
    AuroraAlbumRepository auroraAlbumRepository;

    @GetMapping("/albums")
    public String albumsForm(Model model) {
        model.addAttribute("albums", new Album());
        return "albums";
    }

    @PostMapping("/albums")
    public String submitAlbumQuery(@ModelAttribute Album albums, Model model) {
        List<Album> resultingAlbums = auroraAlbumRepository.findByTitleLike(
                albums.getTitle(), albums.getTitle());
        model.addAttribute("albumsList", resultingAlbums);
        return "albumsresult";
    }

    @GetMapping("/albums/fromartist")
    public String submitAlbumQuery(Model model, @RequestParam(value = "artistId") int artistId) {
        List<Album> resultingAlbums = auroraAlbumRepository.findByArtistId(artistId);
        model.addAttribute("albumsList", resultingAlbums);
        return "albumsresult";
    }
}
