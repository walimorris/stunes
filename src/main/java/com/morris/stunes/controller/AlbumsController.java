package com.morris.stunes.controller;

import com.morris.stunes.model.Album;
import com.morris.stunes.repository.AuroraAlbumRepository;
import com.morris.stunes.service.PageableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AlbumsController {

    @Autowired
    AuroraAlbumRepository auroraAlbumRepository;

    @Autowired
    PageableService pageableService;

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
        List<Integer> numberOfPagesList = pageableService.getPagesList(resultingPageableAlbums);

        List<Album> resultingAlbums = resultingPageableAlbums.getContent();
        model.addAttribute("albumsList", resultingAlbums);
        model.addAttribute("albumspages", numberOfPagesList);
        model.addAttribute("path", "published");
        model.addAttribute("searchterm", "title");
        model.addAttribute("searchValue", albums.getTitle());
        return "albumsresult";
    }

    @GetMapping("/albums/artist")
    public String submitAlbumQuery(Model model, @RequestParam(value = "artistId") int artistId,
                                   @RequestParam(defaultValue = "0") int page,
                                   @RequestParam(defaultValue = "12") int size) {

        Pageable paging = PageRequest.of(page, size);
        Page<Album> resultingPageableAlbums = auroraAlbumRepository.findByArtistId(artistId, paging);
        List<Integer> numberOfPagesList = pageableService.getPagesList(resultingPageableAlbums);

        List<Album> resultingAlbums = resultingPageableAlbums.getContent();
        model.addAttribute("albumsList", resultingAlbums);
        model.addAttribute("albumspages", numberOfPagesList);
        model.addAttribute("path", "artist");
        model.addAttribute("searchterm", "artistId");
        model.addAttribute("searchValue", artistId);
        return "albumsresult";
    }
}
