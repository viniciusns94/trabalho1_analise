/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

/**
 *
 * @author Vinicius_Nogueira_da_Silva
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    private static final Integer OCUPACAO_40 = 40, OCUPACAO_80 = 80, OCUPACAO_90 = 90, OCUPACAO_99 = 99; 
    
    public static void main(String[] args) {
        Start view = new Start();        
/******************* Usuário passa os parâmetros ****************************/

//        view.interfaceUsuario();

//***************************************************************************/

/******************* Parâmetros pré-definidos *******************************/
//        view.cenarios(OCUPACAO_40);
//        view.cenarios(OCUPACAO_80);
//        view.cenarios(OCUPACAO_90);
        view.cenarios(OCUPACAO_99);        

//**************************************************************************/
    }   
}
