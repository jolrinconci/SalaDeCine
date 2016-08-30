/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package saladecine;

/**
 * Este programa nos permite administrar una taquilla de sala de cine, para que
 * los clientes pueden relizar reservas, cancelarlas, descuentos de pagos con
 * tarjeta y venta normal de boletería; además de mostrar los asientos 
 * disponibles, vendidos y reservados.
 * 
 * @author José Luis Rincón Ciriano
 * @version 1.0
 * since 28/08/2016
 */
import java.util.Scanner;//importamos Scanner para usarlo en la captura del teclado.
public class SalaDeCine {
    //variables globales.
    static int[][] asientos = new int[11][20];//En esta matriz se guardan la disponibilidad de los asientos. 
                                              //1 diponible, 2 en reserva, y 3 vendido.
    static int ventaTotal=0;//total de las ventas. Inclute la venta y recarga de tarjetas.
    static int[][] reservaFila = new int[10][50];//limitado a 10 reservas por tarcine.
    static int[][] reservaColumna = new int[10][50];//limitado a 10 reservas por tarcine.
    static int[][] tarcine = new int[4][50];//información de las tarjetas tarcine. Actualmente hasta 50 tarjetas.
    //en las filas del 0 al 2 tenemos:(inicialmente todos los valores son iguales a cero)
    //0:documento ó número de tarjeta.
    //1:saldo.
    //2:tiene reservas?.(0 no, 1 si)
    //3:tamaño de la reserva.
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //Scanner teclado = new Scanner(System.in);//captura el teclado.
        //declaración de variables.
        int[] columnas = new int[20];//variable que guarda el valor de columnas que se muestra en pantalla.
        char[] filas = new char[11];//variable que guarda el valor de filas que se muestra en pantalla.
        boolean salida = false;
        //damos valores iniciales a nuestras variables.
        columnas=valorInicialColumnas();//Valor de columnas que se muestra en pantalla.
        filas=valorInicialFilas();//Valor de filas que se muestra en pantalla.
        valorInicialAsientos();//Todos los asientos disponibles.
        valorInicialTarcine();
        valorInicialReservas();
        
