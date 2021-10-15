package com.example.gerechtservice;

import com.example.gerechtservice.model.Gerecht;
import com.example.gerechtservice.repository.GerechtRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class GerechtControllerIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private GerechtRepository gerechtRepository;

    private Gerecht gerecht1 = new Gerecht("Pizza Margherita", 9.50);
    private Gerecht gerecht2 = new Gerecht("Pizza Hawaï", 99.99);
    private Gerecht gerecht3 = new Gerecht("Pizza Salami", 11.00);

    @BeforeEach
    public void beforeAllTests() {
        gerechtRepository.deleteAll();
        gerechtRepository.save(gerecht1);
        gerechtRepository.save(gerecht2);
        gerechtRepository.save(gerecht3);
    }

    @AfterEach
    public void afterAllTests() {
        gerechtRepository.deleteAll();
    }

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void givenGerecht_whenGetGerechtByNaam_thenReturnJsonGerecht() throws Exception {
        mockMvc.perform(get("/gerechten/{naam}", "Pizza Margherita"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.naam", is("Pizza Margherita")))
                .andExpect(jsonPath("$.prijs", is(9.50)));
    }

    @Test
    public void givenGerechten_whenGetGerechten_thenReturnJsonGerechten() throws Exception{
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
