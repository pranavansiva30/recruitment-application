package com.pranavan.web.service;

import com.pranavan.web.dao.HeadhunterDao;
import com.pranavan.web.model.Headhunter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pranavan on 7/15/18.
 */
@RunWith(SpringRunner.class)
public class TestHeadhunterService {

    @MockBean
    private HeadhunterDao headhunterDao;
    private Headhunter headhunter;
    private List<Headhunter> headhunterList=new ArrayList<>();

    private HeadhunterService headhunterService;

    @Before
    public void setUp() {
        headhunterService= new HeadhunterServiceImpl();

        ReflectionTestUtils.setField(headhunterService, "headhunterDao", headhunterDao);
        headhunter= new Headhunter();
        headhunter.setId(1L);
        headhunter.setActive(true);
        headhunter.setName("ABC");
        headhunterList.add(headhunter);
        Mockito.when(
                headhunterDao.findAll()).thenReturn(headhunterList);

    }

    @Test
    public void testsaveHeadhunter(){
        Headhunter headHunterCreate=headhunter;
        headHunterCreate.setId(null);

        Mockito.when(
                headhunterDao.save(headHunterCreate)).thenReturn(headhunter);
        Headhunter actual=headhunterService.saveHeadhunter(headHunterCreate);
        Assert.assertEquals(actual,headhunter);

    }
    @Test
    public void testUpdateHeadhunter(){
        Headhunter headHunterUpdate=headhunter;
        headHunterUpdate.setName("ABCD");

        Mockito.when(
                headhunterDao.save(headHunterUpdate)).thenReturn(headHunterUpdate);
        Headhunter actual=headhunterService.saveHeadhunter(headHunterUpdate);
        Assert.assertEquals(actual,headhunter);

    }
    @Test
    public void testFindOne(){
        Mockito.when(
                headhunterDao.findOne(1L)).thenReturn(headhunter);
        Headhunter actual=headhunterService.findOne(1L);
        Assert.assertEquals(actual,headhunter);

    }
    @Test
    public void testGetHeadhunters(){
        Mockito.when(
                headhunterDao.findAll()).thenReturn(headhunterList);
        List<Headhunter> actual=headhunterService.getHeadhunters();
        Assert.assertEquals(actual.get(0),headhunter);

    }
}
