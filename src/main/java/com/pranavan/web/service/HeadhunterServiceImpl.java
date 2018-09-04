package com.pranavan.web.service;

import com.pranavan.web.dao.HeadhunterDao;
import com.pranavan.web.model.Headhunter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by pranavan on 7/12/18.
 */
@Service
public class HeadhunterServiceImpl implements HeadhunterService {
    @Autowired
    private HeadhunterDao headhunterDao;

    @Override
    public Headhunter findOne(Long id) {
        Headhunter headhunter=headhunterDao.findOne(id);
        return headhunter;
    }

    @Override
    public Headhunter saveHeadhunter(Headhunter headhunter) {
        return headhunterDao.save(headhunter);
    }

    @Override
    public Headhunter updateHeadhunter(Headhunter headhunter) {
        return headhunterDao.save(headhunter);
    }

    @Override
    public void deleteJobHeadhunter(Long id) {
        headhunterDao.delete(id);
    }

    @Override
    public List<Headhunter> getHeadhunters() {
        return headhunterDao.findAll();
    }
}
