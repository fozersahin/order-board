package com.silverbars.orderboard;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.silverbars.orderboard.constant.OrderType;
import com.silverbars.orderboard.model.Order;
import com.silverbars.orderboard.model.Summary;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class OrderBoardApplicationTests {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void getSummary() throws Exception {

        Order order = new Order("user1", 1, 349, OrderType.BUY);


        String jsonValue = objectMapper.writeValueAsString(order);


        MvcResult registerResult = this.mockMvc.perform(post("/order/register").contentType(MediaType.APPLICATION_JSON).content(jsonValue))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = registerResult.getResponse().getContentAsString();
        Boolean response = objectMapper.readValue(contentAsString, Boolean.class);

        assertEquals(true, response);


        MvcResult summaryResult = this.mockMvc.perform(get("/order/summary")).andDo(print())
                .andExpect(status().isOk())
                .andExpect((content().contentType("application/json")))
                .andReturn();


        Summary summary = new Summary(OrderType.BUY, 349, 1);

        List<Summary> summaryMockList = new ArrayList<>();
        summaryMockList.add(summary);

        String summaryContentAsString = summaryResult.getResponse().getContentAsString();
        List<Summary> summaryResponse = objectMapper.readValue(summaryContentAsString, objectMapper.getTypeFactory().constructCollectionType(List.class, Summary.class));

        assertEquals(summaryMockList, summaryResponse);

    }


    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void removeOrder() throws Exception {

        Order order = new Order("user1", 1, 349, OrderType.BUY);


        String jsonValue = objectMapper.writeValueAsString(order);


        //adds order and checks it
        MvcResult registerResult = this.mockMvc.perform(post("/order/register").contentType(MediaType.APPLICATION_JSON).content(jsonValue))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = registerResult.getResponse().getContentAsString();
        Boolean response = objectMapper.readValue(contentAsString, Boolean.class);

        assertEquals(true, response);

        //removes order
        this.mockMvc.perform(post("/order/cancel").contentType(MediaType.APPLICATION_JSON).content(jsonValue))
                .andExpect(status().isOk())
                //.andExpect(content().contentType("boolean"))
                .andReturn();

        List<Summary> expectedSummaryList = new ArrayList<>();


        //we expecting summary report empty
        MvcResult summaryResult = this.mockMvc.perform(get("/order/summary")).andDo(print())
                .andExpect(status().isOk())
                .andExpect((content().contentType("application/json")))
                .andReturn();

        String summaryContentAsString = summaryResult.getResponse().getContentAsString();
        List<Summary> cancelOrderResponse = objectMapper.readValue(summaryContentAsString, objectMapper.getTypeFactory().constructCollectionType(List.class, Summary.class));

        assertEquals(expectedSummaryList, cancelOrderResponse);

    }


    //null check tests
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void addOrderNull() throws Exception {

        Order order = new Order(null, 1, 349, OrderType.BUY);
        String jsonValue = objectMapper.writeValueAsString(order);

        this.mockMvc.perform(post("/order/register").contentType(MediaType.APPLICATION_JSON).content(jsonValue))
                .andExpect(status().is4xxClientError());

        order = new Order("user1", 1, 349, null);
        jsonValue = objectMapper.writeValueAsString(order);

        this.mockMvc.perform(post("/order/register").contentType(MediaType.APPLICATION_JSON).content(jsonValue))
                .andExpect(status().is4xxClientError());


    }

}
