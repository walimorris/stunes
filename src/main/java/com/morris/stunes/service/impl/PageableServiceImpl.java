package com.morris.stunes.service.impl;

import com.morris.stunes.service.PageableService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PageableServiceImpl implements PageableService {

    @Override
    public List<Integer> getPagesList(Page<?> resultingPageableList) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < resultingPageableList.getTotalPages(); i++) {
            list.add(i);
        }
        return list;
    }
}
