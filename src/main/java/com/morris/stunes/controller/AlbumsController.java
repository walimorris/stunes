package com.morris.stunes.controller;

import com.morris.stunes.model.Album;
import com.morris.stunes.repository.AuroraAlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
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

    @GetMapping("/albums/published")
    public String submitAlbumQuery(@ModelAttribute Album albums, Model model,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "12") int size) {

        Pageable paging = PageRequest.of(page, size);
        Page<Album> resultingPageableAlbums = auroraAlbumRepository.findByTitleIsLikeIgnoreCase(albums.getTitle(), paging);
        List<String> numberOfPagesList = new ArrayList<>();
        for (int i = 0; i < resultingPageableAlbums.getTotalPages(); i++) {
            numberOfPagesList.add(String.valueOf(i + 1));
        }

        List<Album> resultingAlbums = resultingPageableAlbums.getContent();
        model.addAttribute("albumsList", resultingAlbums);
        model.addAttribute("albumspages", numberOfPagesList);
        model.addAttribute("searchterm", albums.getTitle());
        return "albumsresult";
    }

    @GetMapping("/albums/fromartist")
    public String submitAlbumQuery(Model model, @RequestParam(value = "artistId") int artistId) {
        List<Album> resultingAlbums = auroraAlbumRepository.findByArtistId(artistId);
        model.addAttribute("albumsList", resultingAlbums);
        return "albumsresult";
    }
}
