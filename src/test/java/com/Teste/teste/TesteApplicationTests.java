package com.Teste.teste;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TesteApplicationTests {

	//Testando a classe Main, basicamente só chama a classe main com um vetor de args vazio
	@Test
	void main() {
      TesteApplication.main(new String[]{});

	}

}