        while(!salida){//ciclo principal del programa.
            int opcion = menuPrincipal();
            if(opcion==1){//1.Vender boletas
                venderBoletas();
                System.out.println("Gracias por comprar!");
            }else if(opcion==2){//2.Reservar boletas
                reservarBoletas();
            }else if(opcion==3){//3.Crear TARCINE
                crearTarcine();
                System.out.println("Se ha creado su tarjeta!");
            }else if(opcion==4){//4.Recargar TARCINE
                recargarTarcine();
            }else if(opcion==5){//5.Pagar reservas
                pagarReservas();
            }else if(opcion==6){//6.Cancelar reserva
                anularReservas();
            }else if(opcion==7){//7.Mostrar disponibles
                System.out.println("Lista de asientos!      V:vendido  R:reservado");
                dibujarAsientos(columnas,filas);//imprime en pantalla los asientos disponibles.
            }else if(opcion==8){//8.Mostrar total de ventas
                System.out.println("El total de ventas es de: $"+ventaTotal);
            }else if(opcion==9){//9.Reiniciar programa
                valorInicialAsientos();//Todos los asientos disponibles.
                ventaTotal=0;
                valorInicialTarcine();//Se eliminan los datos existentes de tarcine.
                valorInicialReservas();//Se eliminan las reservas de tarcine.
                System.out.println("Datos restablecidos!");
            }else if(opcion==0){//0.Salir
                System.out.println("Hasta luego!");
                salida=true;
            }else{
                System.out.println("::::::::::::::::::::::::::::::::::::::::::");
                System.out.println(":No has seleccionado una opción correcta!:");
                System.out.println("::::::::::::::::::::::::::::::::::::::::::");
            }
        }
    }
    static int[] valorInicialColumnas(){
        int[] columnas = new int[20];
        for(int i=0; i<20; i++){
            columnas[i]=i+1;
        }
        return columnas;
    }//valores iniciales para imprimir en pantalla.
    static char[] valorInicialFilas(){
        char[] filas = new char[11];
        for(int i=0; i<11; i++){
            filas[i]= (char) (65+i) ;
        }
        return filas;
    }//valores iniciales para imprimir en pantalla.
    static void valorInicialAsientos(){
        for(int i=0; i<11;i++){
            for(int j=0; j<20; j++){
                asientos[i][j]=1;//1 diponible, 2 en reserva, y 3 vendido.
            }
        }
    }//estado de los asientos, inicialmente todos disponibles(1).
    static void valorInicialTarcine(){
        for(int i=0; i<4;i++){
            for(int j=0; j<50; j++){
                tarcine[i][j]=0;//0 vacio.
            }
        }
    }//las tarjetas tarcine tiene una valor de cero;
    static void valorInicialReservas(){
        for(int i=0; i<10;i++){
            for(int j=0; j<50; j++){
                reservaFila[i][j]=0;//0 vacio.
                reservaColumna[i][j]=0;//0 vacio.
            }
        }
    }//no hay reservas en las tarjetas.
    static int menuPrincipal(){
        Scanner teclado = new Scanner(System.in);//captura el teclado.
        int opcion;
            System.out.println("          ¿Qué desea hacer?");
            System.out.println("1.Vender boletas    6.Cancelar reserva");
            System.out.println("2.Reservar boletas  7.Mostrar disponibles");
            System.out.println("3.Crear TARCINE     8.Mostrar total de ventas");
            System.out.println("4.Recargar TARCINE  9.Reiniciar programa");
            System.out.println("5.Pagar reservas    0.Salir");
            opcion=teclado.nextInt();
        return opcion;
    }//imprime en pantalla las opciones iniciales y retorna una opción válida.
    static void dibujarAsientos(int[]columnas, char[]filas){
        for(int i=0; i<11;i++){
            for(int j=0; j<20; j++){
                if(asientos[i][j]==1){//1 diponible, 2 en reserva, y 3 vendido.
                    System.out.print(filas[i]);
                    System.out.print(columnas[j] + "  ");
                }else if(asientos[i][j]==2){//1 diponible, 2 en reserva, y 3 vendido.
                    if(j<9){
                        System.out.print("-R  ");
                    }else{
                        System.out.print("-R-  ");
                    }
                }else{
                    if(j<9){
                        System.out.print("-V  ");
                    }else{
                        System.out.print("-V-  ");
                    }
                }
            }
            System.out.println(" ");
        }
    }//imprime en pantalla los asientos disponibles.
    static boolean asientoVendido(int columna, char fila){
        int aux=(int)fila;//convertimos fila en tipo int.
        aux=aux-65;//convertimos al valor correspondiente a la matríz asientos.
        if(asientos[aux][(columna-1)]==1){//si el asiento seleccionado está disponible...
            asientos[aux][(columna-1)]=3;//lo convierte en asiento vendido.
            boolean siguiente=false;
            Scanner teclado = new Scanner(System.in);
            while(!siguiente){
                int opcion;
                System.out.println("¿Desea pagar en efectivo o con TARCINE?");
                System.out.println("1. Efectivo     2. TARCINE");
                opcion=teclado.nextInt();
                if(opcion==1){
                    if(aux<8){
                        ventaTotal=ventaTotal+8000;
                    }else{
                        ventaTotal=ventaTotal+11000;
                    }
                    siguiente=true;
                }else if(opcion==2){
                    boolean valido = false;
                    int captura;//captura con el teclado.
                        System.out.println("Número de TARCINE con el que desea pagar:");
                        captura=teclado.nextInt();
                        for(int i=0; i<50; i++){
                            if(captura == tarcine[0][i]){
                                System.out.println("::::::::::::::::::::");
                                System.out.println(":Número encontrado!:");
                                System.out.println("::::::::::::::::::::");
                                valido=true;
                                if(aux<8){
                                    tarcine[1][i]-=7200;
                                    ventaTotal=ventaTotal+7200;
                                }else{
                                    tarcine[1][i]-=9900;
                                    ventaTotal=ventaTotal+9900;
                                }
                                System.out.println("Obtuviste un descuento del 10%!!!");
                                System.out.println("Su saldo actual es de: $"+tarcine[1][i]);
                                break;
                            }
                        }
                        if(valido==false){
                            System.out.println("::::::::::::::::::::::::::::::");
                            System.out.println(":::: El número no existe! ::::");
                            System.out.println(":No se ha realizado la compra:");
                            System.out.println("::::::::::::::::::::::::::::::");
                            return false;
                        }
                    siguiente=true;
                }else{
                    System.out.println("::::::::::::::::::::::::::::::::::::::::::");
                    System.out.println(":No has seleccionado una opción correcta!:");
                    System.out.println("::::::::::::::::::::::::::::::::::::::::::");
                }
            }
            
            return true;
        }else if(asientos[aux][(columna-1)]==2){
            System.out.println(":::::::::::::::::::::::::::::::::::::");
            System.out.println(":El asiento se encuentra en reserva!:");
            System.out.println("::::  Por favor seleccione otro  ::::");
            System.out.println(":::::::::::::::::::::::::::::::::::::");
            return false;
        }else if(asientos[aux][(columna-1)]==3){
            System.out.println("::::::::::::::::::::::::::::::::::");
            System.out.println(":El asiento se encuentra vendido!:");
            System.out.println("::  Por favor seleccione otro.  ::");
            System.out.println("::::::::::::::::::::::::::::::::::");
            return false;
        }        
        return false;
    }//cambia el estado de un asiento a vendido y registra la venta;
    static boolean asientoReservado(int columna, char fila, int tarjeta, int posicion){
        int aux=(int)fila;//convertimos fila en tipo int.
        aux=aux-65;//convertimos al valor correspondiente a la matríz asientos.
        if(asientos[aux][(columna-1)]==1){//si el asiento seleccionado está disponible...
            asientos[aux][(columna-1)]=2;//lo convierte en asiento reservado.
            reservaFila[posicion][tarjeta]=aux;
            reservaColumna[posicion][tarjeta]=(columna-1);
            return true;
        }else if(asientos[aux][(columna-1)]==2){
            System.out.println("::::::::::::::::::::::::::::::::::::::::");
            System.out.println(":El asiento ya se encuentra en reserva!:");
            System.out.println(":::::  Por favor seleccione otro.  :::::");
            System.out.println("::::::::::::::::::::::::::::::::::::::::");
            return false;
        }else if(asientos[aux][(columna-1)]==3){
            System.out.println("::::::::::::::::::::::::::::::::::");
            System.out.println(":El asiento se encuentra vendido!:");
            System.out.println("::  Por favor seleccione otro.  ::");
            System.out.println("::::::::::::::::::::::::::::::::::");
            return false;
        }        
        return false;
    }//cambia el estado de un asiento a reservado.
    static void asientoAnulado(int columna, int fila){
        int auxFila;
        int auxColumna;
        auxFila=reservaFila[fila][columna];
        auxColumna=reservaColumna[fila][columna];
        asientos[auxFila][auxColumna]=1;
        reservaFila[fila][columna]=0;
        reservaColumna[fila][columna]=0;
    }
    static void reservasPagadas(int columna, int fila){
        int auxFila;
        int auxColumna;
        auxFila=reservaFila[fila][columna];
        auxColumna=reservaColumna[fila][columna];
        if(auxFila<8){
            ventaTotal=ventaTotal+8000;
        }else{
            ventaTotal=ventaTotal+11000;
        }
        asientos[auxFila][auxColumna]=3;
        reservaFila[fila][columna]=0;
        reservaColumna[fila][columna]=0;
    }
    static void reservasPagadasTarcine(int columna, int fila){
        int auxFila;
        int auxColumna;
        auxFila=reservaFila[fila][columna];
        auxColumna=reservaColumna[fila][columna];
        if(auxFila<8){
            tarcine[1][columna]-=7200;
            ventaTotal=ventaTotal+7200;
        }else{
            tarcine[1][columna]-=9900;
            ventaTotal=ventaTotal+9900;
        }
        asientos[auxFila][auxColumna]=3;
        reservaFila[fila][columna]=0;
        reservaColumna[fila][columna]=0;
    }
    static boolean filaCorrecta(char fila){
        if((int)fila>64 && (int)fila<76){
            return true;
        }else{
            return false;
        }
    }//compare si la fila ingresada es un valor correcto y retorne true o false
    static boolean columnaCorrecta(int columna){
        if(columna>0 && columna<21){
            return true;
        }else{
            return false;
        }
    }//compare si la columna ingresada es un valor correcto y retorne true o false
    static char selFila(){
        char opcion;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Seleccione una fila (A-K en Mayúscula):");
        opcion=teclado.next().charAt(0); 
        return opcion;
    }//ingresamos un valor fila en el teclado y lo retornamos;
    static int selColumna(){
        int opcion;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Seleccione una columna (1-20)");
        opcion=teclado.nextInt();
        return opcion;
    }//ingresamos un valor columna en el teclado y lo retornamos;
    static void venderBoletas(){
        boolean siguiente = false;
        boolean compra = false;
        int columna=0;
        char fila=0;
        while(!compra){//termina con una compra exitosa.
            while(!siguiente){//termina si se ingresan valores correctos.
                fila=selFila();
                if(filaCorrecta(fila)==true){
                    siguiente=true;
                }else{
                    System.out.println("::::::::::::::::::::::::::::::::::::::::::");
                    System.out.println(":No has seleccionado una opción correcta!:");
                    System.out.println("::::::::::::::::::::::::::::::::::::::::::");
                }
            }
            while(siguiente){
                columna=selColumna();
                if(columnaCorrecta(columna)==true){
                    siguiente=false;
                }else{
                    System.out.println("::::::::::::::::::::::::::::::::::::::::::");
                    System.out.println(":No has seleccionado una opción correcta!:");
                    System.out.println("::::::::::::::::::::::::::::::::::::::::::");
                }
            }
            compra=asientoVendido(columna,fila);
        }
    }//vende una boleta ó asiento disponible.
    static void crearTarcine(){
        Scanner teclado = new Scanner(System.in);
        boolean siguiente = false;//sale o continua el ciclo
        int captura;//captura con el teclado.
            while(!siguiente){//ingresa un número correcto de tarjeta.
                System.out.println("Ingrese un número de cédula: ");
                captura=teclado.nextInt();
                boolean correcto=false;
                int posicion=50;
                for(int i=0; i<50; i++){
                    if(captura == tarcine[0][i]){
                        System.out.println("::::::::::::::::::::::");
                        System.out.println(":El número ya existe!:");
                        System.out.println("::::::::::::::::::::::");
                        correcto = false;
                        break;
                    }else{
                        correcto = true;
                    }
                }
                for(int j=0; j<50; j++){
                    if(tarcine[0][j]==0){
                        posicion=j;
                        break;
                    }
                }
                if(correcto==true){
                    tarcine[0][posicion]=captura;
                    tarcine[1][posicion]=70000;
                    //ventaTotal=ventaTotal+70000;
                    siguiente=true;
                }
            }
    }//crea una tarjeta tarcine con saldo inicial y número de cédula. (no Registra la venta).
    static void recargarTarcine(){
        Scanner teclado = new Scanner(System.in);
        boolean valido = false;
        int captura;//captura con el teclado.
        System.out.println("Ingrese un número de tarjeta TARCINE: ");
        captura=teclado.nextInt();
        for(int i=0; i<50; i++){
            if(captura == tarcine[0][i]){
                System.out.println("::::::::::::::::::::");
                System.out.println(":Número encontrado!:");
                System.out.println(": Se ha recargado: :");
                System.out.println("::::  $ 50.000  ::::");
                System.out.println("::::::::::::::::::::");
                tarcine[1][i]+=50000;
                //ventaTotal+=50000;
                valido=true;
                break;
            }
        }
        if(valido==false){
            System.out.println("::::::::::::::::::::::");
            System.out.println(":El número no existe!:");
            System.out.println("::::::::::::::::::::::");
        }
    }//recarga 50000 a una tarjeta TARCINE (no registra la venta).
    static void reservarBoletas(){
        Scanner teclado = new Scanner(System.in);
        boolean valido = false;
        int captura;//captura con el teclado.
        System.out.println("Ingrese un número de tarjeta TARCINE: ");
        captura=teclado.nextInt();
        int tamano=10;
        for(int i=0; i<50; i++){
            if(captura == tarcine[0][i]){
                System.out.println("::::::::::::::::::::");
                System.out.println(":Número encontrado!:");
                System.out.println("::::::::::::::::::::");
                valido=true;
                if(tarcine[2][i]==1){
                    System.out.println(":::::::::::::::::::");
                    System.out.println(":Ya has reservado!:");
                    System.out.println(":::::::::::::::::::");
                    break;
                }
                tarcine[2][i]=1;
                boolean salida=false;
                while(!salida){
                    System.out.println("Cuántas reservas desea realizar? máximo 10");
                    tamano=teclado.nextInt();
                    if(tamano>0 && tamano<11){
                        tarcine[3][i]=tamano;
                        salida=true;
                    }else{
                        System.out.println("::::::::::::::::::::::::::::::::::::::::::");
                        System.out.println(":No has seleccionado una opción correcta!:");
                        System.out.println("::::::::::::::::::::::::::::::::::::::::::");
                    }
                }
                for(int j=0; j<tamano; j++){
                    System.out.println("Ingrese el asiento a reservar.");
                    boolean siguiente = false;
                    boolean reserva = false;
                    int columna=0;
                    char fila=0;
                    while(!reserva){//termina con una reserva exitosa.
                        while(!siguiente){//termina si se ingresan valores correctos.
                            fila=selFila();
                            if(filaCorrecta(fila)==true){
                                siguiente=true;
                            }else{
                                System.out.println("::::::::::::::::::::::::::::::::::::::::::");
                                System.out.println(":No has seleccionado una opción correcta!:");
                                System.out.println("::::::::::::::::::::::::::::::::::::::::::");
                            }
                        }
                        while(siguiente){
                            columna=selColumna();
                            if(columnaCorrecta(columna)==true){
                                siguiente=false;
                            }else{
                                System.out.println("::::::::::::::::::::::::::::::::::::::::::");
                                System.out.println(":No has seleccionado una opción correcta!:");
                                System.out.println("::::::::::::::::::::::::::::::::::::::::::");
                            }
                        }
                        reserva=asientoReservado(columna,fila,i,j);
                    }
                }
                break;
            }
        }
        if(valido==false){
            System.out.println("::::::::::::::::::::::");
            System.out.println(":El número no existe!:");
            System.out.println("::::::::::::::::::::::");
        }
    }
    static void anularReservas(){
        Scanner teclado = new Scanner(System.in);
        boolean valido = false;
        int captura;//captura con el teclado.
        System.out.println("Número de TARCINE al que desea anular todas las reservas: ");
        captura=teclado.nextInt();
        int tamano;
        for(int i=0; i<50; i++){
            if(captura == tarcine[0][i]){
                System.out.println("::::::::::::::::::::");
                System.out.println(":Número encontrado!:");
                System.out.println("::::::::::::::::::::");
                valido=true;
                if(tarcine[2][i]==0){
                    System.out.println(":::::::::::::::::::::");
                    System.out.println(":No tienes reservas!:");
                    System.out.println(":::::::::::::::::::::");
                    break;
                }
                tarcine[2][i]=0;//cambia el estado a sin reserva.
                tamano=tarcine[3][i];//tamano guarda temporalmente el tamaño de la reserva
                tarcine[3][i]=0;//el tamaño de reserva es cero.
                for(int j=0; j<tamano; j++){
                    asientoAnulado(i,j);
                }
                System.out.println("::::::::::::::::::::");
                System.out.println(":Reservas anuladas!:");
                System.out.println("::::::::::::::::::::");
                break;
            }
        }
        if(valido==false){
            System.out.println("::::::::::::::::::::::");
            System.out.println(":El número no existe!:");
            System.out.println("::::::::::::::::::::::");
        }
    }
    static void pagarReservas(){
        Scanner teclado = new Scanner(System.in);
        boolean valido = false;
        int captura;//captura con el teclado.
        System.out.println("Número de TARCINE al que desea pagar todas las reservas: ");
        captura=teclado.nextInt();
        int tamano;
        for(int i=0; i<50; i++){
            if(captura == tarcine[0][i]){
                System.out.println("::::::::::::::::::::");
                System.out.println(":Número encontrado!:");
                System.out.println("::::::::::::::::::::");
                valido=true;
                if(tarcine[2][i]==0){
                    System.out.println(":::::::::::::::::::::");
                    System.out.println(":No tienes reservas!:");
                    System.out.println(":::::::::::::::::::::");
                    break;
                }
                tarcine[2][i]=0;//cambia el estado a sin reserva.
                tamano=tarcine[3][i];//tamano guarda temporalmente el tamaño de la reserva
                tarcine[3][i]=0;//el tamaño de reserva es cero.
                boolean salida=false;
                while(!salida){
                    System.out.println("Cómo desea pagar sus reservas?");
                    System.out.println("1. Efectivo     2. TARCINE");
                    int opcion;
                    opcion=teclado.nextInt();
                    if(opcion==1){
                        for(int j=0; j<tamano; j++){
                            reservasPagadas(i,j);
                        }
                        salida=true;
                    }else if(opcion==2){
                        for(int j=0; j<tamano; j++){
                            reservasPagadasTarcine(i,j);
                        }
                        System.out.println(":::::::::::::::::::::::");
                        System.out.println(":Su saldo actual es de:");
                        System.out.println("::::::: $"+tarcine[1][i]+" :::::::::");
                        salida=true;
                    }else{
                        System.out.println("::::::::::::::::::::::::::::::::::::::::::");
                        System.out.println(":No has seleccionado una opción correcta!:");
                        System.out.println("::::::::::::::::::::::::::::::::::::::::::");
                    }
                }
                System.out.println(":::::::::::::::::::");
                System.out.println(":Reservas pagadas!:");
                System.out.println(":::::::::::::::::::");
                break;
            }
        }
        if(valido==false){
            System.out.println("::::::::::::::::::::::");
            System.out.println(":El número no existe!:");
            System.out.println("::::::::::::::::::::::");
        }
    }
}
