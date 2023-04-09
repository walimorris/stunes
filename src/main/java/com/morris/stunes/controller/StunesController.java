package com.morris.stunes.controller;

import com.morris.stunes.model.Album;
import com.morris.stunes.model.Artist;
import com.morris.stunes.repository.RepositoryRDSAurora;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class StunesController {

    @Autowired
    RepositoryRDSAurora rdsAurora;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/albums")
    public String albumsForm(Model model) {
        model.addAttribute("albums", new Album());
        return "albums";
    }

    @PostMapping("/albums")
    public String submitAlbumQuery(@ModelAttribute Album albums, Model model) {
        List<Album> albumsLikeList = rdsAurora.getAlbumsLikeTitle(albums.getTitle());
        model.addAttribute("albumsList", albumsLikeList);
        return "albumsresult";
    }

    @GetMapping("/artists")
    public String artistsForm(Model model) {
        model.addAttribute("artists", new Artist());
        return "artists";
    }

    @PostMapping("/artists")
    public String submitArtistQuery(@ModelAttribute Artist artists, Model model) {
        List<Artist> artistsLikeList = rdsAurora.getArtistsLikeName(artists.getName());
        model.addAttribute("artistsList", artistsLikeList);
        return "artistsresult";
    }
}
