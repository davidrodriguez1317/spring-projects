package com.dro.springprojects.testcompany.integrationtests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration
(
  {
   "file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml"
  }
)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class CompanyControllerIT {
    private static final Long COMPANY_ID_1 = 1L;
    private static final Long COMPANY_ID_2 = 2L;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(this.webApplicationContext)
                .build();
    }

    /**
     * This method tests that we insert a company, and then we receive that same company with
     * its new id and a 201 CREATED
     * @throws Exception
     */
    @Test
    public void postCompany_ShouldReturnCreatedStatusAndCorrectInfo() throws Exception {
        String jsonExpected = "{\"id\":2,\"name\":\"Big Company\",\"address\":\"Elm Street\",\"city\":\"Chicago\",\"country\":\"USA\",\"email\":\"freddycomesforyou@hotmail.com\",\"phone\":\"+0031 677 89 90 66\",\"beneficialOwners\":[]}";

        mockMvc.perform(post("/company")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Big Company\",\"address\":\"Elm Street\",\"city\":\"Chicago\",\"country\":\"USA\",\"email\":\"freddycomesforyou@hotmail.com\",\"phone\":\"+0031 677 89 90 66\",\"beneficialOwners\":[]}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(jsonExpected));
    }
    
    /**
     * This method tests that we insert an existing company, and when we do not get it
     * we receive a 403 FORBIDDEN
     * @throws Exception
     */
    @Test
    public void postCompany_ShouldReturnForbiddenStatus() throws Exception {

        mockMvc.perform(post("/company")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Large Company S.L.\",\"address\":\"Street large\",\"city\":\"Palma\",\"country\":\"Spain\",\"email\":\"thisemail@google.com\",\"phone\":\"+0034 971 55 55 55\",\"beneficialOwners\":[]}"))
                .andExpect(status().isForbidden());      
    }
    
    /**
     * This method returns a Bad Request, as the object is not valid
     * @throws Exception
     */
    @Test
    public void postCompany_ShouldReturnBadRequest() throws Exception {

        mockMvc.perform(post("/company")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"B\",\"address\":\"Elm Street\",\"city\":\"Chicago\",\"country\":\"USA\",\"email\":\"freddycomesforyou@hotmail.com\",\"phone\":\"+0031 677 89 90 66\",\"beneficialOwners\":[]}"))
                .andExpect(status().isBadRequest());      
    }
    
    /**
     * This method tests that we get the mocked initial company in our app when searching for it by id
     * The expected result must be a 200 OK
     * @throws Exception
     */
    
    @Test
    public void getCompany_ShouldReturnCorrectInfo() throws Exception {
        String jsonExpected = "{\"id\":1,\"name\":\"Large Company S.L.\",\"address\":\"Street large\",\"city\":\"Palma\",\"country\":\"Spain\",\"email\":\"thisemail@google.com\",\"phone\":\"+0034 971 55 55 55\",\"beneficialOwners\":[]}";

        mockMvc.perform(get("/company/{id}", COMPANY_ID_1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(jsonExpected));
    }
    
    /**
     * This method tries to get a non-existing company, so it returns a 404 NOT FOUND
     * @throws Exception
     */
    @Test
    public void getCompany_ShouldReturnNoInfoAsItDoesNotExists() throws Exception {

        mockMvc.perform(get("/company/{id}", COMPANY_ID_2))
                .andExpect(status().isNotFound());
    }
    
    
    /**
     * This method returns all the companies and a 200 OK
     * @throws Exception
     */
    @Test
    public void getCompanies_ShouldReturnCorrectInfo() throws Exception {
        String jsonExpected = "[{\"id\":1,\"name\":\"Large Company S.L.\",\"address\":\"Street large\",\"city\":\"Palma\",\"country\":\"Spain\",\"email\":\"thisemail@google.com\",\"phone\":\"+0034 971 55 55 55\",\"beneficialOwners\":[]}]";

        mockMvc.perform(get("/company/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(jsonExpected));
    }

    /**
     * This methods checks that the process of updating results in a 200 OK and the object updated
     * @throws Exception
     */
    @Test
    public void updateCompany_ShouldReturnCorrectInfo() throws Exception {
        String jsonExpected = "{\"id\":1,\"name\":\"VERY Large Company S.L.\",\"address\":\"Street large\",\"city\":\"Palma\",\"country\":\"Spain\",\"email\":\"thisemail@google.com\",\"phone\":\"+0034 971 55 55 55\",\"beneficialOwners\":[]}";

        mockMvc.perform(put("/company/{id}", COMPANY_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"name\":\"VERY Large Company S.L.\",\"address\":\"Street large\",\"city\":\"Palma\",\"country\":\"Spain\",\"email\":\"thisemail@google.com\",\"phone\":\"+0034 971 55 55 55\",\"beneficialOwners\":[]}"))
                .andExpect(status().isOk())
                .andExpect(content().string(jsonExpected));
    }
    
    /**
     * This methods checks that the process of updating results in a 404 NOT FOUND
     * if the company does not exists
     * @throws Exception
     */
    @Test
    public void updateCompany_ShouldReturnNotFoundStatus() throws Exception {

        mockMvc.perform(put("/company/{id}", COMPANY_ID_2)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":2,\"name\":\"VERY Large Company S.L.\",\"address\":\"Street large\",\"city\":\"Palma\",\"country\":\"Spain\",\"email\":\"thisemail@google.com\",\"phone\":\"+0034 971 55 55 55\",\"beneficialOwners\":[]}"))
                .andExpect(status().isNotFound());
    }
    
    /**
     * This method checks that the process of updating results in a 200 OK and the object updated
     * @throws Exception
     */
    @Test
    public void updateCompanyWithBeneficialOwner_ShouldReturnCorrectInfo() throws Exception {
        String jsonExpected = "{\"id\":1,\"name\":\"Large Company S.L.\",\"address\":\"Street large\",\"city\":\"Palma\",\"country\":\"Spain\",\"email\":\"thisemail@google.com\",\"phone\":\"+0034 971 55 55 55\",\"beneficialOwners\":[{\"id\":0,\"name\":\"David\",\"percentageOwnership\":50.1},{\"id\":1,\"name\":\"John\",\"percentageOwnership\":49.9}]}";

        mockMvc.perform(put("/company/beneficialowner/{id}", COMPANY_ID_1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{\"name\": \"David\",\"percentageOwnership\": 50.1},{\"name\":\"John\",\"percentageOwnership\":49.9}]"))
                .andExpect(status().isOk())
                .andExpect(content().string(jsonExpected));
    }
    
    /**
     * This methods checks that the process of updating results in a 404 NOT FOUND
     * if the company does not exists
     * @throws Exception
     */
    @Test
    public void updateCompanyWithBeneficialOwner_ShouldReturnNotFoundStatus() throws Exception {

        mockMvc.perform(put("/company/beneficialowner/{id}", COMPANY_ID_2)
                .contentType(MediaType.APPLICATION_JSON)
                .content("[{\"name\": \"David\",\"percentageOwnership\": 50.1},{\"name\":\"John\",\"percentageOwnership\":49.9}]"))
                .andExpect(status().isNotFound());
    }

}
