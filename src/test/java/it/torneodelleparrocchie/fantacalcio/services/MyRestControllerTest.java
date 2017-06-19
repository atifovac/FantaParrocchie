package it.torneodelleparrocchie.fantacalcio.services;
/**
 * Created by dsalvatore on 17/06/17.
 */

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MyRestControllerTest {
    private Logger logger = LoggerFactory.getLogger(MyRestControllerTest.class);

    @InjectMocks
    private HelloService service = new HelloService();

    @Test
    public void whenHelloRestWorldThenSuccess() {
        //given

        //when
        String hello = service.hello();

        //then
        assertThat("Qualcosa è andato storto", hello, Matchers.is("Hello, World!!"));

    }

}
