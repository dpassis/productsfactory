/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package productsfactory.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.SplashScreen;

/**
 *
 * @author Daniel
 */
public class SplashTeste {
    
   final SplashScreen splash ;
  //texto que se muestra a medida que se va cargando el screensplah
  final String[] texto = {"Driver de Conexão Ok!" ,"Conexão Ok!","Banco de Dados Ok!", "Bibliotecas Ok!",
                          "Formulários Ok!","Relatórios Ok!","Arquivos de Configuração Ok!","Imagens do Sistema Ok!",
                          "Rede Ok!", "Usuários do Sistema Ok!", "Sistema Ok!",
                          "Login Ok!","aguarde...",
                          ""};

  public SplashTeste() {
        splash = SplashScreen.getSplashScreen();
  }

   public void animar()
   {
        if (splash != null)
        {
            Graphics2D g = splash.createGraphics();
            for(int i=1; i<texto.length; i++)
            {                       
                //se pinta texto del array
                g.setColor( new Color(0, 0, 128));//color de fondo
                g.fillRect(160, 328,280,12);//para tapar texto anterior
                g.setColor(Color.white);//color de texto 
                g.drawString("Inicializando...     "+texto[i-1]+" ", 160, 338);                
                g.setColor(Color.green);//color de barra de progeso
                g.fillRect(100, 308,(i*442/texto.length), 13);//la barra de progreso
                //se pinta una linea segmentada encima de la barra verde
                float dash1[] = {2.0f};
                BasicStroke dashed = new BasicStroke(9.0f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,5.0f, dash1, 0.0f);
                g.setStroke(dashed);
                g.setColor(Color.GREEN);//color de barra de progeso
                g.setColor( new Color(0, 0, 128));
                g.drawLine(100,314, 510, 314);                
                //se actualiza todo
                splash.update();
  try {
   Thread.sleep(1000);
  } catch(InterruptedException e) { }
            }
    splash.close();
 }
        //una vez terminada la animación se muestra la aplicación principal
         try {
             //new miprograma().setVisible(true);
         }
 catch (Exception e) {
            System.out.println(e.getMessage());
        }
   }
}
