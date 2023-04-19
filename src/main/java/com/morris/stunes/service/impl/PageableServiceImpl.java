package com.morris.stunes.service.impl;

import com.morris.stunes.service.PageableService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PageableServiceImpl implements PageableService {

    @Override
    public List<Integer> getPagesList(Page<?> resultingPageableList, int page) {
        List<Integer> list = new ArrayList<>();

        /*
         If the page is divisible by 10 (max 10 pagination links per results page), return
         render 10-page links from the  starting page. Or else, pull the page number and
         call the floorWholeNumberBaseTen in order to render all available page links from
         least to greatest.
         */
        for (int i = page % 10 == 0 ? page : floorWholeNumberBaseTen(page); i < resultingPageableList.getTotalPages(); i++) {
            list.add(i);
        }
        return list;
    }

    /**
     * Obtains the remainder of a number from subtracting ten and subtracts the remainder
     * from the original number. This floors a whole number to base 10. Example: 16 -> 10,
     * 23 -> 20 and single digits will be 0.
     *
     * @param num int
     *
     * @return int
     */
    private int floorWholeNumberBaseTen(int num) {
        int remainder = num % 10;
        return num - remainder;
    }
}
