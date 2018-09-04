package com.pranavan.web.service;

import com.pranavan.web.model.Headhunter;


import java.util.List;

/**
 * Created by pranavan on 7/12/18.
 */
public interface HeadhunterService {

    public Headhunter findOne(Long id);

    public Headhunter saveHeadhunter(Headhunter headhunter);
    public Headhunter updateHeadhunter(Headhunter headhunter);

    public void deleteJobHeadhunter(Long id);

    public List<Headhunter> getHeadhunters();

}
