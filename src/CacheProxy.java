import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

public class CacheProxy {
    private final Database database;
    private final Map<Integer, CacheEntry> cache;
    private final int cacheSize;

    public CacheProxy(Database database, int cacheSize) {
        this.database = database;
        this.cacheSize = cacheSize;
        this.cache = new LinkedHashMap<Integer, CacheEntry>(cacheSize, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, CacheEntry> eldest) {
                return size() > cacheSize;
            }
        };
    }

    public Student getStudentById(int id) throws SQLException {
        CacheEntry entry = cache.get(id);
        if (entry != null && !entry.isExpired()) {
        	System.out.println("From Cache");
            return entry.getStudent();
        }

        Student student = database.getstudentbyid(id);
        if (student != null) {
        	System.out.println("From Database");
            cache.put(id, new CacheEntry(student));
        }
        return student;
    }

    private static class CacheEntry {
        private final Student student;
        private final Timestamp timestamp;

        public CacheEntry(Student student) {
            this.student = student;
            this.timestamp = Timestamp.from(Instant.now());
        }

        public boolean isExpired() {
            long currentTime = Instant.now().toEpochMilli();
            long cacheTime = timestamp.getTime();
            return currentTime - cacheTime > 5 * 1000; 
        }

        public Student getStudent() {
            return student;
        }
    }
}
