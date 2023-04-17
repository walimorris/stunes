package com.morris.stunes.service;

import org.springframework.data.domain.Page;

import java.util.List;

public interface PageableService {
    /**
     * Returns a list of numbers from 0 - N based on the number of returned pages from
     * a {@link org.springframework.data.domain.Pageable} query. This method can assist
     * with further pagination functionality on application views.
     *
     * @param resultingPageableList {@link Page}
     *
     * @return {@link List} of integers for rendering
     */
    List<Integer> getPagesList(Page<?> resultingPageableList);
}
