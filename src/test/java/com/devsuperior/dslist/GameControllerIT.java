package com.devsuperior.dslist;

import com.devsuperior.dslist.dto.GameDTO;
import com.devsuperior.dslist.dto.GameMinDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class GameControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void findById_ShouldReturnGameDTO_WhenGameExists() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/games/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").isString());
    }

    @Test
    void findById_ShouldReturnNotFound_WhenGameDoesNotExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/games/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void findAll_ShouldReturnListOfGames() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/games"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].title").isString());
    }
}
