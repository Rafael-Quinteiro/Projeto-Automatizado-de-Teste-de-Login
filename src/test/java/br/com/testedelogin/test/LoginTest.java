package br.com.testedelogin.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.aventstack.extentreports.ExtentTest;

import br.com.testedelogin.global.Constantes;
import br.com.testedelogin.page.LoginPO;
import br.com.testedelogin.util.MetodoUtil;

/*Classe de testes do Teste de Login. */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LoginTest extends BaseTest {

    /*Instância de MétodoUtil. */ 
    private static MetodoUtil metodoUtil;

    /*Instância de Constantes. */ 
    private static Constantes constantes;

    /*Instância de LoginPO. */ 
    private static LoginPO loginPO;

    /*Instancia de SparkReporterUtil. */
    public static SparkReporterUtil sparkReporterUtil;

    /*Nome da classe de teste. */
    private static final String descricaoRotina = "Login";

    /**Método responsável por iniciar as instâncias das classes necessárias para executar os testes. */
    @BeforeClass
    public static void iniciarTestes() {
        sparkReporterUtil = new SparkReporterUtil(driver);
        metodoUtil = new MetodoUtil(driver);
        constantes = new Constantes();
        loginPO = new LoginPO(driver);

        nomearTituloRelatorio(descricaoRotina);
    }

    /**
     * Método responsável por iniciar todos os pontos necessários antes de executar qualquer teste do sistema. *
     * @throws IOException Indica uma exceção I/O.
     */
    /*Metodo responsável por mover arquivo de relatório gerado para a devida pasta. */
    @AfterClass
    public static void MoveArquivoDeRelatorio() throws IOException {
        Path temp = Files.move
        (Paths.get("C:/automation-testedelogin/reports/Reports-Automation.html"),
        Paths.get("C:/automation-testedelogin/reports/Login/" + descricaoRotina + ".html"));
  
        if(temp != null)
        {
            System.out.println("");
        } else {
            System.out.println("Falha ao mover o arquivo para a pasta de relatório.");
        }
    }

    /*Teste responsável por tentar realizar login com e-mail e senha, vazios. */
    @Test
    public void TC001_nao_Deve_Realizar_Login_Com_Dados_De_Usuario_E_Senha_Em_Branco() {
            String descricaoTeste = "TC001_nao_Deve_Ser_Possivel_Realizar_Login_Com_Dados_De_Usuario_E_Senha_Em_Branco";
            String mensagemErroUsuarioSenhaEmBranco = "Usuario e/ou senha nao pode estar em branco!";
            String nomeScreenshot = this.getClass().getSimpleName() + descricaoTeste.substring(0, 5);
            ExtentTest teste = extent.createTest(descricaoTeste).assignDevice(Constantes.NAVEGADOR).assignDevice(Constantes.SISTEMA_OPERACIONAL);
            
        try {
            metodoUtil.limparCampoInput(loginPO.inputUsuario);
            metodoUtil.limparCampoInput(loginPO.inputSenha);

            metodoUtil.escreverCampoInput(loginPO.inputUsuario, constantes.LOGIN_EMAIL_VAZIO, driver);
            metodoUtil.escreverCampoInput(loginPO.inputSenha, constantes.SENHA_VAZIA, driver);

            loginPO.btnAcessar.click();

            metodoUtil.aguardarElementoFicarVisivel(loginPO.textoUsuarioSenhaEmBranco, Constantes.TEMPO_MAXIMO_ESPERA);
            Assert.assertEquals(mensagemErroUsuarioSenhaEmBranco, metodoUtil.obterTexto(loginPO.textoUsuarioSenhaEmBranco));

            loginPO.btnFecharMensagemErro.click();

            sparkReporterUtil.rotularTesteSucesso(teste);
        } catch (Throwable t) {
            sparkReporterUtil.encerrarTesteFalha(teste, nomeScreenshot, t);
            irParaPaginaInicialDoTsteDeLogin(driver);
        }
    }

    /*Teste responsável por tentar realizar login com senha inválida. */
    @Test
    public void TC002_nao_Deve_Realizar_Login_Com_Senha_Invalida() {
            String descricaoTeste = "TC002_nao_Deve_Realizar_Login_Com_Senha_Invalida";
            String mensagemErroUsuarioOuSenhaInvalida = "Usuário e/ou senha inválidos, ou senha expirada. Favor logar novamente.";
            String nomeScreenshot = this.getClass().getSimpleName() + descricaoTeste.substring(0, 5);
            ExtentTest teste = extent.createTest(descricaoTeste).assignDevice(Constantes.NAVEGADOR).assignDevice(Constantes.SISTEMA_OPERACIONAL);

        try {
            metodoUtil.limparCampoInput(loginPO.inputUsuario);
            metodoUtil.limparCampoInput(loginPO.inputSenha);

            metodoUtil.escreverCampoInput(loginPO.inputUsuario, constantes.LOGIN_EMAIL, driver);
            metodoUtil.escreverCampoInput(loginPO.inputSenha, constantes.SENHA_INVALIDA, driver);

            loginPO.btnAcessar.click();

            metodoUtil.aguardarElementoFicarVisivel(loginPO.textoUsuarioSenhaInvalida, Constantes.TEMPO_MAXIMO_ESPERA);
            Assert.assertEquals(mensagemErroUsuarioOuSenhaInvalida, metodoUtil.obterTexto(loginPO.textoUsuarioSenhaInvalida));

            loginPO.btnFecharMensagemErro.click();

            sparkReporterUtil.rotularTesteSucesso(teste);
        } catch (Throwable t) {
            sparkReporterUtil.encerrarTesteFalha(teste, nomeScreenshot, t);
            irParaPaginaInicialDoTsteDeLogin(driver);
        } 
    }

    /*Teste responsável por tentar realizar login com e-mail inválido. */
    @Test
    public void TC003_nao_Deve_Realizar_Login_Com_Email_Invalido() {
            String descricaoTeste = "TC003_nao_Deve_Realizar_Login_Com_Email_Invalido";
            String mensagemErroUsuarioNaoCadastrado = "Usuário não cadastrado!";
            String nomeScreenshot = this.getClass().getSimpleName() + descricaoTeste.substring(0, 5);
            ExtentTest teste = extent.createTest(descricaoTeste).assignDevice(Constantes.NAVEGADOR).assignDevice(Constantes.SISTEMA_OPERACIONAL);
            
        try {
            metodoUtil.limparCampoInput(loginPO.inputUsuario);
            metodoUtil.limparCampoInput(loginPO.inputSenha);

            metodoUtil.escreverCampoInput(loginPO.inputUsuario, constantes.LOGIN_EMAIL_INVALIDO, driver);
            metodoUtil.escreverCampoInput(loginPO.inputSenha, constantes.SENHA, driver);

            loginPO.btnAcessar.click();
            
            metodoUtil.aguardarElementoFicarVisivel(loginPO.textoUsuarioNaoCadastrado, Constantes.TEMPO_MAXIMO_ESPERA);
            Assert.assertEquals(mensagemErroUsuarioNaoCadastrado, metodoUtil.obterTexto(loginPO.textoUsuarioNaoCadastrado));

            loginPO.btnFecharMensagemErro.click();

            sparkReporterUtil.rotularTesteSucesso(teste);
        } catch (Throwable t) {
            sparkReporterUtil.encerrarTesteFalha(teste, nomeScreenshot, t);
            irParaPaginaInicialDoTsteDeLogin(driver);
        }
    }

    /*Teste responsável por realizar logout. */
    @Test
    public void TC004_deve_Realizar_Logout() {
            String descricaoTeste = "TC004_deve_Realizar_Logout";
            String nomeScreenshot = this.getClass().getSimpleName() + descricaoTeste.substring(0, 5);
            ExtentTest teste = extent.createTest(descricaoTeste).assignDevice(Constantes.NAVEGADOR).assignDevice(Constantes.SISTEMA_OPERACIONAL);

        try {
            metodoUtil.limparCampoInput(loginPO.inputUsuario);
            metodoUtil.limparCampoInput(loginPO.inputSenha);
   
            metodoUtil.escreverCampoInput(loginPO.inputUsuario, constantes.LOGIN_EMAIL, driver);
            metodoUtil.escreverCampoInput(loginPO.inputSenha, constantes.SENHA, driver);
   
            loginPO.btnAcessar.click();
   
            metodoUtil.aguardarElementoPorTexto(loginPO.textoTelaEscolhaServico, Constantes.TEXTO_TESTE_LOGIN, Constantes.TEMPO_MAXIMO_ESPERA);

            loginPO.btnSair.click();

            Assert.assertTrue(metodoUtil.obterURLDaPagina().equals(Constantes.URL_BASE_LOGIN));
   
            sparkReporterUtil.rotularTesteSucesso(teste);
        } catch (Throwable t) {
            sparkReporterUtil.encerrarTesteFalha(teste, nomeScreenshot, t);
            irParaPaginaInicialDoTsteDeLogin(driver);
        }
    }          
}
