package br.com.testedelogin.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/*Classe base para todas as pages do projeto. Ao criar um novo Page object, deve se fazer a herança dessa classe. */
public abstract class BasePO {
    
    /*Driver que será utilizado por todos os testes do sistema. */
    protected WebDriver driver;

    /*Método construtor responsável por iniciar os objetos do PageFactory*/
    protected BasePO(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
