package br.com.testedelogin.util;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MetodoUtil {

    /*Driver que será utilizado por todos os testes do sistema. */
    public WebDriver driver;

    /**
     * Construtor da classe.
     * @param driver : Driver utilizado pelo sistema.
     */
    public MetodoUtil(WebDriver driver) {
        this.driver = driver;
    }

    /**Método responsável por escrever em um campo input.
     * @param input Texto que será escrito no campo input informado.
     * @param texto Campo input que será utilizado.
     * @param driver Drive utilizado pelo sistema.
     */
    public void escreverCampoInput(WebElement input, String texto, WebDriver driver) {
        input.sendKeys(texto);
        input.sendKeys(Keys.TAB);
    }

    /**Método responsável por escrever em um campo input.
     * @param driver Driver utilizado pelo sistema.
     * @param elemento Elemento a ser utilizado para o duplo clique.
     */
    public void duploClick(WebDriver driver, WebElement elemento) {
        Actions duploClick = new Actions(driver);
        duploClick.doubleClick(elemento);
    }

    /**
     * Método responsável por digitar letra por letra em inputs difíceis de interagir.
     * @param texto : Texto que deseja digitar.
     * @param input : Input onde o texto vai ser digitado.
     * @throws InterruptedException
     */
    public void escreverLetraPorLetra(String texto, WebElement input) throws InterruptedException {
        String[] letras = texto.split("");
        for (String s : letras) {
            Thread.sleep(100);
            input.sendKeys(s);
        }
            input.sendKeys(Keys.TAB);
    }

    /**
     * Método responsável por limpar um input.
     * @param elemento Elemento que será utilizado para limpar o campo input.
     */
    public void limparCampoInput(WebElement elemento) {
        elemento.clear();
    }

    /**
     * Metodo que retorna um texto de um elemento.
     * @param elemento Elemento que será utilizado para obter o texto.
     * @return Retorna o elemento consultado.
     */
    public String obterTexto(WebElement elemento){
        return elemento.getText();
    }

    /**
     * Metodo que retorna o título da pagina atual.
     * @return : Retorna título da página.
    */
    public String obterTituloPagina(){
        return driver.getTitle();
    }

      /**
     * Metodo que retorna o título da pagina atual.
     * @return : Retorna título da página.
    */
    public String obterURLDaPagina(){
        return driver.getCurrentUrl();
    }

    /**
     * Método para selecionar opções em qualquer Combobox.
     * @param elemento : Elemento do Combobox utilizado.
     * @param texto : Texto do Combobox que será selecionado.
     */
    public void selecionarOpcaoCombobox(WebElement elemento, String texto) {
        Select opcaoSelecionada = new Select(elemento);
        opcaoSelecionada.selectByVisibleText(texto);
    }

    /**
     * Método para aguardar o carregamento de um elemento específico pelo texto.
     * @param elemento : Elemento a ser carregado.
     * @param texto : Texto presente no elemento que deve ser carregado.
     * @param tempoMaximoEspera : Segundos para o carregamento do elemento.
     * @return : Retorna um resultado booleano.
    */
    public void aguardarElementoPorTexto(WebElement elemento, String texto, int tempoMaximoEspera ) {
        WebDriverWait aguarda = new WebDriverWait(driver, Duration.ofSeconds(tempoMaximoEspera));
        aguarda.until(ExpectedConditions.textToBePresentInElement(elemento, texto));
    }

    /**
     * Método para aguardar o carregamento de um elemento específico.
     * @param elemento : Elemento a ser carregado.
     * @param tempoMaximoEspera : Segundos para o carregamento do elemento.
    */
    public void aguardarElementoFicarVisivel(WebElement elemento, int tempoMaximoEspera) {
        WebDriverWait esperar = new WebDriverWait(driver, Duration.ofSeconds(tempoMaximoEspera));
	    elemento = esperar.until(ExpectedConditions.visibilityOf(elemento));
    }

    /**
     * Método para aguardar o carregamento de um elemento específico.
     * @param elemento : Elemento a ser carregado.
     * @param tempoMaximoEspera : Segundos para o carregamento do elemento.
    */
    public void aguardarElementoSerLocalizado(WebElement locator, int tempoMaximoEspera) {
        WebDriverWait esperar = new WebDriverWait(driver, Duration.ofSeconds(tempoMaximoEspera));
	    esperar.until(ExpectedConditions.visibilityOfElementLocated(null));
    }

    /**
     * Método para aguardar o elemento até ele ser clicável.
     * @param tempoMaximoEspera : Segundos para o elemento ser clicável.
     * @param elemento : Elemento a ser clicável.
    */
    public void aguardarElementoSerClicavel(Integer tempoMaximoEspera, WebElement elemento) {
        WebDriverWait esperar = new WebDriverWait(driver, Duration.ofSeconds(tempoMaximoEspera));
        esperar.until(ExpectedConditions.elementToBeClickable(elemento));
    }

    /**
     * Método para aguardar a invisibilidade de um elemento específico.
     * @param elemento : Elemento a ser aguardado.
     * @param tempoMaximoEspera : Tempo máximo em segundos para aguardar a visibilidade do elemento.
     * @param driver : Driver da página.
    */
    public void aguardaElementoNaoEstarVisivel(final WebElement elemento, Integer tempoMaximoEspera, WebDriver driver){
        WebDriverWait esperar = new WebDriverWait(driver, Duration.ofSeconds(tempoMaximoEspera));
	    esperar.until(ExpectedConditions.invisibilityOf(elemento));
    }

    /**
     * Método responsável por mapear um elemento pelo seu seletor css.
     * @param elemento : Elemento "pai" do elemento que deseja encontrar.
     * @param css : Seletor css do elemento.
     * @return Retorna o elemento mapeado por seletor css.
     */
    public WebElement mapearElementoPorCss(WebElement elemento, String css) {
        return elemento.findElement(By.cssSelector(css));
    }

    /**
     * Método responsável por aguardar o carregamento de todas as requisições.
     * @param tempoMaximoEspera : Tempo máximo de espera para serem feitas todas as requisições antes de retornar um erro.
     * @param driver : Driver da página.
     * @return Retorna verdadeiro se todas as requisições foram feitas.
     */
    public Boolean aguardarCarregamentoRequisicoes(int tempoMaximoEspera, WebDriver driver) {
        WebDriverWait esperar = new WebDriverWait(driver, Duration.ofSeconds(tempoMaximoEspera));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        Boolean resposta = esperar.until(javascript -> (boolean) js.executeScript("return jQuery.active==0"));

        return resposta;
    }

    /**
     * Método responsável por aguardar o elemento da página ser carregador e clicar no botão de rastreamento.
     * @param elemento : Elemento "pai" do elemento que deseja encontrar.
     * @return Retorna o elemento mapeado.
     */
    public MetodoUtil aguardaElementoSerCarregado(WebElement elemento) {
        new WebDriverWait(driver, Duration.ofSeconds(20))
        .ignoring(StaleElementReferenceException.class)
        .ignoring(NoSuchElementException.class)
        .ignoring(org.openqa.selenium.TimeoutException.class)
        .until((WebDriver d) -> {

           elemento.click();

            return true;
        });

        return null;
    }

    /**
     * Método responsável por aguardar um tempo pré-determinado.
     * @param tempoDeEspera : Tempo de espera explícito que deverá ser definido no método.
     * @throws InterruptedException
     */
    public void esperaExplicita(long tempoDeEspera) throws InterruptedException {
        Thread.sleep(tempoDeEspera);
    }

    /*Método responsável por validar se os arquivos que estão na pasta retornam conforme os nomes de arquivos esperados. */
    public void validaArquivoPasta() {
        String path = "C:/Users/rafaelqo/Desktop/ValidaArquivo/";
        String[] pesquisar = {"FAT", "REL"};
        File caminho = new File(path);
        boolean caminhoValido = caminho.exists();
        boolean eDiretorio = caminho.isDirectory();
            
        if(caminhoValido && eDiretorio) {
            String[] conteudo = caminho.list();
            for (String arquivo : conteudo) {
                if(arquivo.substring(0, 3).contains(pesquisar[0].toString())) {
                    System.out.println("O arquivo, " + arquivo.substring(0, 3) + ", está na pasta");
                } else {
                    System.out.println("O arquivo, " + arquivo + ", não está na pasta");
                }
            }
        }
    }

    /*Método responsável por mostrar arquivos que estão no diretório. */
    public void visualizarArquivosNaPasta() {
        File file = new File("C:/Users/rafaelqo/Desktop/ValidaArquivo/");
        File afile[] = file.listFiles();
        int i = 0;
        for (int j = afile.length; i < j; i++) {
            File arquivos = afile[i];
            System.out.println(arquivos.getName());
        }
    }

    /**
     * Método responsável por salvar o print na pasta de prints.
     * @param file : Diretório da pasta dos prints.
     * @param descricaoTeste : Descrição dos testes.
     */
    /*Metodo responsável por aguardar um tempo de forma explicita pré-determinada. */
    public String salvarCapturaDeTela(String file, String descricaoTeste) {
        File Destinationfile = new File("src/screenshots/" + descricaoTeste + ".png");
        File scrfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String absolutepath_screen = Destinationfile.getAbsolutePath();

        try {
            FileHandler.copy(scrfile, Destinationfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return absolutepath_screen;
    }
}

