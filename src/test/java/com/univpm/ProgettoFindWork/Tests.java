package com.univpm.ProgettoFindWork;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.univpm.ProgettoFindWork.Models.KeywordStatisticsRecord;
import com.univpm.ProgettoFindWork.Services.FindWorkService;
import com.univpm.ProgettoFindWork.Services.FindWorkService.RequestBuilder;

class Tests {
    private FindWorkService.RequestBuilder r;

    @BeforeEach
    void setUp() throws Exception {
    this.r= new FindWorkService.RequestBuilder();
                r
                .location("LONDON")
                .employment("contract")
                .remote(true)
                .keywords("ciao");
    }


    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void test() {
        assertEquals("LONDON", r.getLocation());
        assertEquals(true, r.getRemote());
        assertEquals("contract", r.getEmployment());

    }

    @Test
    void test1() {
         assertEquals("https://findwork.dev/api/jobs/?location=LONDON&remote=true&employment_type=contract&search=ciao+", r.build());

    }

    @Test
    void test2() {
        assertAll("valori",()->assertEquals("LONDON", r.getLocation()),
                ()->assertEquals(true, r.getRemote()),
                ()->assertEquals("contract", r.getEmployment()));
    }

}