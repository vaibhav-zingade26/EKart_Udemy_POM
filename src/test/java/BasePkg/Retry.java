package BasePkg;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    int count=0;
    int threshold=2;
    @Override
    public boolean retry(ITestResult result) {
            if(count<threshold){
            count++;
            System.out.println("Attempting the by "+result.getMethod()+" | "+count);
            return true;
        }
        return false;
    }
}
