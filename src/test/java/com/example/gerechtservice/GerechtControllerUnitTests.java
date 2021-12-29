package com.example.gerechtservice;

import com.example.gerechtservice.model.Gerecht;
import com.example.gerechtservice.repository.GerechtRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class GerechtControllerUnitTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GerechtRepository gerechtRepository;

    private String genereerDatestringVandaag(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        return formatter.format(date);
    }

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    void givenGerecht_whenGetGerechtByGerechtnummer_thenReturnJsonGerecht() throws Exception{
        Gerecht gerecht1 = new Gerecht("Pizza Margherita", 9.50);

        String gerechtnummer = genereerDatestringVandaag() + "PM";

        given(gerechtRepository.findGerechtByGerechtNummer(gerechtnummer)).willReturn(gerecht1);

        mockMvc.perform(get("/gerechten/{gerechtnummer}", gerechtnummer))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.naam", is("Pizza Margherita")))
                .andExpect(jsonPath("$.prijs", is(9.50)));
    }


    @Test
    void givenGerechten_whenGetGerechten_thenReturnJsonGerechten() throws Exception{
        Gerecht gerecht1 = new Gerecht("Pizza Margherita", 9.50);
        Gerecht gerecht2 = new Gerecht("Pizza Hawaï", 99.99);
        Gerecht gerecht3 = new Gerecht("Pizza Salami", 11.00);

        List<Gerecht> gerechtList = new ArrayList<>();
        gerechtList.add(gerecht1);
        gerechtList.add(gerecht2);
        gerechtList.add(gerecht3);

        given(gerechtRepository.findAll()).willReturn(gerechtList);

        mockMvc.perform(get("/gerechten"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].naam", is("Pizza Margherita")))
                .andExpect(jsonPath("$[0].prijs", is(9.50)))
                .andExpect(jsonPath("$[1].naam", is("Pizza Hawaï")))
                .andExpect(jsonPath("$[1].prijs", is(99.99)))
                .andExpect(jsonPath("$[2].naam", is("Pizza Salami")))
                .andExpect(jsonPath("$[2].prijs", is(11.00)));
    }
}