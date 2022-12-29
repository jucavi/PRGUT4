/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calculadora;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Aplicacíón donde se implementan métodos para realizar y representar por pantalla 
 * paso a paso el proceso de resolución "manual" de las operaciones matmáticas de suma, 
 * resta, multiplicación y división
 * 
 * @author jcarv
 */
public class CalculadoraManual {

    /**
     * Retorna una cadena de texto con longitud y solo conformada por el
     * caracter pasados por parámetro
     *
     * Ejemplo: longitud = 6 caracter = '*'
     *
     * Retorno -> "******"
     *
     * @param longitud Longitud de la linea de texto
     * @param caracter Caracter de relleno
     * @return String caracter * longitud
     */
    private static String repetirNchar(int longitud, char caracter) {
        StringBuilder linea = new StringBuilder();

        // Agrega longitud veces el caracter a la linea
        for (int i = 0; i < longitud; i = sumar(i, 1)) {
            linea.append(caracter);
        }

        return linea.toString();
    }

    /**
     * Imprime por pantalla una linea de texto de longitud definida por el
     * primer parámetro y conformada exclusivamente por el caracter del segundo
     * parámetro
     *
     * @param longitud Longitud de la linea de texto
     * @param caracter Caracter de relleno
     */
    private static void imprimirNChar(int longitud, char caracter) {
        System.out.println(repetirNchar(longitud, caracter));
    }

    /**
     * Cabecera común de las operaciones suma, resta, multiplicación y división
     *
     * Ejemplo: operador: '+' num1: 1999 num2: 105001
     *
     * Salida:
     *
     * Cálculo de la Suma:
     *
     *          105001 
     * +          1999
     * ---------------
     *
     * @param operador Simbolo mátematico de la opración {+, -, *, /}
     * @param num1 Número entero positivo
     * @param num2 Número entero positivo
     */
    private static void imprimirCabeceraOp(char operador, int num1, int num2) {

        String operAString = "OPERACION INVÁLIDA";

        int maxNum = (num1 > num2) ? num1 : num2;
        final int LONG_FILA = 15;
        final int MAX_DIGITOS = obtenerLongitud(maxNum);
        final int ESPACIOS_IZQ = LONG_FILA - MAX_DIGITOS;

        switch (operador) {
            case '+' ->
                operAString = "Suma";
            case '-' ->
                operAString = "Resta";
            case '*' ->
                operAString = "Multiplicaión";
            case '/' ->
                operAString = "División";

            default ->
                System.out.println("Compruebe que utiliza uno de estos caracteres (+, -, /, *)");
        }

        System.out.printf("Cálculo de la %s:\n\n", operAString);
        System.out.printf("%" + ESPACIOS_IZQ + "s" + "%" + MAX_DIGITOS + "d%n", " ", num1);
        System.out.printf(operador + "%" + (ESPACIOS_IZQ - 1) + "s" + "%" + MAX_DIGITOS + "d%n", " ", num2);
        imprimirNChar(LONG_FILA, '-');
    }

    /**
     * Retorna el valor del primer argumento elevado a la potencia del segundo
     * argumento.
     *
     * @param base La base
     * @param exponente El exponente
     * @return double base**exponente
     */
    private static double potenciar(double base, int exponente) {

        int resultado = 1;

        if (exponente < 0) {
            exponente *= -1;
            base = 1 / base;
        }

        for (int i = 0; i < exponente; i++) {
            resultado *= base;
        }

        return resultado;
    }

    /**
     * Retorna el valor de 10 elevado a la potencia del argumento.
     *
     * @param exponente El exponente
     * @return int 10^exponente
     */
    private static double potenciarBase10(int exponente) {
        return potenciar(10, exponente);
    }

    /**
     * Retorna la cantidad de dígitos que tiene la representación de un número
     * entero
     *
     * @param numero Número entero
     * @return int Longitud de la representación de un número entero
     */
    private static int obtenerLongitud(int numero) {

        int orden = 0;

        // la representación de cero cocienteIteracion un dígito
        do {
            numero /= 10;
            orden = sumar(orden, 1);

        } while (numero != 0);

        return orden;
    }

    /**
     * Retorna el número entero siguiente al pasado como parámetro utilizando el
     * método sumar
     *
     * @param numer Número entero
     * @return int numero + 1
     */
    private static int incrementar(int numero) {
        return sumar(numero, 1);
    }

