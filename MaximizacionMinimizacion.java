import java.util.Scanner;
public class MaximizacionMinimizacion {
public static void main(String[] args) {
    Scanner sc=new Scanner(System.in);
    System.out.println("Metodosimplex paso a paso:  ");
    //deseas maximizar o minimizar
    System.out.println("Deseas maximizar o minimizar (max/min): ");
    String tipo=sc.nextLine().trim().toLowerCase();
    boolean maximizar = tipo.equals("max");
    //Numero de variables y restricciones
    System.out.println("Numero de variables: ");
    int n=sc.nextInt();
    System.out.println("Numero de restricciones: ");
    int m=sc.nextInt();
    //funcion objetivo
    double[] c=new double[n];
    System.out.println("Coeficientes de la funcion objetivo: ");
    for(int i=0;i<n;i++) {
        System.out.println("x"+ (i+1) + ": ");
        c[i]=sc.nextDouble();
    }
    //restricciones
    double[][] A=new double[m][n];
    double[] b=new double[m];
    for(int i=0; i<m; i++){
        System.out.println("Restriccion " + (i + 1));
        for(int j=0; j<n; j++){
            System.out.println("Coeficiente de x" + (j + 1) + ": ");
            A[i][j] = sc.nextDouble();
        }
        System.out.println("Valor del lado derecho: ");
        b[i] = sc.nextDouble();
       }
    //Construccion de tableo
    int filas = m + 1;
    int cols = n + m +1;
    double [][]tab = new double [filas][cols];
    //fila de z
    for(int j =0; j < n;j++){
     tab[0][j] = maximizar ? -c[j]:c[j];
    }

    //restricciones + holguras
    for (int i = 0 < m; i++){
}
