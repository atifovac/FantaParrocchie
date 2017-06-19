package it.torneodelleparrocchie.fantacalcio.controllers;
/**
 * Created by dsalvatore on 17/06/17.
 */

import it.torneodelleparrocchie.fantacalcio.services.impl.HelloServiceImpl;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class MyRestControllerTest {

    @InjectMocks
    private MyRestController controller = new MyRestController();

    @Mock
    private HelloServiceImpl helloService;

    @Test
    public void whenHelloRestWorldThenSuccess() {
        //given
        doReturn("Hello, World!!").when(helloService).hello();

        //when
        String hello = controller.helloRestWorld();

        //then
        assertThat("Qualcosa è andato storto", hello, Matchers.is("Hello, World!!"));

    }

}