    /**
     * Retorna el número entero anterior al pasado como parámetro utilizando el
     * método sumar
     *
     * @param numer Número entero
     * @return int numero - 1
     */
    private static int decrementar(int numero) {
        return restar(numero, 1);
    }

    /**
     * Salida por pantalla detallando paso a paso el algoritmo para la operación
     * "suma", de números enteros de forma manual
     *
     * @param sumando1 Primer sumando de la operación
     * @param sumando2 Segundo sumando de la operación
     */
    private static void sumarMenu(int sumando1, int sumando2) {

        // La operación de suma comienza con acarreo cero
        int acarreo = 0;
        int exponente = 0;
        // Resultado de la susumando2ma
        int sumaFinal = 0;

        imprimirCabeceraOp('+', sumando1, sumando2);
        
        // Recorre los dígitos de ambos números comenzando por el menos significativo
        // de ambos ralizando su suma y determinado si hay acarreo
        while (sumando1 != 0 || sumando2 != 0 || acarreo != 0) {

            // Almacena la suma de los dígitos con los que se opera
            int sumaDigitos;

            int digitoSumando1 = sumando1 % 10;
            int digitoSumando2 = sumando2 % 10;

            System.out.printf("Sumamos %d mas %d", digitoSumando1, digitoSumando2);
            if (acarreo == 0) {
                System.out.print(".");
            } else {
                System.out.printf(" y %d que me llevaba.", acarreo);
            }

            // Condiciónes finalizadoras del while
            sumando1 /= 10;
            sumando2 /= 10;

            // Suma de digitos teniendo en cuenta el acarreo de la operación anterior
            sumaDigitos = sumar(digitoSumando1, digitoSumando2);
            sumaDigitos = sumar(sumaDigitos, acarreo);

            acarreo = sumaDigitos / 10;
            sumaDigitos = sumaDigitos % 10;

            System.out.printf(" Nos da %d", sumaDigitos);
            if (acarreo == 0) {
                System.out.print(".\n");
            } else {
                System.out.printf(" y me llevo %d.%n", acarreo);
            }

            // Suma el resultado teniendo en cuenta las unidades
            sumaFinal = sumar(sumaFinal, sumaDigitos * (int) potenciarBase10(exponente));

            exponente = incrementar(exponente);
        }
        System.out.printf("%nSuma Total: %d%n%n", sumaFinal);
    }

    /**
     * Salida por pantalla detallando paso a paso el algoritmo para la operación
     * "resta", de números enteros de forma manual.
     *
     *
     * @param minuendo Minuendo de la operación
     * @param sustraendo Sustraendo de la operación
     */
    private static void restarMenu(int minuendo, int sustraendo) {

        // La operación de resta comienza con acarreo cero
        int acarreo = 0;
        int exponente = 0;

        // Valor final de la resta a retornar por el método
        int restaFinal = 0;

        imprimirCabeceraOp('-', minuendo, sustraendo);

        // Recorre los dígitos de ambos números comenzando por el menos significativo
        // de ambos ralizando su resta y determinado si hay acarreo
        while (minuendo != 0 || sustraendo != 0 || acarreo != 0) {

            int restaDigitos;

            int digitoMinuendo = minuendo % 10;
            int digitoSustraendo = sustraendo % 10;
            
            // Condiciones finalizadoras del while
            minuendo /= 10;
            sustraendo /= 10;

            // Si el resultado en la resta de los dígitos es negativo le sumamos 10
            // implica acarreo en la siguiente iteración
            if (digitoMinuendo < digitoSustraendo) {
                System.out.printf("Como %d es menor que %d, sumamos 10 a %1$d. ",
                        digitoMinuendo, digitoSustraendo);
                digitoMinuendo = sumar(digitoMinuendo, 10);
            }

            // Realiza la resta de los dígitos tenieno en cuenta el acarreo 
            // de la operación anterior
            digitoSustraendo = sumar(digitoSustraendo, acarreo);
            restaDigitos = restar(digitoMinuendo, digitoSustraendo);

            System.out.printf("Restamos %d", digitoSustraendo);
            if (acarreo != 0) {
                System.out.printf(" y %d que me llevaba.", acarreo);
            }
            System.out.printf(" a %d.", digitoMinuendo);

            // Si el digito del minuendo es mayor que 9 es necesario el acarreo
            acarreo = digitoMinuendo / 10;

            System.out.printf(" Nos da %d", restaDigitos);
            if (acarreo == 0) {
                System.out.print(".\n");
            } else {
                System.out.printf(" y me llevo %d.%n", acarreo);
            }

            // Actuliza la resta, teniendo en cuenta la potencia de los dígitos
            restaDigitos = restaDigitos * (int) potenciarBase10(exponente);
            restaFinal = sumar(restaFinal, restaDigitos);

            exponente = incrementar(exponente);
        }
        System.out.printf("%nResta Total: %d%n%n", restaFinal);

    }

