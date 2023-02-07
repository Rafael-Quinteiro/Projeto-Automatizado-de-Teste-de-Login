package br.com.testedelogin.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ElementosBasePO extends BasePO {

    /*Elemento de botão de fechar mensagem que traz modal de detalhes do erro. */
    @FindBy(css = "p-messages > div > div > div > button")
    public WebElement btnFechaMensagemDeDetalhesDeErro;
    
    /**
    * Construtor da classe.
    * @param driver Driver da página.
    */
    public ElementosBasePO(WebDriver driver) {
        super(driver);
    }    
}
