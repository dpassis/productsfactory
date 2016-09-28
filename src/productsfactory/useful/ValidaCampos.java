/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package productsfactory.useful;

import java.text.SimpleDateFormat;

/**
 *
 * @author Daniel
 */
public class ValidaCampos {

    public boolean validaData(String data) {
         SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

         try {
             
             
             format.setLenient(false);//valida data
             format.parse(data).getTime();//transforma String em java.sql.Date
             
             return false;
         } catch (Exception ex) {
             System.out.println(ex.getMessage());
             return true;
        }

     }//fim do validaData==================================================
 public boolean verificaCPF(String cpfNum) {

         cpfNum = cpfNum.replace(".","");//elimina os pontos da máscara
         cpfNum = cpfNum.replace("-","");//elimina o hífen da máscara

         int[] cpf = new int[cpfNum.length()]; //define o valor com o tamanho da string
         int resultP = 0;
         int resultS = 0;

         //converte a string para um array de integer
         for (int i = 0; i < cpf.length; i++) {
             cpf[i] = Integer.parseInt(cpfNum.substring(i, i + 1));
         }

         //calcula o primeiro número(DIV) do cpf
         for (int i = 0; i < 9; i++) {
             resultP += cpf[i] * (i + 1);
         }
         int divP = resultP % 11;

         //se o resultado for diferente ao 10º digito do cpf retorna falso
         if (divP != cpf[9]) {
             return false;
         } else {
             //calcula o segundo número(DIV) do cpf
             for (int i = 0; i < 10; i++) {
                 resultS += cpf[i] * (i);
             }
             int divS = resultS % 11;

             //se o resultado for diferente ao 11º digito do cpf retorna falso
             if (divS != cpf[10]) {
                 return false;
             }
         }

         //se tudo estiver ok retorna verdadeiro
         return true;
     }//fim do calcular cpf ==================================================

public boolean verificaCNPJ(String cnpjNum) {

         cnpjNum = cnpjNum.replace(".","");//elimina o ponto da máscara
         cnpjNum = cnpjNum.replace("/","");//elimina a barra da máscara
         cnpjNum = cnpjNum.replace("-","");//elimina o hífen da máscara

         int[] cnpj = new int[cnpjNum.length()];
         int resultP = 0;
         int resultS = 0;
         int divP = 0;
         int divS = 0;

         //converte string para um array de integer
         for (int i = 0; i < cnpjNum.length(); i++) {
             cnpj[i] = Integer.parseInt(cnpjNum.substring(i, i + 1));
         }

         int j = 6;
         //calcula o primeiro div
         for (int i = 0; i < 12; i++) {
            resultP += cnpj[i] * j;

             j++;
             if (j > 9) {
                 j = 2;
             }
         }
         divP = resultP % 11;

         if (divP != cnpj[12]) {
             return false;
         } else {
             j = 5;
             //calcula o segundo div
             for (int i = 0; i < 13; i++) {
                 resultS += cnpj[i] * j;

                 j++;
                 if (j > 9) {
                     j = 2;
                 }
             }
             divS = resultS % 11;

             if (divS != cnpj[13])
                 return false;
         }
         return true;
     }//fim do calcular CNPJ ==================================================
public boolean isNumber(String number){
		for(char c: number.toCharArray()){
			if(!Character.isDigit(c))
				return false;
		}
		return true;
	}
}