    /**
     * Salida por pantalla detallando paso a paso el algoritmo para la operación
     * "multiplicación", de números enteros positivos de forma manual incluyendo
     * el cero
     *
     * @param multiplicando Número entero mayor o igual que cero
     * @param multiplicador Número entero mayor o igual que cero
     */
    private static void multiplicarMenu(int multiplicando, int multiplicador) {

        int multiplicacionFinal = 0;
        // Partimos de un exponente un orden menor a la cantidad de dígitos 
        // en la representación del número multiplicador
        int exponente = decrementar(obtenerLongitud(multiplicador));

        imprimirCabeceraOp('*', multiplicando, multiplicador);

        // Itera sombre cada dígito del multiplicador
        for (int i = exponente; i >= 0; i--) {

            int multiplicacionParcial = 0;
            int orden = (int) potenciarBase10(i);

            // Con abs(dígitoMultiplicador) puede ser posible realizar la multiplicación 
            // de todos los números enteros, habria que tener en cuanta el signo
            int digitoMultiplicador = multiplicador / orden;

            String vezVeces = (digitoMultiplicador == 1) ? "vez" : "veces";
            String posicionEs = (i == 1) ? "posición" : "posiciones";

            multiplicador %= orden;

            // Calcula la multiplicación parcial aplicando la suma
            for (int j = 0; j < digitoMultiplicador; j++) {
                multiplicacionParcial = sumar(multiplicacionParcial, multiplicando);
            }

            System.out.printf("%d sumado consigo mismo %d %s es %d",
                    multiplicando, digitoMultiplicador, vezVeces, multiplicacionParcial);
            if (i != 0) {
                System.out.printf(" desplazamos %d %s", i, posicionEs);
            }
            multiplicacionParcial *= orden;
            System.out.printf(" %d.%n", multiplicacionParcial);

            if (multiplicacionFinal != 0) {
                System.out.printf("Sumamos %d a %d%n",
                        multiplicacionParcial, multiplicacionFinal);
            }
            multiplicacionFinal = sumar(multiplicacionFinal, multiplicacionParcial);

        }

        System.out.printf("%nMultiplicación Total: %d%n", multiplicacionFinal);
    }
    
