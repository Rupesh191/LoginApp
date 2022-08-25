package com.project.backend.test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.project.backend.User;
import com.project.backend.UserController;
import com.project.backend.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {
   @Mock
   private UserService userService;
   @InjectMocks
   private UserController userController;
   @Autowired
   private MockMvc mockMvc;
   @BeforeEach
   public void setup(){
//   new User(1,"Rupesh","Rathore","rupesh_rathore","rupesh@gmail.com","rupesh","8349719415");
   mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
   }
   @AfterEach
   void tearDown() {
   }
   
   @Test
   public void testregister () throws Exception {

       MockHttpServletRequestBuilder builder =
                           MockMvcRequestBuilders.post("/user/register")
                                                 .contentType(MediaType.APPLICATION_JSON)
                                                 .content(createUserInJson("Rupesh",
                                                		                   "Rathore",
                                                		                   "rupesh_rathore",
                                                                           "rupesh@gmail.com",
                                                                           "8349719415",
                                                                           "rupesh"));

       this.mockMvc.perform(builder)
                   .andExpect(MockMvcResultMatchers.status()
                                                   .isOk());



       builder = MockMvcRequestBuilders.get("/user")
                                       .accept(MediaType.APPLICATION_JSON);
       this.mockMvc.perform(builder)
                   .andExpect(MockMvcResultMatchers.status()
                                                   .isOk());

   }

   private static String createUserInJson (String firstname, String lastname, String username, String email, String password, String number) {
       return "{ \"firstname\": \"" + firstname + "\", " +
    		               "\"lastname\":\"" + lastname + "\"," +
    		               "\"username\":\"" + username + "\"," +
                           "\"emailAddress\":\"" + email + "\"," +
                           "\"password\":\"" + password + "\"}" +
                           "\"number\":\"" + number + "\",";
   }
   
   @Test
   public void testlogin() throws Exception{
       MockHttpSession session = new MockHttpSession();

       User user = new User();
       user.setUsername("rupesh_rathore");
       user.setPassword("rupesh");

       session.setAttribute("user", user);

       MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/user")
                                       .session(session);
       this.mockMvc.perform(builder)
                   .andExpect(MockMvcResultMatchers.status()
                                                   .isOk());
   }
   
   @Test
   public void testGetAllUsers () throws Exception {

       MockHttpServletRequestBuilder builder =
                           MockMvcRequestBuilders.get("/user");


       this.mockMvc.perform(builder)
                   .andExpect(MockMvcResultMatchers.status()
                                                   .isOk());
   }
   }
   

