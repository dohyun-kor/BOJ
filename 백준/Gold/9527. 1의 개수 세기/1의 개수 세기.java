import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Scanner를 이용해 두 자연수 A와 B를 입력받습니다.
        Scanner sc = new Scanner(System.in);
        long A = sc.nextLong();
        long B = sc.nextLong();
        
        // A부터 B까지의 합은 f(B) - f(A - 1)로 구합니다.
        long result = countOnesUpTo(B) - countOnesUpTo(A - 1);
        System.out.println(result);
    }
    
    /**
     * countOnesUpTo(n)는 0부터 n까지의 모든 수를 이진수로 표현했을 때
     * 나타나는 1의 총 개수를 재귀적으로 계산하여 반환합니다.
     */
    public static long countOnesUpTo(long n) {
        // 기저 조건: n이 0이면 0을 반환합니다.
        if (n == 0) return 0;
        
        // Long.highestOneBit(n)은 n보다 작거나 같은 가장 큰 2의 거듭제곱 값을 반환합니다.
        long power = Long.highestOneBit(n);
        
        // power가 2^p이므로, p를 구합니다.
        int p = Long.numberOfTrailingZeros(power);  // power = 2^p
        
        // 0부터 (2^p - 1)까지의 1의 개수의 합은 p * 2^(p-1)
        long base = p * (power >> 1);  // (power >> 1)는 power/2와 같습니다.
        
        // n = 2^p + r 인 r 계산
        long remainder = n - power;
        
        // 2^p부터 n까지는 최상위 비트가 모두 1이므로, (r + 1)의 1이 추가됩니다.
        long msbContribution = remainder + 1;
        
        // 재귀 호출: 나머지 부분에 대해 동일한 계산을 수행합니다.
        return base + msbContribution + countOnesUpTo(remainder);
    }
}
