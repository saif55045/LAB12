import java.sql.SQLException;
import java.util.Map;

public class Mainclass {
    public static void main(String[] args) throws SQLException, InterruptedException {
            Database db = new Database();
            CacheProxy cacheProxy = new CacheProxy(db, 5); 
            
            System.out.println("Requesting Student 1:");
            System.out.println(cacheProxy.getStudentById(1));

            System.out.println("Requesting Student 2:");
            System.out.println(cacheProxy.getStudentById(2));

            System.out.println("Requesting Student 1 again (should be cached):");
            System.out.println(cacheProxy.getStudentById(1));

            System.out.println("Simulating delay for cache expiration...");
            Thread.sleep(6 * 1000); 
            for (int i = 1; i <= 6; i++) {
                System.out.println("Requesting Student " + i + ":");
                System.out.println(cacheProxy.getStudentById(i));
            }

            for (int i = 1; i <= 5; i++) {
                System.out.println("Requesting Student " + i + ":");
                System.out.println(cacheProxy.getStudentById(i));
            }
                    
    }
}
