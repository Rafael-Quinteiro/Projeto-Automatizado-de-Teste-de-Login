package br.com.testedelogin.global;

/*Classe responsável por armazenar variáveis utilizadas por todo o projeto. */
public class Constantes {

    /**Versão do Chrome Driver */
    public static final String VERSAO_CHROME_DRIVER = "109";
    
    /*Tempo máximo de espera de elementos. */
    public final static int TEMPO_MAXIMO_ESPERA = 20;

    /*Constante da página inicial da tela de login. */
    public static final String URL_BASE_LOGIN = "https://testedelogin.com.br/testedelogin/public/login"; 

    /*Constante da página inicial do Teste de Login. */
    public static final String URL_INICIAL_TESTE_DE_LOGIN = "https://testedelogin.com.br/testedelogin/private";

    /*Constante do caminho do chromedriver. */
    public static final String PATH_CHROME_DRIVER = "src/test/java/br/com/testedelogin/resource/chromedriver-" + VERSAO_CHROME_DRIVER +".exe";

    /*Versão do navegador usado para testes. */
    public static final String NAVEGADOR = "Google Chrome" + VERSAO_CHROME_DRIVER;

    /*Sistema operacional do ambiente de testes. */
    public static final String SISTEMA_OPERACIONAL = System.getProperty("os.name");

    /*Constante de texto da página de login. */
    public static final String TEXTO_TESTE_LOGIN = "Teste de Login";
                           
    /*Login de e-mail de acesso. */
    public final String LOGIN_EMAIL = "testedelogin@testedelogin.com";

    /*Senha de acesso. */
    public final String SENHA = "Abc*1234";

    /*Login de e-mail de acesso inválido */
    public final String LOGIN_EMAIL_INVALIDO = "teste@testedelogin.com";

    /*Senha de acesso inválida. */
    public final String SENHA_INVALIDA = "123456";

    /*Login de e-mail vazio */
    public final String LOGIN_EMAIL_VAZIO = "";

    /*Senha de acesso vazia. */
    public final String SENHA_VAZIA = "";
}
