/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author jcarv
 */
public class CalculadoraManual {

    // Constructor vacío permitirá acceder a los métodos de clase
    public CalculadoraManual() {}
    
    
    // Métodos auxiliares
    /**
     * Imprime por pantalla una linea conformada por el caracter y de la longitud
     * pasados como parámetro
     * 
     * @param longitud
     * @param caracter
     */
    private static void printNChar(int longitud, char caracter) {
        for (int i = 0; i < longitud; i++) {
            System.out.print(caracter);
        }
        System.out.println();
    }
    
    /**
     * Cabecera común de las operaciones suma, resta, multiplicación y división
     * 
     * Ejemplo: 
     *  operador: '+'
     *  num1: 1999
     *  num2: 105001
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
    private static void cabeceraOperacion(char operador, int num1, int num2) {
        
        String operAString = "OPERACION";
        
        int maxNum = (num1 > num2) ? num1 : num2;
        final int LONG_FILA = 15;
        final int MAX_DIGITOS = obtenerLongitud(maxNum);
        final int ESPACIOS_IZQ = LONG_FILA - MAX_DIGITOS;
        
        switch (operador) {
            case '+' ->  operAString = "Suma";
            case '-' -> operAString = "Resta";
            case '*' -> operAString = "Multiplicaión";
            case '/' -> operAString = "División";
                
            default -> System.out.println("Compruebe que utiliza uno de estos caracteres (+, -, /, *)");
        }
        
        System.out.printf("Cálculo de la %s:\n\n", operAString);
        System.out.printf("%" + ESPACIOS_IZQ + "s" + "%" + MAX_DIGITOS + "d%n", " ",  num1);
        System.out.printf(operador + "%" + (ESPACIOS_IZQ - 1) + "s" + "%" + MAX_DIGITOS + "d%n", " ",  num2);
        printNChar(LONG_FILA, '-');
    }
    
    /**
     * Retorna el valor del primer argumento elevado a la potencia del segundo argumento.
     * 
     * @param base La base
     * @param exponente El exponente
     * @return int La base elevada al exponente
     */
    private static double potenciar(double base, int exponente) {

        int resultado = 1;
        
        if (exponente < 0) {
            exponente *= -1;
            base = 0.1;
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
     * Retorna la cantidad de digitos que tiene la representación de un número entero
     * 
     * @param numero Número entero
     * @return int Longitud de la representación de un nñumero entero 
     */
    private static int obtenerLongitud(int numero) {
        
        int orden = 0;
        
        // la representación de cero contiene un dígito
        do {   
            numero /= 10;
            orden = sumar(orden, 1);
            
        } while (numero != 0);
        
        return orden;
    }
    
    /**
     * Retorna el número entero siguiente al pasado como parámetro
     * 
     * @param numer Número entero
     * @return int numero + 1
     */
    private static int incrementar(int numero) {
        return sumar(numero, 1);
    }
    
    /**
     * Retorna el número entero anterior al pasado como parámetro
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
    public static void sumarMenu(int sumando1, int sumando2) {
        
        // La operación de suma comienza con acarreo cero
        int acarreo = 0;
        int exponente = 0;
        // Resultado de la susumando2ma
        int sumaFinal = 0;
        
        cabeceraOperacion('+', sumando1, sumando2);

        while (sumando1 != 0 || sumando2 != 0 || acarreo != 0) {
              
            // Almacena la suma de los dígitos
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
            sumaDigitos = digitoSumando1 + digitoSumando2 + acarreo;
            
            acarreo = sumaDigitos / 10;
            sumaDigitos = sumaDigitos % 10;
            
            System.out.printf(" Nos da %d", sumaDigitos);
            if (acarreo == 0) {
                System.out.print(".\n");
            } else {
                System.out.printf(" y me llevo %d.%n", acarreo);
            }
            
            // Suma el resultado teniendo en cuenta las unidades
            sumaFinal += sumaDigitos * (int) potenciarBase10(exponente);
            
            exponente++;
        }
        System.out.printf("%nSuma Total: %d%n%n", sumaFinal);
    }

    /**
     * Salida por pantalla detallando paso a paso el algoritmo para la operación
     * "resta", de números naturales de forma manual.
     * 
     * VÁLIDO SOLO PARA VALORES DE: minuendo >= sustraendo
     * 
     * @param minuendo Minuendo de la operación
     * @param sustraendo Sustraendo de la operación
     */
    public static void restarMenu(int minuendo, int sustraendo) {
        
        // La operación de resta comienza con acarreo cero
        int acarreo = 0;
        int exponente = 0;
        
        // Valor final de la resta a retornar por el método
        int restaFinal = 0;
        
        cabeceraOperacion('-', minuendo, sustraendo);
        
        // Recorre los dígitos de ambos números comenzando por el menos significativo
        // de ambos ralizando su resta y determinado si hay acarreo
        while (minuendo != 0 || sustraendo != 0 || acarreo != 0) {
            
            int restaDigitos;

            int digitoMinuendo = minuendo % 10;
            int digitoSustraendo = sustraendo % 10;
            
            minuendo /= 10;
            sustraendo /= 10;
            
            // Si el resultado en la resta de los dígitos es negativo le sumamos 10
            // implica acarreo en la siguiente iteración
            if (digitoMinuendo < digitoSustraendo) {
                System.out.printf("Como %d es menor que %d sumamos 10 a %1$d. ", 
                        digitoMinuendo, digitoSustraendo);
                digitoMinuendo = sumar(digitoMinuendo, 10);
            }
            
            // Realiza la resta de los dígitos tenieno en cuenta el acarreo 
            // de la operación anterior
            digitoSustraendo = sumar(digitoSustraendo, acarreo);
            restaDigitos = digitoMinuendo - digitoSustraendo;
            
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
     *  Salida por pantalla detallando paso a paso el algoritmo para la operación
     * "multiplicacion", de números enteros positivos de forma manual
     * 
     * @param multiplicando Número natural
     * @param multiplicador Numero natural
     */
    public static void multiplicarMenu(int multiplicando, int multiplicador) {
        
        int multiplicacionFinal = 0;
        // Partimos de un exponente un orden menor a la cantidad de dígitos contenidos en el multiplicador
        int exponente = decrementar(obtenerLongitud(multiplicador));
        
        cabeceraOperacion('*', multiplicando, multiplicador);
        
        // Itera sombre cada dígito del multiplicador
        for (int i = exponente; i >= 0 ; i--) {
            
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
    public static void dividirMenu(int dividendo, int divisor) {
        
        int cociente = 0;
        int resto = dividendo;
        
        cabeceraOperacion('/', dividendo, divisor);
        
        // Para valores de dividendo mayor que divisor o dividendo cero no es necesario
        // hacer ninguna operacion para conocer el resultado
        if (dividendo >= divisor && divisor != 0) {
            
            // Exponente del orden de magnitud de un número
            int exponente = obtenerLongitud(dividendo);
            int longDivisor = obtenerLongitud(divisor);

            // Exponente que permitirá comenzar con el grupo de dígitos mínimo necesario
            // para comenzar
            exponente = restar(exponente, longDivisor);
            resto = dividendo / (int) potenciarBase10(exponente);

            exponente = decrementar(exponente);       
            System.out.println("Tomamos el primer grupo de cifras: " + resto);

            do {
                
                int contiene = 0;
                String vezVeces;
                
                // Se agregan dígitos al resto mientras queden dígitos por recorrer y
                // el divisor no este contenido en el resto
                while (resto < divisor && exponente >= 0) {
                    
                    System.out.printf("Divisor (%d) mayor que el resto (%d). ",
                            divisor, resto);
                    
                    resto = multiplicar(resto, 10)
                            ;
                    // Obtención del dígito que será "bajado"
                    int grupoDigitos = dividendo / (int) potenciarBase10(exponente);
                    int digito = (grupoDigitos > 9) ? grupoDigitos % 10 : grupoDigitos;
                    
                    resto = sumar(resto, digito);
                    System.out.printf("Bajamos el %d y lo agregamos al resto: %d%n",
                            digito, resto);
                    
                    if (resto < divisor && exponente > 0) {
                        
                        cociente *= 10;
                        
                        System.out.printf("Divisor (%d) mayor que el resto (%d). ",
                            divisor, resto);
                        
                        if (cociente != 0) {
                            System.out.println("Añadimos un cero al cociente: " + cociente);
                        }
                    }
                    
                    exponente = decrementar(exponente);
                } // Fin del segundo while
                
                if (resto >= divisor) {
                    System.out.printf("%d esta contenido en %d, ",
                            divisor, resto);
                }
                
                // Veces que el divisor esta contenido en el resto (división entera)
                while (resto >= divisor) {
                    resto = restar(resto, divisor);
                    contiene = incrementar(contiene);
                }
                
                cociente = sumar(multiplicar(cociente, 10), contiene);
                
                if (contiene != 0) {
                    vezVeces = (contiene == 1) ? " vez. " : " veces. ";
                    System.out.print(contiene + vezVeces);
                    System.out.println("Le añadimos al cociente: " + cociente);
                }
                
            } while (exponente >= 0); // aun no se han recorrido todos los dígitos
            
            System.out.println("No quedan dígitos por recorrer.");
        } // fin del if
        
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
     * Retorna la resta de dos números enteros positivos.
     * 
     * VÁLIDO SOLO PARA VALORES DE: minuendo >= sustraendo
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
            
            // Obtiene el dígito menos significativo del sustraendo y el minuendo
            int ultimoDigMinuendo = minuendo % 10;
            int ultimoDigSustraendo = sustraendo % 10;
            
            minuendo /= 10;
            sustraendo /= 10;
            
            // Si el resultado en la resta de los dígitos es negativo le sumamos 10
            // implica acarreo en la siguiente iteración
            if (ultimoDigMinuendo < ultimoDigSustraendo) {
                ultimoDigMinuendo = sumar(ultimoDigMinuendo, 10);
            }
            
            // Realiza la resta de los dígitos tenieno en cuenta el acarreo 
            // de la operación anterior
            ultimoDigSustraendo = sumar(ultimoDigSustraendo, acarreo);
            restaDigitos = ultimoDigMinuendo - ultimoDigSustraendo;
            
            // Actuliza la resta, teniendo en cuenta la potencia de los dígitos
            restaDigitos = restaDigitos * (int) potenciarBase10(exponente);
            restaFinal = sumar(restaFinal, restaDigitos);
            
            // Si el digito del minuendo es mayor que 9 es necesario el acarreo
            acarreo = ultimoDigMinuendo / 10;
            
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
        
        // Unidad del dídigito en el que esta trabajando la iteración (unidad, décima, ..)
        int exponente = 0;
        
        // Almacena el resultado final de la multiplicación que será retornado
        // por el método
        int multiplicacionFinal = 0; 
        
        // Itera si ninguno de los números es cero (un multiplicación por cero
        // es cero) por lo que aun quedan digitos sobre los que operar
        while (multiplicando != 0 && multiplicador != 0) {
            
            // Contendrá la multiplicación en forma de suma del multiplicando por
            // el digitoMultiplicador
            int multiplicacionParcial = 0;
            
            // Obtiene el digito menos significativo del multiplicador
            int digitoMultiplicador = multiplicador % 10;
            
            // Condición finalizadora del while
            multiplicador /= 10;
            
            // Itera sobre el digitoMultiplicador acumulando en cada iteración
            // el multiplicando en multiplicacionParcial
            for(int i = 0; i < digitoMultiplicador; i = sumar(i, 1)) {
                multiplicacionParcial = sumar(multiplicacionParcial, multiplicando);
            }
            
            // Ajusta el valor obtenido teniendo en cuenta el orden del digito 
            // del multiplicando sobre el cual se esta trabajando
            multiplicacionParcial *= (int) potenciarBase10(exponente);
            
            // Actuliza el resutado final con el valor obtenido 
            multiplicacionFinal = sumar(multiplicacionFinal,  multiplicacionParcial);
            
            // Incrementa el exponente
            exponente = incrementar(exponente);
        }
        
        // Retorna el resultado de la multiplicación
        //  TODO comprobar que no haya resultado negativo overflow
        return multiplicacionFinal;
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
                
                /* 
                    En dividendos con ceros intermedios (ej. 20001) la obtencion 
                del dígito con el que trabajar en la iteración no es posible hacerla 
                como se ha venido realizando hasta ahora.
                    
                    La obtención se realizará dividiendo e dividendo por el orden de
                magnitud correspondiente al dígito que se busca y si el resultado
                obtenido es mayor que 9 nos quedamos con el dígito menos significativo
                del resultado
                */ 
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
            cociente = sumar(multiplicar(cociente, 10), contiene);
            
        }
        
        // Retorna el resultado del cociente de la división entera 
        return cociente;
    }
    
}
