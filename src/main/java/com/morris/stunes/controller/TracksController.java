package com.morris.stunes.controller;

import com.morris.stunes.model.Track;
import com.morris.stunes.repository.AuroraTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TracksController {

    @Autowired
    AuroraTrackRepository auroraTrackRepository;

    @GetMapping("/tracks")
    public String submitTracksQuery(@ModelAttribute Track tracks, Model model,
                                   @RequestParam(value = "albumId", required = true) int albumId) {

        List<Track> resultingTracks = auroraTrackRepository.findTracksByAlbumId(albumId);
        model.addAttribute("tracks", resultingTracks);
        return "tracksresult";
    }

    @GetMapping("/tracks/mostpopular")
    public @ResponseBody List<Track> getMostPopularTracks() {
        return auroraTrackRepository.findMostPopularTracks();
    }
}
