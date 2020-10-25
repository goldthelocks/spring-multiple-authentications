package ph.eraine.poc.multipleauth.controller.internal;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class GameControllerTest {

    private MockMvc mvc;

    @InjectMocks
    private GameController controller;

    @BeforeTestClass
    public void setup() {
        mvc = MockMvcBuilders.standaloneSetup(controller).build();
    }



}