        /**
     * Salida por pantalla detallando paso a paso el algoritmo para la operación
     * "división" entera, de números naturales de forma manual
     *
     * @param dividendo Número a dividir
     * @param divisor Número que divide
     */
    private static void dividirMenu(int dividendo, int divisor) {

        int cociente = 0;
        int resto = dividendo;

        imprimirCabeceraOp('/', dividendo, divisor);

        // Para valores de dividendo mayor que divisor o dividendo cero no es necesario
        // hacer ninguna operacion para conocer el resultado
        if (dividendo >= divisor && divisor != 0) {

            // Exponente del orden de magnitud de un número
            int exponente = obtenerLongitud(dividendo);
            int longDivisor = obtenerLongitud(divisor);

            // Exponente que permitirá comenzar con el grupo de dígitos mínimo
            exponente = restar(exponente, longDivisor);
            resto = dividendo / (int) potenciarBase10(exponente);

            exponente = decrementar(exponente);
            System.out.printf("Tomamos el primer grupo de cifras: %d%n", resto);

            do {

                int cocienteIteracion = 0;
                String restoGrupo = (cociente != 0) ? "resto" : "grupo de cifras";

                // Se agregan dígitos al resto o al grupo de cifras inicial mientras 
                // queden dígitos por recorrer y el divisor no este contenido en el resto
                while (resto < divisor && exponente >= 0) {

                    System.out.printf("Divisor (%d) mayor que el %s (%d). ",
                            divisor, restoGrupo, resto);

                    resto = multiplicar(resto, 10);
                    
                    // Obtención del dígito que será "bajado"
                    int grupoDigitos = dividendo / (int) potenciarBase10(exponente);
                    int digito = (grupoDigitos > 9) ? grupoDigitos % 10 : grupoDigitos;

                    resto = sumar(resto, digito);
                    System.out.printf("Bajamos el %d y lo agregamos al %s: %d%n",
                            digito, restoGrupo, resto);

                    if (resto < divisor && exponente > 0) {

                        cociente *= 10;

                        System.out.printf("Divisor (%d) mayor que el %s (%d). ",
                                divisor, restoGrupo, resto);

                        if (cociente != 0) {
                            System.out.println("Añadimos un cero al cociente: " + cociente);
                        }
                    }

                    exponente = decrementar(exponente);
                } // Fin del primer while

                if (resto >= divisor) {
                    System.out.printf("%d esta contenido en %d, ",
                            divisor, resto);
                }

                // Veces que el divisor esta contenido en el resto (división entera)
                while (resto >= divisor) {
                    resto = restar(resto, divisor);
                    cocienteIteracion = incrementar(cocienteIteracion);
                }
                
                cociente = multiplicar(cociente, 10);
                cociente = sumar(cociente, cocienteIteracion);

                if (cocienteIteracion != 0) {
                    String vezVeces = (cocienteIteracion == 1) ? " vez " : " veces ";
                    System.out.print(cocienteIteracion + vezVeces + " y resto " + resto);
                    System.out.println(". Le añadimos al cociente: " + cociente);
                }

            } while (exponente >= 0); // Aún no se han recorrido todos los dígitos

            System.out.println("No quedan dígitos por recorrer.\n");
        } // Fin del if

        if (divisor == 0) {
            System.out.println("La división por cero no está permitida");
        } else {
            System.out.println("División Total:");
            System.out.println("  Cociente:" + cociente);
            System.out.println("  Resto:" + resto);
        }
    }

    /**
     * Retorna la suma de dos números enteros
     *
     * @param sumando1 El primer sumando
     * @param sumando2 El segundo sumando
     * @return int sumando1 + sumando2
     */
    private static int sumar(int sumando1, int sumando2) {

        // La operación de suma comienza con acarreo cero
        int acarreo = 0;
        int exponente = 0;
        // Valor final de la suma a retornar por el método
        int sumaFinal = 0;

        // Recorre los dígitos de ambos números comenzando por el menos significativo
        // de ambos ralizando su suma y determinado si hay acarreo
        while (sumando1 != 0 || sumando2 != 0 || acarreo != 0) {

            int sumaDigitos;

            // Obtiene el dígito menos significativo de ambos sumandos
            int digitoSumando1 = sumando1 % 10;
            int digitoSumando2 = sumando2 % 10;

            // Elimina el dígito menos signifícativo de ambos dumandos
            // Condiciónes finalizadoras del while
            sumando1 /= 10;
            sumando2 /= 10;

            sumaDigitos = digitoSumando1 + digitoSumando2 + acarreo;

            // Se obtiene el acarreo y el resultado de la suma
            acarreo = sumaDigitos / 10;
            sumaDigitos = sumaDigitos % 10;

            // Suma teniendo en cuenta unidades
            sumaFinal += sumaDigitos * (int) potenciarBase10(exponente);

            exponente++;
        }

        return sumaFinal;
    }

    /**
     * Retorna la resta de dos números enteros.
     *
     * @param minuendo El minuendo
     * @param sustraendo El sustraendo
     * @return int minuendo - sustraendo
     */
    private static int restar(int minuendo, int sustraendo) {

        // La operación de resta comienza con acarreo cero
        int acarreo = 0;
        int exponente = 0;

        // Valor final de la resta a retornar por el método
        int restaFinal = 0;

        // Recorre los dígitos de ambos números comenzando por el menos significativo
        // de ambos ralizando su resta y determinado si hay acarreo
        while (minuendo != 0 || sustraendo != 0 || acarreo != 0) {

            int restaDigitos;
            int restaConOrden;

            // Obtiene el dígito menos significativo del sustraendo y el minuendo
            int ultimoDigMinuendo = minuendo % 10;
            int ultimoDigSustraendo = sustraendo % 10;

            minuendo /= 10;
            sustraendo /= 10;
            
            restaDigitos = sumar(ultimoDigMinuendo, -ultimoDigSustraendo);
            restaDigitos = sumar(restaDigitos, acarreo);

            // Se obtiene el acarreo y el resultado de la suma
            acarreo = restaDigitos / 10;
            restaDigitos = restaDigitos % 10;

            // Suma teniendo en cuenta unidades
            restaConOrden = restaDigitos * (int) potenciarBase10(exponente);
            restaFinal = sumar(restaFinal, restaConOrden);
            

            exponente = incrementar(exponente);
        }

        return restaFinal;
    }

