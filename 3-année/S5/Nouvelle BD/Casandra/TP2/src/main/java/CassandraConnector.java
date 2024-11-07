import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class CassandraConnector {
    private Session session;

    public void connect (String node, Integer port){
        Cluster.Builder b = Cluster.builder().addContactPoint(node);
        if(port != null){
            b.withPort(port);
        }
        session = b.build().connect();
    }

    public Session getSession(){
        return this.session;
    }
}
