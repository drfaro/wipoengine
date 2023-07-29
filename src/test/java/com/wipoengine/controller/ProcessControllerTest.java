package com.wipoengine.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.wipoengine.models.ProcessModel;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Date;
import java.util.List;



@ExtendWith(SpringExtension.class)
@WebMvcTest(ProcessController.class)
class ProcessControllerTest {

    @MockBean
    ProcessController processController;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testfindAll() throws Exception {
        String testTitle = "Title"+(new Date().toString());
        ProcessModel processModel = new ProcessModel();
        processModel.setTitle(testTitle);
        List<ProcessModel> listProcessModel = List.of(processModel);
        var status = HttpStatusCode.valueOf(200);
        ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(listProcessModel, status);

        Mockito.when(processController.getAllProcess()).thenReturn(responseEntity);

        mockMvc.perform(get("/process"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].title", Matchers.is(testTitle)));
    }

}