    /**
     * Retorna la multiplicación de dos números enteros positivos
     *
     * @param multiplicando El multiplicando
     * @param multiplicador El multiplicador
     * @return int multiplicando * multiplicador
     */
    private static int multiplicar(int multiplicando, int multiplicador) {

        int multiplicacionFinal = 0;
        
        // Partimos de un exponente un orden menor a la cantidad de dígitos en 
        // la representación del multiplicador
        int exponente = decrementar(obtenerLongitud(multiplicador));

        // Itera sombre cada dígito del multiplicador
        for (int i = exponente; i >= 0; i--) {

            int multiplicacionParcial = 0;
            int orden = (int) potenciarBase10(i);

            // Con abs(dígitoMultiplicador) puede ser posible realizar la multiplicación 
            // de todos los números enteros, habria que tener en cuanta el signo
            int digitoMultiplicador = multiplicador / orden;

            multiplicador %= orden;

            // Calcula la multiplicación parcial aplicando la suma
            for (int j = 0; j < digitoMultiplicador; j++) {
                multiplicacionParcial = sumar(multiplicacionParcial, multiplicando);
            }

            multiplicacionParcial *= orden;
            
            multiplicacionFinal = sumar(multiplicacionFinal, multiplicacionParcial);
        }

        return multiplicacionFinal;
    }
    
    /**
     * Muestra por pantalla si el resultado obtenido con el uso del método resta
     * corresponde con el valor esperado
     * 
     * @param minuendo Número natural
     * @param sustraendo Número natural
     * @param diferencia minuendo - sustraendo
     */
    private static void comprobarResta (int minuendo, int sustraendo, int diferencia) {
        
        String esCorrectoStr;
        
        int resultadoRestar = restar(minuendo, sustraendo);
        
        esCorrectoStr = (resultadoRestar == diferencia) ?"CORRECTO" : "INCORRECTO";
        
        System.out.printf("Esperado: %d, Obtenido: %d%n!%s¡%n%n", 
                diferencia, resultadoRestar, esCorrectoStr);
    }
    
    /**
     * Muestra por pantalla si el resultado obtenido con el uso de los métodos 
     * implementados corresponden con los valores esperados para los números náturales
     * 
     * @param dividendo Número natural a dividir
     * @param divisor Número natural que divide
     * @param cociente dividendo / divisor
     * @param resto dividendo - (cociente * divisor)
     */
    private static void comprobarDivision (int dividendo, int divisor, int cociente, int resto) {
        
        int restoCalc = dividendo;
        int cocientePorDivisor;
        int cocienteCalc = 0;
        int sumTemp = 0;
        String esCorrectoCocStr, esCorrectoResStr;
        
        if (dividendo >= divisor && divisor != 0) {
//            try {
//                cocienteCalc = dividir(dividendo, divisor);
//            } catch (ArithmeticException e) {
//                System.out.println(e);
//            }
            while (sumar(sumTemp, divisor) <= dividendo) {
                sumTemp = sumar(sumTemp, divisor);
                cocienteCalc = sumar(cocienteCalc,  1);
            }

            cocientePorDivisor = multiplicar(cocienteCalc, divisor);
            restoCalc = restar(dividendo, cocientePorDivisor);
        }
        
        esCorrectoCocStr = (cocienteCalc == cociente) ? "CORRECTO" : "INCORRECTO";
        esCorrectoResStr = (restoCalc == resto) ? "CORRECTO" : "INCORRECTO";

        System.out.printf("Cociente esperado: %d, Cociente obtenido: %d%n!%s¡%n", 
                cociente, cocienteCalc, esCorrectoCocStr);
        System.out.printf("Cociente esperado: %d, Cociente obtenido: %d%n!%s¡%n%n", 
                resto, restoCalc, esCorrectoResStr);
    }
    
