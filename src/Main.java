import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        float a, e, eps;
        System.out.print("Введите нижнюю границу: ");
        a = scan.nextFloat();
        System.out.print("Введите шаг: ");
        e = scan.nextFloat();
        System.out.print("Введите точность: ");
        eps = scan.nextFloat();
        System.out.println("\n=====================================================================\n ");
        System.out.println("Значение посчитанное с помощью типа double\n=====================================================================\n ");
        countDoubleSin(a, e, eps);
        System.out.println("Значение посчитанное с помощью типа float\n===============================================================\n ");
        countFloatSin(a, e, eps);
    }
    private static void countDoubleSin(double x, double e, double eps){
        double result = 0;
        while (true) {
            int n = 1;
            while (Math.pow(x, 2 * n - 1) / getFactorial(2 * n - 1) > eps) {
                double factorial = getFactorial(2 * n - 1), member_of_Taylor = Math.pow(x, 2 * n - 1);
                result += Math.pow(-1, n - 1) * member_of_Taylor / factorial;
                /*System.out.println("\nПромежуточный результат: " + result + "\n" + "Погрнешность промежуточных значений : " + Math.abs(Math.sin(x) - result) + "\nЗначение факториала " + getFactorial(2 * n - 1) + "\nЗначение знаменателя " + Math.pow(x, 2 * n - 1) + "\n");*/
                n++;
                if (getFactorial(2 * n - 1) > Double.MAX_VALUE || Math.pow(x, 2 * n - 1) > Double.MAX_VALUE
                        || getFactorial(2 * n - 1) < Double.MIN_VALUE || Math.pow(x, n - 1) < Double.MIN_VALUE) {
                    return;
                }
            }
            if(Math.abs(Math.sin(x) - result) > eps)
                break;
            System.out.println("Значение аргумента: " + x);
            System.out.println("Точное значение: " + Math.sin(x));
            System.out.println("Значение полученное путём разложения: " + result);
            System.out.println("Итоговая погрешность: " + Math.abs(Math.sin(x) - result));
            System.out.println("\n=========================================\n");
            result = 0;
            x += e;
        }
    }

    private static void countFloatSin(double x, double e, double eps){
        float float_result = 0;
        while (true) {
            int n = 1;
            while (Math.pow(x, 2 * n - 1) / getFactorial(2 * n - 1) > eps) {
                float float_factorial = getFloatFactorial(2 * n - 1), float_member_of_Taylor = (float) Math.pow(x, 2 * n - 1);
                float_result += Math.pow(-1, n - 1) * float_member_of_Taylor / float_factorial;
                n++;
                if (getFloatFactorial(2 * n - 1) > Float.MAX_VALUE || (float) Math.pow(x, 2 * n - 1) > Float.MAX_VALUE
                        || getFloatFactorial(2 * n - 1) < Float.MIN_VALUE || (float) Math.pow(x, n - 1) < Float.MIN_VALUE) {
                    return;
                }
            }
            if(Math.abs(Math.sin(x) - float_result) > eps)
                break;
            System.out.println("Значение аргумента: " + x);
            System.out.println("Точное значение: " + Math.sin(x));
            System.out.println("Значение полученное путём разложения: " + float_result);
            System.out.println("Итоговая погрешность: " + Math.abs(Math.sin(x) - float_result));
            System.out.println("\n====================================================\n");
            float_result = 0;
            x += e;
        }
    }

    private static double getFactorial(int n) {
        double result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        return result;
    }
    private static float getFloatFactorial(int n) {
        float result = 1;
        for (int i = 1; i <= n; i++) {
            result = result * i;
        }
        return result;
    }
}
