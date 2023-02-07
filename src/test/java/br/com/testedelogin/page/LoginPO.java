package br.com.testedelogin.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import br.com.testedelogin.util.MetodoUtil;

/*Page object da página de login do Teste de Login. */
public class LoginPO extends BasePO {

    /*Instância de MétodoUtil. */ 
    private MetodoUtil metodoUtil;

    /*Elemento de input do login. */
    @FindBy(id = "usuario")
    public WebElement inputUsuario;

    /*Elemento de input da senha. */
    @FindBy(id = "senha")
    public WebElement inputSenha;

    /*Elemento de botão Acessar. */
    @FindBy(css = "button > span")
    public WebElement btnAcessar;
    
    /*Elemento de título da tela do Teste de login. */
    @FindBy(css = "div.ng-star-inserted > h2")
    public WebElement textoTelaEscolhaServico;

    /*Elemento de botão de Sair. */
    @FindBy(css = "div.div-button-rigth > button")
    public WebElement btnSair;

    /*Elemento de div de mensagem de usuário e/ou senha em branco. */
    @FindBy(css = "div.p-toast-detail.ng-tns-c99-2")
    public WebElement textoUsuarioSenhaEmBranco;

    /*Elemento de div de mensagem de usuário e/ou senha inválida. */
    @FindBy(css = "div.p-toast-detail.ng-tns-c99-3")
    public WebElement textoUsuarioSenhaInvalida;

    /*Elemento de div de mensagem de usuário não cadastrado. */
    @FindBy(css = "div.p-toast-detail.ng-tns-c99-4")
    public WebElement textoUsuarioNaoCadastrado;

    /*Elemento de botão de fechar mensagem de erro. */
    @FindBy(css = "p-toastitem > div > div > button")
    public WebElement btnFecharMensagemErro;

     /**
     * Construtor da classe.
     * @param driver Driver da página.
     */
    public LoginPO(WebDriver driver) {
        super(driver);
    }

     /**
     * Método responsável por efetuar o login no Teste de Login.
     * @param login Login do usuário.
     * @param senha Senha do usuário.
     * @param driver Driver da página.
     */
    public void efetuarLogin(String login, String senha, WebDriver driver){
        
        metodoUtil = new MetodoUtil(driver);

        metodoUtil.limparCampoInput(inputUsuario);
        metodoUtil.limparCampoInput(inputSenha);

        metodoUtil.escreverCampoInput(inputUsuario, login, driver);
        metodoUtil.escreverCampoInput(inputSenha, senha, driver);

        btnAcessar.click();
    }
}
    

