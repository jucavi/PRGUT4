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
     * Retorna el valor del primer argumento elevado a la potencia del segundo argumento.
     * 
     * @param base La base
     * @param exponente El exponente
     * @return int La base elevada al exponente
     */
    private static double potenciar(double base, int exponente) {
        
        // Almacena el resultados de multiplicaciones sucesivas de la base
        // La potenciación con exponente 0 devuelve 1 como resultado
        int resultado = 1;
        
        if (exponente < 0) {
            exponente *= -1;
            base = 0.1;
        }
        
        // Iteracion que realiza la multiplcacion de la base un número de veces
        // igual al valor que tenga el exponente
        for (int i = 0; i < exponente; i++) {
            resultado *= base;
        }
        
        return resultado;
    }
    
     /**
     * Retorna el valor de 10 elevado a la potencia del argumento.
     * 
     * @param exponente El exponente
     * @return int 10 elevado al exponente
     */
    private static double potenciarBase10(int exponente) {
        return potenciar(10, exponente);
    }
    
    
    /**
     * Retorna la cantidad de dígitos que componen un número entero
     * Se considera que 0 tiene un dígito
     * 
     * @param numero Número entero
     * @return int Número de dígitos
     */
    private static int obtenerOrden(int numero) {
        
        int orden = 0;
        
         while (numero != 0) {   
            numero /= 10;
            orden = sumar(orden, 1);
            
        }
        
        return orden;
    }
    
    /**
     * Retorna el número siguiente al pasado como parámetro
     * 
     * @param numer Número entero
     * @return int numero + 1
     */
    private static int incrementar(int numero) {
        return sumar(numero, 1);
    }
    
    /**
     * Retorna el número anterior al pasado como parámetro
     * 
     * @param numer Número entero
     * @return int numero - 1
     */
    private static int decrementar(int numero) {
        return restar(numero, 1);
    }
    
    
    public static void sumarMenu(int sumando1, int sumando2) {
        System.out.println(sumar(sumando1, sumando2));
    }

    public static void restarMenu(int minuendo, int sustraendo) {
        System.out.println(restar(minuendo, sustraendo));
    }

    public static void multiplicarMenu(int multiplicando, int multiplicador) {
        System.out.println(multiplicar(multiplicando, multiplicador));
    }

    public static void dividirMenu(int dividendo, int divisor) {
        System.out.println(dividir(dividendo, divisor));
    }

    /**
     * Retorna la suma de dos números enteros
     * 
     * @param sumando1 El primer sumando
     * @param sumando2 El segundo sumando
     * @return int sumando1 + sumando2
     */
    private static int sumar(int sumando1, int sumando2) {
        
        // La operación de resta comienza con acarreo cero
        int acarreo = 0;
        
        // Unidad del ´digito en el que esta trabajando la iteración (unidad, décima, ..)
        int exponente = 0;
        
        // Valor final de la suma a retornar por el método
        int sumaFinal = 0;

        // El proceso de suma será realizado mientras queden digitos en alúgn 
        // sumando o haya un acarreo pendiente
        while (sumando1 != 0 || sumando2 != 0 || acarreo != 0) {
            
            // Almacena la suma de los dígitos
            int sumaDigitos;
            
            // Obtiene el dígito menos significativo de ambos sumandos
            int digitoSumando1 = sumando1 % 10;
            int digitoSumando2 = sumando2 % 10;
            
            // Elimina el dígito menos signifícativo de ambos dumandos
            // Condiciónes finalizadoras del while
            sumando1 /= 10;
            sumando2 /= 10;

            // Suma de digitos teniendo en cuenta el acarreo de la operación anterior
            sumaDigitos = digitoSumando1 + digitoSumando2 + acarreo;
            
            // Se obtiene el acarreo y el resultado de la suma objetivo
            // para números menores de 10: acarreo = 0, sumaDigitos = número
            acarreo = sumaDigitos / 10;
            sumaDigitos = sumaDigitos % 10;
            
            // Suma teniendo en cuenta unidades
            sumaFinal += sumaDigitos * (int) potenciarBase10(exponente);
            
            // Incrementa el exponente
            exponente++;
        }
        
        // Retorna el resultado de la suma
        return sumaFinal;
    }

    
    /**
     * Retorna la resta de dos números
     * 
     * @param minuendo El minuendo
     * @param sustraendo El sustraendo
     * @return int minuendo - sustraendo
     */
    private static int restar(int minuendo, int sustraendo) {
        
        // La operación de resta comienza con acarreo cero
        int acarreo = 0;
        
        // Unidad del ´digito en el que esta trabajando la iteración (unidad, décima, ..)
        int exponente = 0;
        
        // Valor final de la resta a retornar por el método
        int restaFinal = 0;
        
        // El proceso de resta será realizado mientras queden digitos a restar o 
        // quede algun acarreo pendiente de la iteración anterior
        while (minuendo != 0 || sustraendo != 0 || acarreo != 0) {
            
            // Almacena la resta de los dígitos
            int restaDigitos;
            
            // Obtiene el dígito menos significativo del sustraendo y el minuendo
            //
            // Como el paso de parámetros es por valor y son datos primitivos podemos
            // modificarles dentro de los métodos sin que los valores originales
            // cambien
            int ultimoDigMinuendo = minuendo % 10;
            int ultimoDigSustraendo = sustraendo % 10;
            
            // Actuliza el minuendo y el sustraendo eliminando el último dígito
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
            
            // Incrementa el exponente
            exponente = incrementar(exponente);
        }
        
        // Retorna el resultado de la resta
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
        
        // Unidad del ´digito en el que esta trabajando la iteración (unidad, décima, ..)
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
        int exponente = obtenerOrden(dividendo);
        
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
            cociente = sumar(cociente * 10, contiene);
            
        }
        
        // Retorna el resultado del cociente de la división entera 
        return cociente;
    }
    
}
