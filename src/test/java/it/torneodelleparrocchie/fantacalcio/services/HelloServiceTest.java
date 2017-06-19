package it.torneodelleparrocchie.fantacalcio.services;

import it.torneodelleparrocchie.fantacalcio.services.impl.HelloServiceImpl;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertThat;

/**
 * Created by dsalvatore on 19/06/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class HelloServiceTest {

    @InjectMocks
    private HelloService service = new HelloServiceImpl();

    @Test
    public void whenHelloThenSuccess() {
        //given

        //when
        String hello = service.hello();

        //then
        assertThat("Qualcosa è andato storto", hello, Matchers.is("Hello, World!!"));

    }
}
