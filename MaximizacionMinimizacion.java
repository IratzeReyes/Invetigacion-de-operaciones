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
        for (int j = 0;j < n; j++){
            tab[i + 1][i] = A[i][j];
        }
        tab[i + 1][n + i] = 1; //holgura
        tab[i + 1][cols-1] = b[i];
    }
    //Mostrar tableo inicail
    System.out.println("/nTableau inicial: ");
     imprimirTableau(tab);

    //Iteraciones
    int iter = 1;
    while(true){

        //Columna pivote
        int colPiv = -1;
        double min = 0;
        for (int j = 0; j < cols -1; j++){
            if (tab[0][j]<min){
                min = tab[0][j];
                colPiv = j;
            }
        }
      if (colPiv == -1) {
                System.out.println("Ya no hay coeficientes negativos en Z. ¡Solución óptima encontrada!");
                break;
            }

            // Fila pivote
            int filaPiv = -1;
            double minRatio = Double.POSITIVE_INFINITY;
            for (int i = 1; i < filas; i++) {
                if (tab[i][colPiv] > 0) {
                    double ratio = tab[i][cols - 1] / tab[i][colPiv];
                    if (ratio < minRatio) {
                        minRatio = ratio;
                        filaPiv = i;
                    }
                }
            }
         if (filaPiv == -1) {
                System.out.println("Problema ilimitado.");
                return;
            }

            System.out.println("\nIteración " + iter++);
            System.out.println("Columna pivote: " + (colPiv + 1));
            System.out.println("Fila pivote: " + (filaPiv + 1));

            // Pivoteo
            double pivote = tab[filaPiv][colPiv];
            for (int j = 0; j < cols; j++) {
                tab[filaPiv][j] /= pivote;
            }
            for (int i = 0; i < filas; i++) {
                if (i != filaPiv) {
                    double factor = tab[i][colPiv];
                    for (int j = 0; j < cols; j++) {
                        tab[i][j] -= factor * tab[filaPiv][j];
                    }
                }
            }
}