    /**
     * Borra la pantalla
     */
    private static void borrarPantalla() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    /**
     * Imprime por pantalla la cabecera del menú principal de la aplicación
     *
     * Salida:
     *
     * *********************************************
     *   1. Sumar 
     *   2. Restar 
     *   3. Multipicar 
     *   4. Dividir
     *   5. Comprobar Restar
     *   6. Comprobar Dividir
     *   0. Salir
     * *********************************************
     * Seleccione la operacion que desea realizar:
     *
     *
     */
    private static void imprimirCabeceraMenuPrinc() {

        final int LONG_LINEA = 45;

        borrarPantalla();

        imprimirNChar(LONG_LINEA, '*');

        System.out.println("\t1. Sumar");
        System.out.println("\t2. Restar");
        System.out.println("\t3. Multipicar");
        System.out.println("\t4. Dividir");
        System.out.println("\t5. Comprobar Restar");
        System.out.println("\t6. Comprobar Dividir");
        System.out.println("\t0. Salir");

        imprimirNChar(LONG_LINEA, '*');
    }

    /**
     * Entrada por Teclado
     *
     * @return String linea introducida por teclado
     */
    private static String introdicirLinea() {
        Scanner sc = new Scanner(System.in);

        return sc.nextLine();
    }

    /**
     * Espera hasta que se introduzca un valor cualqiera por teclado
     */
    private static void esperar() {
        System.out.println("Pulse ENTER para continuar");
        introdicirLinea();
    }
    
    /**
     * Retorna si el número pasado como parámetro cumple la condicion de ser
     * natural y dentro del límite superior del tipo short
     * 
     * @param opernado Número entero
     * @return boolean 0 &lt; operando &le; 32767
     */
    private static boolean validarNaturalShort(long operando) {
        return operando > 0 && operando <= Short.MAX_VALUE;
    }

    /**
     * Retorna entrada por teclado acotada a números naturales entre (1, 32767)
     * Se repite mientras la entrada no sea válida
     *
     * @param ordinal Lugar que ocupa la operación (primer, segundo, tercero...)
     * @return short Número positivo mayor que cero y menor o igual que 32767
     */
    private static short introducirOperandoShort(String ordinal) {

        long operando = 0;
        boolean esNaturalShort = false;
        
        System.out.printf("Introduzca el %s operando (1, %d): ", ordinal, Short.MAX_VALUE);

        do {
            
            try {   

                Scanner sc = new Scanner(System.in);
                operando = sc.nextLong();
                
                esNaturalShort = validarNaturalShort(operando);
            
                if (!esNaturalShort) {
                    System.out.println("El número sobrepasa los límites permitidos, inténtelo de nuevo: ");
                }

            } catch (InputMismatchException e) {
                System.out.println("Por favor entre un valor númerico válido: ");
            }
            
        } while (!esNaturalShort);

        return (short) operando;
    }
    
    /**
     * Menú principal de la aplicación
     *
     * Permite representar por pantalla el proceso 'manual' que se lleva a cabo en la
     * solución de las operaciones matemáticas de suma, resta, multiplicación y división
     * 
     * Será ejecutado hasta que le usuario ingrese por teclado el 0
     */
    private static void lanzarMenuPrincipal() {

        boolean continua = true;

        while (continua) {

            String opcionLista;
            short operando1;
            short operando2;

            imprimirCabeceraMenuPrinc();

            System.out.print("Seleccione la operación que desea realizar: ");
            opcionLista = introdicirLinea();
            System.out.println();
            
            switch (opcionLista) {

                case "1" -> {
                    borrarPantalla();
                    operando1 = introducirOperandoShort("primer");
                    operando2 = introducirOperandoShort("segundo");
                    
                    sumarMenu(operando1, operando2);
                    esperar();
                }
                
                case "2" -> {          
                    borrarPantalla();
                    operando1 = introducirOperandoShort("primer");
                    operando2 = introducirOperandoShort("segundo");
                    
                    if (operando2 > operando1) {
                        System.out.println("\nLa operación devuelve un resultado negativo y no será procesada.\n");
                    } else {
                        restarMenu(operando1, operando2);
                    }
                    esperar();
                }
                
                case "3" -> {
                    borrarPantalla();
                    operando1 = introducirOperandoShort("primer");
                    operando2 = introducirOperandoShort("segundo");
                    
                    multiplicarMenu(operando1, operando2);
                    esperar();

                }
                
                case "4" -> {
                    borrarPantalla();
                    operando1 = introducirOperandoShort("primer");
                    operando2 = introducirOperandoShort("segundo");
                    
                    dividirMenu(operando1, operando2);
                    esperar();

                }
                
                case "5" -> {
                    operando1 = introducirOperandoShort("primer");
                    operando2 = introducirOperandoShort("segundo");
                    
                    comprobarResta(operando1, operando2, operando1 - operando2);
                    esperar();

                }
                case "6" -> {
                    operando1 = introducirOperandoShort("primer");
                    operando2 = introducirOperandoShort("segundo");
                    
                    comprobarDivision(operando1, operando2, operando1 / operando2, operando1 % operando2);
                    esperar();

                }
                
                case "0" ->
                    continua = false;
            }
        }
    }
    
