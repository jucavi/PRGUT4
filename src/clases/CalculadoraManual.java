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
    private static int obtenerNDigitos(int numero) {
        
        int longitud = 0;
        
        do {   
            numero /= 10;
            longitud = sumar(longitud, 1);
            
        } while (numero != 0);
        
        return longitud;
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
            exponente = sumar(exponente, 1);
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
            exponente = sumar(exponente, 1);
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
        
        // Unidad del ´digito en el que esta trabajando la iteración (unidad, décima, ..) 
        int exponente = 0;
        
        // Resto de la operación
        int resto = 0;
        
        // Valor a retornar por el método
        int cociente = 0;
        
        // Itera mientras el dividendo no sea cero y aún quedan dígitos en los que operar)
        while (dividendo != 0 || exponente >= 0) {
            
            // Obtiene la cantidad de dígitos que contiene el dividendo
            int numDigDividendo = obtenerNDigitos(dividendo);
            
            // A partir de esa cantidad se obtiene el orden sobre el que se va a operar
            exponente = restar(numDigDividendo, 1);
            
            // Almacena la cantida de veces que está contenido el dividendo en el grupo
            // de digitos seleccionado
            int estaConenido = 0;
            
            do {
                // Si hay resto de la iteración anterior aumentamos un orden de magnitud
                // para agregar el siguiente dígito a operar del dividendo
                resto *= 10;
                
                // Obtiene el dígito mas significativo del dividendo
                int primerDigDividendo = dividendo / (int) potenciarBase10(exponente);
                
                // Suma al resto el dígito anterior
                resto = sumar(resto, primerDigDividendo);
                
                // Condición finalizadora del while
                // Actualiza el dividendo con los dígitos sobre los que aún no se ha operado
                dividendo %= (int) potenciarBase10(exponente);
                
                // GRAN PROBLEMA CON LOS CEROS!!!!!!!!!!!!!!
                
                // Actauliza el exponente
                // Si el exponente es menor que cero significa que ya hemos recorrido
                // todo el divisor
                // Condición de finalizacion del do-while
                exponente = restar(exponente, 1);
                
                // Agrega
                if (resto < dividendo) {
                    cociente *= 10;
                }
                
            // Itera mientras el resto no contenga al divisor
            } while (resto <= divisor && exponente >= 0);
            
            
            // Obtiene cuantas veces esta contenido el divisor en el resto y actualiza
            // el valor del resto para la siguiente iteración
            while (resto >= divisor) {
                resto = restar(resto, divisor);
                estaConenido = sumar(estaConenido, 1);
            }
            
            // Añade al cociente el nuevo valor obtenido
            cociente = sumar(cociente * 10, estaConenido);
            
        }
        
        // Retorna el resultado del cociente de la división entera 
        return cociente;
    }
    
}
