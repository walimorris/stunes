package com.morris.stunes.controller;

import com.morris.stunes.model.Track;
import com.morris.stunes.repository.AuroraTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TracksController {

    @Autowired
    AuroraTrackRepository auroraTrackRepository;

    @GetMapping("/tracks")
    public String submitAlbumQuery(@ModelAttribute Track tracks, Model model,
                                   @RequestParam(value = "albumId", required = true) int albumId) {

        List<Track> resultingTracks = auroraTrackRepository.findTracksByAlbumId(albumId);
        model.addAttribute("tracks", resultingTracks);
        return "tracksresult";
    }
}