    // Estaba hecho antes de la tutoria por una mala lectura del enunciado por mi parte
    // No queria borrar un código que funcionaba
    
    /**
     * Retorna el cociente de la división de dos números enteros positivos
     *
     * @param dividendo El dividendo
     * @param divisor El divisor
     * @return int El cociente de dividendo / divisor
     * @throws ArithmeticException La división por cero no está permitida
     */
    private static int dividir(int dividendo, int divisor) throws ArithmeticException {
        
        // Si el dividendo es cero lanza una exepción 
        if (divisor == 0) throw new ArithmeticException("La división por cero no está permitida");
        
        // Si el dividendo no esta contenido en el divisor devolvemos 0 como resultado
        if (divisor > dividendo) return 0;
        
        // Valor a retornar por el método
        int cociente = 0;
        
        // Exponente del orden de magnitud de un número
        int exponente = obtenerLongitud(dividendo);
        
        // Resto de la operación, se inicializa con el dígito mas significativo del dividendo
        int resto = dividendo / (int) potenciarBase10(exponente);
        
        // Actulizamos el exponente
        exponente = decrementar(exponente);
        
        // Itera mientras aún quedan dígitos en los que operar
        while (exponente >= 0) {
            
            // Almacena la cantida de veces que está contenido el dividendo en el grupo
            // de digitos seleccionado
            int contiene = 0;
            
            // itera siempre que el resto sea menor que el divisor y queden dígitos
            // por recorrer en el dividendo
            while (resto < divisor && exponente >= 0) {
                
                // Si hay resto de la iteración anterior o aumentamos un orden de magnitud
                // para agregar el siguiente dígito a operar del dividendo
                resto *= 10;                
                
                // En dividendos con ceros intermedios (ej. 20001) la obtencion 
                // del dígito con el que trabajar en la iteración no es posible hacerla 
                // como se ha venido realizando hasta ahora.
                //     
                // La obtención se realizará dividiendo e dividendo por el orden de
                // magnitud correspondiente al dígito que se busca y si el resultado
                // obtenido es mayor que 9 nos quedamos con el dígito menos significativo
                // del resultado
                int grupoDigitos = dividendo / (int) potenciarBase10(exponente);
                int digito = (grupoDigitos > 9) ? grupoDigitos % 10 : grupoDigitos;
                
                // Suma al resto el dígito anterior
                resto = sumar(resto, digito);
                
                //
                if (resto < divisor && exponente > 0) {
                    cociente *= 10;
                }
                
                // Actauliza el exponente
                // Si el exponente es menor que 0 significa que ya hemos recorrido
                // todo el divisor
                // Condición de finalizacion del while
                exponente = decrementar(exponente);
            } 
            
            
            // Obtiene cuantas veces esta contenido el divisor en el resto y actualiza
            // el valor del resto para la siguiente iteración
            while (resto >= divisor) {
                resto = restar(resto, divisor);
                contiene = incrementar(contiene);
            }
            
            // Añade al cociente el nuevo valor obtenido
            cociente = multiplicar(cociente, 10);
            cociente = sumar(cociente, contiene);
            
        }
        
        // Retorna el resultado del cociente de la división entera 
        return cociente;
    }
    
    /**
     * Método principal
     * 
     * @param args 
     */
   public static void main(String[] args) {
        lanzarMenuPrincipal();
        System.out.println("Hasta la vista!");
   }
}
