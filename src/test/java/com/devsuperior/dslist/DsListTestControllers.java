package com.devsuperior.dslist;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class GameControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testFindAllGames() throws Exception {
        mockMvc.perform(get("/games"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists());
    }

    @Test
    void testFindGameById() throws Exception {
        Long gameId = 1L; // Supondo que exista um jogo com ID 1 no banco de testes
        mockMvc.perform(get("/games/" + gameId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(gameId));
    }
}

@SpringBootTest
@AutoConfigureMockMvc
class GameListControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testFindAllGameLists() throws Exception {
        mockMvc.perform(get("/lists"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists());
    }

    @Test
    void testFindGamesByList() throws Exception {
        Long listId = 1L; // Supondo que exista uma lista com ID 1 no banco de testes
        mockMvc.perform(get("/lists/" + listId + "/games"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").exists());
    }

    @Test
    void testMoveGameInList() throws Exception {
        Long listId = 1L;
        String jsonBody = "{\"sourceIndex\": 0, \"destinationIndex\": 1}";

        mockMvc.perform(post("/lists/" + listId + "/replacement")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk());
    }
}

