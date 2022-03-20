package com.insurance.calculation.premiumpolicy.controller;

import com.insurance.calculation.premiumpolicy.domain.Policy;
import com.insurance.calculation.premiumpolicy.service.PremiumCalculationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static com.insurance.calculation.premiumpolicy.TestHelper.createPolicy;
import static com.insurance.calculation.premiumpolicy.TestHelper.mapToJson;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PremiumCalculatorResourceTest {

    private static final String API_URL = "/api/premium-calculations";
    private final Policy POLICY = createPolicy(BigDecimal.valueOf(250), BigDecimal.valueOf(150));

    @Mock
    private PremiumCalculationServiceImpl mockPremiumCalculationService;

    @InjectMocks
    private PremiumCalculatorResource controller;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void calculatePremiumPolicy() throws Exception {
        var expectedResponse = BigDecimal.valueOf(14.5);

        when(mockPremiumCalculationService.calculatePremium(POLICY)).thenReturn(expectedResponse);

        mockMvc.perform(
                        MockMvcRequestBuilders.post(API_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapToJson(POLICY)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().string("14.5 EUR"));

        verify(mockPremiumCalculationService).calculatePremium(POLICY);
    }

    @Test
    void calculatePremiumPolicyReturnBadRequest() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.post(API_URL)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapToJson(null)))
                .andDo(print())
                .andExpect(status().isBadRequest());

        verifyNoInteractions(mockPremiumCalculationService);
    }

    @Test
    void calculatePremiumPolicyReturnNotFound() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/wrong-url")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapToJson(POLICY)))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}