package com.morris.stunes.controller;

import com.morris.stunes.model.Artist;
import com.morris.stunes.repository.AuroraArtistRepository;
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
public class ArtistsController {

    @Autowired
    AuroraArtistRepository auroraArtistRepository;

    @Autowired
    PageableService pageableService;

    @GetMapping("/artists")
    public String artistsForm(Model model) {
        model.addAttribute("artists", new Artist());
        return "artists";
    }

    @GetMapping("/artists/published")
    public String submitArtistQuery(@ModelAttribute Artist artists, Model model,
                                    @RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "12") int size) {

        Pageable pageableConfig = PageRequest.of(page, size);
        Page<Artist> artistsLikeList = auroraArtistRepository.findByNameIsLikeIgnoreCase(artists.getName(),
                pageableConfig);

        List<Artist> artistList = artistsLikeList.getContent();
        List<Integer> numberOfPagesList = pageableService.getPagesList(artistsLikeList);

        model.addAttribute("artistsList", artistList);
        model.addAttribute("artistspages", numberOfPagesList);
        model.addAttribute("path", "published");
        model.addAttribute("searchterm", "name");
        model.addAttribute("searchValue", artists.getName());
        return "artistsresult";
    }
}
